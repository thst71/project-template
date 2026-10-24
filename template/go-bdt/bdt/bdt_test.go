//go:build bdt

// Package bdt runs the acceptance scenarios of the specification against the built command.
package bdt

import (
	"fmt"
	"os"
	"os/exec"
	"path/filepath"
	"strings"
	"testing"

	"github.com/cucumber/godog"
)

var binary string

func TestMain(m *testing.M) {
	dir, err := os.MkdirTemp("", "bdt-")
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

type greeter struct{ greeting string }

func (g *greeter) greet(args ...string) error {
	out, err := exec.Command(binary, args...).Output()
	g.greeting = strings.TrimSpace(string(out))
	return err
}

func (g *greeter) greetingIs(want string) error {
	if g.greeting != want {
		return fmt.Errorf("want %q, got %q", want, g.greeting)
	}
	return nil
}

func initializeScenario(sc *godog.ScenarioContext) {
	g := &greeter{}
	sc.Step(`^I greet nobody$`, func() error { return g.greet() })
	sc.Step(`^I greet "([^"]*)"$`, func(name string) error { return g.greet("-name", name) })
	sc.Step(`^the greeting is "([^"]*)"$`, g.greetingIs)
}

func TestAcceptance(t *testing.T) {
	suite := godog.TestSuite{
		ScenarioInitializer: initializeScenario,
		Options: &godog.Options{
			Format:   "cucumber:../build/bdt/cucumber.json,pretty",
			Paths:    []string{"../specification/acceptance"},
			TestingT: t,
			Strict:   true,
		},
	}
	if suite.Run() != 0 {
		t.Fatal("acceptance scenarios failed")
	}
}
