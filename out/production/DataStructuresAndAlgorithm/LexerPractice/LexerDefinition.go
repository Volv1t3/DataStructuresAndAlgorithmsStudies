package main

import (
	"fmt"
	"go/token"
)

// ! This needs to have a main function like any other C/C++ relative
func main() {
	/**
	There could be various types of tokens. These tokens can be numeric as in operands, operators, letters or keywords.
	In many cases we have to define our variables and our data types beforehand such that our program does not output a
	negative output like syntax error.
	*/

	result := token.Lookup("for")
	fmt.Printf(string(result))
}
