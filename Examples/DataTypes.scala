import scala.collection.mutable.HashMap

class DataTypeExamples(var constString: String)
{
    val ConstString: String = constString

    // Function names are in lowerCamel
    def printStr = 
    {
        println(this.ConstString)
    }

    def basicTypes = 
    {
        // val is constant, var is variable

        // Auto typing
        // intOne defaults to an Int
        val intOne = 44

        // Explicitly Int
        var intTwo: Int = 67

        var byte: Byte = 127 // Max signed byte value -128 min
        val double: Double = 6.1 
        
        // double: Float = 1.1 // Fails to compile, expects double to be of float type to redefine

        // intOne = 1 // Fails to compile
        intTwo = 250        

        var string: String = "I am immutable even though I am a var"
    }

    def conversionExample = 
    {
        val int: Int = 44
        var byte: Byte = 127
        var double: Double = 6.1

        println(s"Converting Double to Int: ${double.toInt}")

        println(s"Widenening byte to int: $byte + $int = ${byte .+(int)}")
        println(s"Widenening int to double: $int + $double = ${int + double}")        
    }

    def arrayExample = 
    {
        var array: Array[Int] = Array(0,1,2,3,4,5,6,7)
        println(s"Getting second element: ${array(1)}")
        array.update(1,7)
        println(s"Getting second element: ${array(1)}")
        array(1) = 1
        println(s"Getting second element: ${array(1)}")

        println(s"Trying to force Float to convert into an Int")
        var float: Float = 7.1
        // array(0) = float // Fails to compile
        array(0) = float.toInt
        // println(s"Getting first element: ${array(0)}")
        println("Getting first element: " + array(0)) // Both ways work
        println(s"Need to use __.to___ to convert types in Scala")
    }

    // If () is included in signature, they are required to call function
    def runExample() = 
    {
        this.basicTypes

        this.conversionExample

        this.arrayExample
    }

    
}

// Class and object names are in UpperCamel
object DataTypes
{
    def main(args: Array[String]) = 
    {
        var ex1: DataTypeExamples = new DataTypeExamples("This is my string")
        ex1.printStr

        var ex2: DataTypeExamples = new DataTypeExamples("And this is mine")
        ex2.printStr

        ex1.runExample()
    }
}