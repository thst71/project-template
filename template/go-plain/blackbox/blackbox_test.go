//go:build blackbox

// Package blackbox tests the built command as a separate process.
package blackbox

import (
	"os"
	"os/exec"
	"path/filepath"
	"testing"
)

var binary string

func TestMain(m *testing.M) {
	dir, err := os.MkdirTemp("", "blackbox-")
	if err != nil {
		panic(err)
	}
	binary = filepath.Join(dir, "__PROJECT__")
	if out, err := exec.Command("go", "build", "-o", binary, "..").CombinedOutput(); err != nil {
		panic(string(out))
	}
	code := m.Run()
	_ = os.RemoveAll(dir)
	os.Exit(code)
}

func greet(t *testing.T, args ...string) string {
	t.Helper()
	out, err := exec.Command(binary, args...).Output()
	if err != nil {
		t.Fatal(err)
	}
	return string(out)
}

func TestGreetsTheWorld(t *testing.T) {
	if got := greet(t); got != "Hello, world!\n" {
		t.Errorf("got %q", got)
	}
}

func TestGreetsAName(t *testing.T) {
	if got := greet(t, "-name", "Alice"); got != "Hello, Alice!\n" {
		t.Errorf("got %q", got)
	}
}
