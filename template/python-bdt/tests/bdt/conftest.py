"""Acceptance scenarios: the command as a separate process."""

import re
import subprocess
import sys
from collections.abc import Callable
from typing import Any

import pytest
from pytest_bdd import parsers, then, when

# Test IDs and trace tags (AT-1.1, FR-1.1) identify scenarios; they are no pytest markers.
_ID = re.compile(r"[A-Z][A-Z0-9]*-\d+(\.\d+)*")


def pytest_bdd_apply_tag(tag: str, function: Callable[..., Any]) -> bool | None:
    return True if _ID.fullmatch(tag) else None


def run(*args: str) -> str:
    completed = subprocess.run(
        [sys.executable, "-m", "__PACKAGE__", *args],
        capture_output=True,
        text=True,
        check=True,
    )
    return completed.stdout.strip()


@pytest.fixture
def greeting() -> dict[str, str]:
    return {}


@when("I greet nobody")
def greet_nobody(greeting: dict[str, str]) -> None:
    greeting["text"] = run()


@when(parsers.parse('I greet "{name}"'))
def greet_name(greeting: dict[str, str], name: str) -> None:
    greeting["text"] = run("--name", name)


@then(parsers.parse('the greeting is "{expected}"'))
def greeting_is(greeting: dict[str, str], expected: str) -> None:
    assert greeting["text"] == expected
