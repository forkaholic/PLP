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

Programming Languages Project for CS330 at Simmons University
