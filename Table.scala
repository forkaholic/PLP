import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/*
    Acts like a basic SQL table to a certain extent.
    Missing functionality such as dropping tables because...
    I don't hate myself and that will make things much safer (hopefully)
*/
// filename, num of key values, number of total values
class Table(val file: String, val keys, val numEntries)
{
    private var entries = new ArrayBuffer[Array[Int | Double | String](this.numEntries)]()

    private val types = new Array[String](this.numEntries)

    // Get the types of each key and value
    def getTypes = 
    {
        val openFile = Source.fromFile(this.file)
        val splitLine = openFile.getLines.next()
        for(i <- 0 until this.numEntries)
        {
            types(i) = splitLine(i)
        }
    }

    // Read file into entries buffer
    // Not sure if this is going to be the final implementation,
    // might read from the file whenever a record needs to be found
    def readFile = 
    {
        var i = 0
        val types Array[String] = 
        val openFile = Source.fromFile(this.file)

        for(line <- openFile.getLines)
        {
            val splitLine = line.split(",")

            if (i == 0)
            {
                for(a <- 0 until this.numEntries)
                {
                    types(a) = splitLine(a)
                }
                i = 1
            }
            else
            {
                val row = Array[Int | Double | String](this.numEntries)
                for(a <- 0 until this.numEntries)
                {
                    // Match the type of this column, then convert current value to that type
                    row(a) = getValue(types(a), splitLine(a)
                }
            }
        }
    }

    // Check validity of entire table after reading and before writing.
    // For conflicting entires, most recent (highest) entry is dropped from buffer.
    def checkValidity =
    {

    }

    def writeFile = 
    {

    }

    def getValue(id: String, element: String): Int | Double | String | Array[Int] =
    {
        id match
        {
            case "Int" => element.toInt
            case "Double" => element.toDouble
            case "String" => element
            case "Ints" => this.stringToArray()
        }
    }

    def intArrayToString(elements: Array[Int]): String = 
    {
        val sb = new StringBuilder("[")

        for (ele <- elements)
        {
            sb ++= (ele.toString + ";")
        }

        if (sb.length() > 1)
        {
            sb.deleteCharAt(sb.length()-1)
        }
        
        sb ++= "]"
        sb.toString
    }

    // Convert String value into an Array of Ints
    def stringToIntArray(element: String): Array[Int] =
    {
        element.replaceAll("[\\[\\]]", "").split(";").map(x => x.toInt)
    }

    // Get valid entries based on keys and their locations
    // Note, matches entries based on String values as that is what they are
    // at time of comparison to speed up searches
    def getEntry(keys: Array[String], locations: Array[Int]): Option[Array[Int | Double | String]] = 
    {
        try {
            var i = 0
        }
        catch {
            null
        }
    }

    // End of constructor
}