// Package greet builds the greeting.
package greet

import (
	"fmt"
	"strings"
)

// Greet is the greeting for a name; a blank name greets the world.
func Greet(name string) string {
	trimmed := strings.TrimSpace(name)
	if trimmed == "" {
		trimmed = "world"
	}
	return fmt.Sprintf("Hello, %s!", trimmed)
}
