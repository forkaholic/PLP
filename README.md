# PLP on Scala
## History of Scala
Scala is a programming language created in 2001 by Martin Odersky and was designed with aspects of both functional and object-oriented programming in mind. Scala is mainly used for web design, data processing, and distributed computing. Good sources of information to begin learning Scala can be found in the following sources and more: [My Great Learning's Guide to Scala](https://www.mygreatlearning.com/blog/scala-tutorial/) and Programming in Scala by Martin Odersky.

## Getting Started
### Installation
First, ensure that Java is installed (Java JDK 8 or Open JDK 8).
Next, download and run the correct Scala executable from [here](https://www.scala-lang.org/download/).

### IDE
Turn develop Scala programs, there are a few valid choices. For Java developers, Eclipse and IntelliJ are decent options as there are market addons to extend the base IDEs. However, I chose to work with VS Code as it works with just about any language and has a built in console to quickly compile and execute programs.

### Executing Programs
To run a Scala program, the program must first be compiled. To compile a Scala program, use the command
"scalac SOURCE_FILE.scala".

After compiling, to run a specific program or object with a main function, use
"scala OBJECT_NAME".

### Comments
To write comments in Scala, use either "//" for single line comments or "/* ..... */" for multi-line comments.

## Data Types and Naming Conventions
There are 51 reserved words which cannot be used for variable names in Scala.

Variables should be named in the lower camel case format, with classes, objects, and their constants being named in the upper camel case format. These conventions are not enforced by the compiler and instead are community guidelines. 

Scala is a statically typed language as variables cannot change types after being created.

Scala is a strongly typed language as variables of each type act in ways that are expected to act. 

Scala is an implicitly typed language as you can choose whether or not to declare your variables' types.

Variables that are defined with the val keyword are immutable, while those that are defined with var are mutable. It should be noted that String variables are immutable, even if one is declared a var.

For all number types, the following commonly used operators are available and can be used between other number types: !=, ==, +, -, *, /, %, &, |, ^, <, >, <=, >=, >>, <<

Mixed type operations are allowed for base types where a function has been created specifically for the interaction. An example would be performing an addition between an Int and a Double, where the Int uses either the "+" operator or the ".+()" function: "5 + 8.1" or "5.+(8.1)"

Variable names and types are bound in compile time. By the time the program is being run, the types have already been erased and cannot be inspected as easily. Operators are also bound in compile time as functions are written for their operation.

The only major pitfall encountered in this excersize would be trying to put multiple types of variables in the same Array, as this is not possible.

There are not any built-in complex data types that are commonly used in Scala.

### Selection Statements
The boolean values in Scala are true and false, meaning you cannot use Ints to represent booleans.

Scala makes use of the if/else if/else structure of control statements. In older versions of Scala, if/then/else was used instead.

There are two ways to delimit code blocks in control statements. You can use no curly braces for single line expressions, with only a space being required for the next part of the control statement (if x else if y else z). The other option is required for multi-line code blocks, but can be used for single line blocks as well where you use curly braces (if{x}else if{y}else{z}). 

As Scala is built on top of Java, it uses short circuiting in the same way. If conditions a is true and b is false, then a || b will be true with a short circuit and a && b will be false without a short circuit.

Scala deals with the dangling else problem by matching each else statement with the most recent available if statement that is in the same scope and immedeatly precedes the else statement (for both single statement code blocks and multi-line blocks). If there isn't an if statement that matches this description, then it is not a valid else statement and will cause compilation to fail. A simple way to avoid thinking about this issue entirely would be to use braces with every if and else statement. 

When using the match case statements, at the end of a given case, the last evaluated statement is returned. This means that it is not possible to evaluate every condition without calling the function again and using a different case.

Programming Languages Project for CS330 at Simmons University
