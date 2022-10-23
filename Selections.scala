class SelectionExamples
{
    // These Strings can be either functions that return the str itself, a var, or a val
    def one: String = "You chose one. Surprising."
    var two: String = "Two? Really?"
    val other: String = "That's right, good choice."

    // You can choose to omit the {} for functions that use single line selections (if/else if/else, x match case....)
    def matchInt(i: Int): String = 
        i match
            case 1 => this.one
            case 2 => this.two
            case _ => this.other
    
    // The this keyword and {} can also be omitted
    def ifElseInt(i: Int): String = 
        if(i == 1) one 
        else if(i == 2)
        {
            two  
        }  
        else other

    def shortCircuitEx =
    {
        if(true || this.boom)
        {
            println("The world has not gone boom")
        }
        if(true && this.boom)
        {
            println("The world has not gone boom")
        }
    }

    def boom: Boolean = 
    {
        println("The world is going boom")
        false
    }

}


object Selections
{
    def main(args: Array[String]) =
    {
        var select: SelectionExamples = new SelectionExamples

        println("Enter either 1 or 2 (using match):")
        val funcNum1 = scala.io.StdIn.readInt()

        println(select.matchInt(funcNum1))


        println("Enter either 1 or 2 (using if/else):")
        val funcNum2 = scala.io.StdIn.readInt()

        println(select.ifElseInt(funcNum2))

        select.shortCircuitEx

    }


}