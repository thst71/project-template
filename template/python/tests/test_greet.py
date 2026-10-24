import pytest

from __PACKAGE__.greet import greet

pytestmark = pytest.mark.unit


@pytest.mark.parametrize(
    ("name", "greeting"),
    [
        ("Alice", "Hello, Alice!"),
        ("  Bob ", "Hello, Bob!"),
        ("", "Hello, world!"),
        ("   ", "Hello, world!"),
        (None, "Hello, world!"),
    ],
)
def test_greet(name: str | None, greeting: str) -> None:
    assert greet(name) == greeting
