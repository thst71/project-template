#!/bin/sh
# Turns this template into a project: copies the chosen variant into the root, fills in the
# project name, writes AGENTS.md and removes the template. Run it once, in a fresh copy.
#
# Usage: ./init.sh <node|python|go> <bdt|plain> <project-name>
#   node | python | go  the language of the hello world
#   bdt            requirements-based blackbox tests: acceptance scenarios in feature files,
#                  test and traceability reports
#   plain          blackbox tests as the final step, without scenarios
set -eu

fail() {
	echo "init.sh: $*" >&2
	exit 1
}

[ $# -eq 3 ] || fail "usage: ./init.sh <node|python|go> <bdt|plain> <project-name>"
language=$1
testing=$2
name=$3
case $language in node | python | go) ;; *) fail "language must be node, python or go, not $language" ;; esac
case $testing in bdt | plain) ;; *) fail "testing must be bdt or plain, not $testing" ;; esac
case $name in
'' | -* | *[!a-z0-9-]*) fail "project name: lower case letters, digits and -, not starting with -" ;;
esac

# Work in the template root only: everything below refers to its literal paths.
cd "$(dirname "$0")"
[ -f init.sh ] && [ -d template/common ] && [ -d .agents/skills ] ||
	fail "run init.sh in the root of an unused copy of the template"

package=$(printf '%s' "$name" | tr - _)
skills=$(tr -d ' \r\n' <.agents/skills/VERSION)

for overlay in common "$language" "$testing" "$language-$testing"; do
	if [ -d "template/$overlay" ]; then
		cp -R "template/$overlay/." .
	fi
done
if [ -d src/__PACKAGE__ ]; then
	mv src/__PACKAGE__ "src/$package"
fi

# AGENTS.md: the common text with the sections of the variant
awk -v components="template/fragments/$language.md" -v testing="template/fragments/$language-$testing.md" '
	function insert(file,   line) {
		while ((getline line < file) > 0) print line
		close(file)
	}
	$0 == "__COMPONENTS__" { insert(components); next }
	$0 == "__TESTING__" { insert(testing); next }
	{ print }
' template/fragments/AGENTS.md >AGENTS.md

# Placeholders in the copied files
find . -type f ! -path './.git/*' ! -path './.agents/*' ! -path './template/*' ! -name init.sh |
	while IFS= read -r file; do
		if grep -q -e __PROJECT__ -e __PACKAGE__ -e __AGENT_SKILLS__ "$file"; then
			sed -e "s/__PROJECT__/$name/g" -e "s/__PACKAGE__/$package/g" -e "s/__AGENT_SKILLS__/$skills/g" \
				"$file" >"$file.init"
			mv "$file.init" "$file"
		fi
	done

rm -rf template
rm -f init.sh .github/workflows/template.yml

echo "initialised $name ($language, $testing); next steps:"
case $language in
node)
	echo "  npm install && npx playwright install chromium"
	echo "  npm run check"
	;;
python)
	echo "  uv sync"
	echo "  uv run poe check"
	;;
go)
	echo "  go mod download   (Task and golangci-lint must be installed)"
	echo "  task check"
	;;
esac
if [ "$testing" = bdt ]; then
	echo "  the acceptance scenarios: see AGENTS.md, Testing"
fi
echo "  git add -A && git commit -m \"chore: initialise $name from project-template\""
