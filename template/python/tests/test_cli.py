import pytest

from __PACKAGE__.cli import main

pytestmark = pytest.mark.component


def test_greets_a_name(capsys: pytest.CaptureFixture[str]) -> None:
    assert main(["--name", "Alice"]) == 0
    assert capsys.readouterr().out == "Hello, Alice!\n"


def test_greets_the_world(capsys: pytest.CaptureFixture[str]) -> None:
    assert main([]) == 0
    assert capsys.readouterr().out == "Hello, world!\n"
