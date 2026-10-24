"""The command as a separate process."""

import subprocess
import sys

import pytest

pytestmark = pytest.mark.blackbox


def run(*args: str) -> str:
    completed = subprocess.run(
        [sys.executable, "-m", "__PACKAGE__", *args],
        capture_output=True,
        text=True,
        check=True,
    )
    return completed.stdout


def test_greets_the_world() -> None:
    assert run() == "Hello, world!\n"


def test_greets_a_name() -> None:
    assert run("--name", "Alice") == "Hello, Alice!\n"
