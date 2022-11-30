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

## Selection Statements
The boolean values in Scala are true and false, meaning you cannot use Ints to represent booleans.

Scala makes use of the if/else if/else structure of control statements. In older versions of Scala, if/then/else was used instead.

There are two ways to delimit code blocks in control statements. You can use no curly braces for single line expressions, with only a space being required for the next part of the control statement (if x else if y else z). The other option is required for multi-line code blocks, but can be used for single line blocks as well where you use curly braces (if{x}else if{y}else{z}). 

As Scala is built on top of Java, it uses short circuiting in the same way. If conditions a is true and b is false, then a || b will be true with a short circuit and a && b will be false without a short circuit.

Scala deals with the dangling else problem by matching each else statement with the most recent available if statement that is in the same scope and immedeatly precedes the else statement (for both single statement code blocks and multi-line blocks). If there isn't an if statement that matches this description, then it is not a valid else statement and will cause compilation to fail. A simple way to avoid thinking about this issue entirely would be to use braces with every if and else statement. 

When using the match case statements, at the end of a given case, the last evaluated statement is returned. This means that it is not possible to evaluate every condition without calling the function again and using a different case.

## Loops and Functions

### Functions
Scala includes multiple types of loops. The while statement is exactly what you expect it to be, and is good for sentinel value loops. The do while loop (actually while do...) is pointless in Scala as the proper functionality was dropped in the transition to Scala 3, and what remains is just a worse while loop that obfuscates how you read code. The for loop in Scala is absolutely fantastic, as in addition to normal functionality of a scoped counter variable, the ability to use filters makes code easier to read as if statements are integrated into the parenthesis of the loop, and allowing multiple ranges simultaneously makes nested loops extremely simple to write if functionality is only needed in the innermost loop.

To create a function in Scala, use the following formal:

"def funcName(par: Type, ...): RetType = { ... }"

To create a single line function without parameters that doesn't return anything use:
(Note that statements such as if/else if/.../else, match case count as single line)

"def funcName = ..."

Using these two examples, any valid function declaration can be assembled.

Functions cannot be placed anywhere, and must reside in a class or object declaration, or inside another function.

Scala supports recursive functions.

Scala allows for multiple parameters of different types to be used. 

To return multiple values of different types, you must return those values in a tuple.

Scala appears to be similar to Java in that it is pass by value (a value that is a reference), but that is not easily tested as all function parameters are immutable, meaning reassignment isn't possible. 

As Scala is built on top of Java, it stores local variables in the stack, and references to object on the stack while placing actual objects in the heap.

Variables are not accessible outisde of their scope in Scala. Loop blocks and function blocks are not treated differently.

Side-effects are possible through the use of mutable object members and states. If reassignment takes place in a function then the change will persist outside of the functions scope. 

An important piece of information that is necessary to write functions in Scala is that the last line evaluated is returned if a return type is provided in the signature. If the last line of a function is not the desired one to return, then simple reference the variable again at the end, and it will be the value that is returned. Another important detail is that functions with a return type can be used as parameters of another function.

## Classes and Inheritance
In Scala you are forced to use objects from the very beginning, as everything must be inside an object, class, or trait. Objects, classes, and traits all following the naming scheme of UpperCamelCase.

As Scala is built off of the Java language and everything in Scala is forwards and backwards compatible with Java, all of the standard functions for objects from Java are available for use in Scala (although they might be under a different name). This includes equals, eq, toString, and more. 

Inheritance in Scala is very similar to Java in that classes and traits (interfaces) can be extended and implemented (with). However, there is are slight changes in how both work, as traits can contain functionality, and classes cannot contain static members or functions. Other than that, classes and traits are the same. You are not able to inherit from multiple classes, but you can implement as many traits as you want.

To override a non-final function previously defined by a super-class, the override keyword can be used before a function is defined to signify that the super function is to be ignored and the new one used in its place. The super method can still be used by using the format "super.functionName".

Although static members and functions are not defined in Scala, the same functionality can be achieved by using companion objects, which are instances of a class that have additional implementations built in that can be accessed by every other instance, regardless of the visibility of the member or function. This can include constants, members, and functions that rely upon either.

It should also be noted that there is a special type of class called the case class, which overrides the base Java functionality of == to instead interpret the arguments for equality instead of the address of the object. For example, a case class called Person with arguments "Sam" and 21, would be equivalent by default to another instance of Person with the same arguments.

https://www.tutorialspoint.com/scala/
https://www.dotnetperls.com/for-scala

Programming Languages Project for CS330 at Simmons University
