// Command __PROJECT__ greets the world or the name it is given.
package main

import (
	"flag"
	"fmt"
	"io"
	"os"

	"__PROJECT__/internal/greet"
)

func main() {
	os.Exit(run(os.Args[1:], os.Stdout, os.Stderr))
}

// run prints the greeting for the arguments and returns the exit code.
func run(args []string, stdout, stderr io.Writer) int {
	flags := flag.NewFlagSet("__PROJECT__", flag.ContinueOnError)
	flags.SetOutput(stderr)
	name := flags.String("name", "", "the name to greet")
	if err := flags.Parse(args); err != nil {
		return 2
	}
	_, _ = fmt.Fprintln(stdout, greet.Greet(*name))
	return 0
}
