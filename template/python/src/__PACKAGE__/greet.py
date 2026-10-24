"""The greeting."""


def greet(name: str | None = None) -> str:
    """The greeting for a name; a missing or blank name greets the world."""
    trimmed = (name or "").strip()
    return f"Hello, {trimmed or 'world'}!"
