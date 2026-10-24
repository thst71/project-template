package greet

import "testing"

func TestGreet(t *testing.T) {
	cases := map[string]string{
		"Alice":  "Hello, Alice!",
		"  Bob ": "Hello, Bob!",
		"":       "Hello, world!",
		"   ":    "Hello, world!",
	}
	for name, want := range cases {
		if got := Greet(name); got != want {
			t.Errorf("Greet(%q) = %q, want %q", name, got, want)
		}
	}
}
