// var, public and mutable, val, public and immutable, nothing, private and immutable
class Person(name: String, age: Int)
{
    def smack: Unit = println(s"*smacked $name* \"Ouch\"")

    def hi: Unit = println(s"Hi, I'm $name and I'm $age years old.")
}

class Student(name: String, age: Int, var degree: String) extends Person(name, age)
{
    override def hi: Unit = {super.hi; println(s"Oh, I also have a $degree degree.")}

    def study = println("*loud sobbing can be heard nearby*")
}

object Classes
{
    def main(args: Array[String]): Unit = 
    {
        val someoneElseIGuess = new Person("Nobody", 2)
        someoneElseIGuess.hi
        someoneElseIGuess.smack
        
        val me = new Student("Devin", 21, "Bachelors")
        me.hi
        me.degree = "Masters"
        me.hi
        me.study
        me.study
        me.study
        me.study
        me.study
        me.study
        me.study
        me.study
        me.study
    }
}
