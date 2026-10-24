"""The command line: ``__PROJECT__ [--name NAME]``."""

import argparse
import sys
from collections.abc import Sequence

from __PACKAGE__.greet import greet


def main(argv: Sequence[str] | None = None) -> int:
    """Prints the greeting for the given name."""
    parser = argparse.ArgumentParser(
        prog="__PROJECT__",
        description="Greets the world or a name.",
    )
    parser.add_argument("--name", help="the name to greet")
    args = parser.parse_args(argv)
    sys.stdout.write(greet(args.name) + "\n")
    return 0
