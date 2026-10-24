package main

import (
	"bytes"
	"testing"
)

func TestRun(t *testing.T) {
	cases := []struct {
		args []string
		code int
		out  string
	}{
		{[]string{"-name", "Alice"}, 0, "Hello, Alice!\n"},
		{nil, 0, "Hello, world!\n"},
		{[]string{"-unknown"}, 2, ""},
	}
	for _, c := range cases {
		var stdout, stderr bytes.Buffer
		if code := run(c.args, &stdout, &stderr); code != c.code || stdout.String() != c.out {
			t.Errorf("run(%q) = %d, %q; want %d, %q", c.args, code, stdout.String(), c.code, c.out)
		}
	}
}
