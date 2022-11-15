class Simple(var strvar: String)
{

}

class LoopsExample
{
    // Suplemental function to remove repeated checks
    def factorial(i: Int): Int = 
        if(i < 1) 0 else recursiveFactorial(i)

    def recursiveFactorial(i: Int): Int =
        i match
            case 1 => 1
            case _ => i * recursiveFactorial(i-1)

    def whileFactorial(x: Int): Int = 
    {
        var i: Int = x
        var sum: Int = 1
        while(i > 1)
        {
            sum *= i
            i -= 1
        }
        sum
    }

    def forFactorial(x: Int): Int =
    {
        var sum = 1
        for(i <- 1.to(x)) sum *= i
        sum
    }

    def filteredFor(x: Int): Int = 
    {
        var sum = 1
        for(i <- 1 to x if i > 5) // Dont want those awful small numbers getting in the way..
            sum *= i
        sum
    }

    def forEach: Int = 
    {
        val a: Array[Int] = Array(1,2,3,4,5,6)
        var sum = 1
        for(i <- a) sum *= i
        sum
    }

    // Do whiles do not act as you would expect them to... 
    // It is really just a worse while in Scala
    def doWhile =
    {
        var i = 3
        while
            i -= 1
            i > 0
        do print("" + i + " ")


    }

    def mul(x: Int, y: Int): Int = 
    {
        x * y
    }

    def split(s: String): Array[String] =
    {
        Array(
            s.substring(0, s.length() / 2),
            s.substring(s.length() / 2)
        ) 
    }

    /*
     Can't really test to see if Scala is pass by value or reference as the only
     things you can modify are mutable members, reassignment is not possible, so
     assuming it is the same as Java, it is pass by value, where the value is a
     pointer to the value
    */
    def modstr(s: Simple, str: String) = 
        s.strvar = str
    
}

object Loops
{
    def main(args: Array[String]) = 
    {
        var loop: LoopsExample = new LoopsExample

        println(loop.factorial(3))
        println(loop.whileFactorial(4))
        println(loop.forFactorial(5))
        println(loop.filteredFor(6))
        println(loop.forEach)
        println(loop.doWhile)
        println(loop.mul(6,7))
        val strs = loop.split("Hello there")
        println(strs(0))
        println(strs(1))
        var s = new Simple("This string")
        loop.modstr(s, "is not the same")
        println(s.strvar)
    }
}