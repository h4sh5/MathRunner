# MathRunner
a simple calculator with compiler/interpreter-like structure in java

I built this little project to practise writing an interpreter from scratch.

IntelliJ is recommended for building this project, but not neccessary.

When using it, modify the configuations so that you can specify the .math file to run e.g. `examples/2.math`

Alternatively use the command line after its built. cd into the directory containing MathRunner.class, and run `java MathRunner path/to/file.math`

## Architecture

There are two parts to this:
Lexer - scans the math code (e.g. `1 + 2 * 3`) and turns it into tokens
Parser - takes the stream of tokens and returns the integer value.

AST (Abstract Syntax Tree) has not been built in the parsing phase but might be added in the future

