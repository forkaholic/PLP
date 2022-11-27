import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import Structures.Key
import Structures.Value

package Structures {

    // Used in the factory to return a tuple which avoids a type mismatch
    class KVPTuple(val key: Key, val value: Value)

    /*
        Acts like a basic SQL table to a certain extent.
        Missing functionality such as dropping tables because...
        I don't hate myself and that will make things much safer (hopefully)
    */
    class Table(file: String, numKeys: Int, numValues: Int) 
    {
        private var numEntries = this.numKeys + this.numValues

        private var entries: Map[Key,Value] = Map()
        private val types = new Array[String](this.numEntries)

        def apply(key: Key): Value = if(this.keyExists(key)) this.entries(key) else null

        // Get the types of each key and value
        def getTypes = 
        {
            val openFile = Source.fromFile(this.file)
            val splitLine = openFile.getLines.next().split(",")
            openFile.close()
            for(i <- 0 until this.numEntries)
            {
                types(i) = splitLine(i).split("#")(0)
            }
        }

        // Read file into entries buffer
        // Not sure if this is going to be the final implementation,
        // might read from the file whenever a record needs to be found
        def readFile = 
        {       
            println("started")
            var i = 0
            val openFile = Source.fromFile(this.file)
            openFile.getLines.next()
            for(line <- openFile.getLines)
            {
                val splitLine = line.split(",")

                // if (i == 0){i = 1; println("i place")} // Make sure types are skipped
                // else
                // {
                    val first = Array[Int | Double | String](this.numKeys)
                    val second = Array[Int | Double | String | Array[Int]](this.numValues)
                    var ok = true
                    var a = 0
                    while(a < this.numEntries && ok)
                    {
                        print(a)
                        println(first.length)
                        // Match the type of this column, then convert current value to that type
                        // Seperated into two blocks as there is type mismatch between Key and Value,
                        // could use generic here when creating table, but it gets to be a headache quickly...
                        // Regardless, look into matter later so that tables can be generalized
                        if(a < this.numKeys)
                        {
                            val value = getTypeFromString(types(a), splitLine(a))
                            value match
                            {
                                case null => ok = false
                                case _ => first(a) = value 
                            }
                        }
                        else
                        {
                            val value = getTypeFromStringExtended(types(a), splitLine(a))
                            value match
                            {
                                case null => ok = false
                                case _ => second(a - this.numKeys) = value
                            }
                        }

                        a += 1
                    }

                    println(ok)
                    if(ok) 
                    {
                        val kvp = this.keyValueFactory(first, second)
                        this.entries + (kvp.key -> kvp.value)
                    }
                // }
            }
            openFile.close()
        }

        // Check key and value to determine if the types
        // match and that key is not in entries.
        // Only used when trying to create a new entry
        def checkNewEntry(key: Key, value: Value): Boolean = 
            if(this.keyValid(key) && !this.keyExists(key) && this.valuesValid(value)) 
                true else false

        // Check if the provided "array" is valid
        private def validArray(array: Key | Value, offset: Int): Boolean = 
        {
            var i = 0
            var ok = true
            while(i < array.length && ok)
            {
                var element = array(i)
                if(element != this.getTypeFromString(this.types(i + offset), element.toString))
                {
                    ok = false
                }
                i += 1
            }
            ok
        }

        private def keyValueFactory(first: Array[Int | Double | String],
            second: Array[Int | Double | String | Array[Int]]): KVPTuple = {
                first.foreach(e => println(e))
                second.foreach(e => println(e))
                
                new KVPTuple(new Key(first),
                    new Value(second))

            }

        // Check key against types to make sure the types match
        def keyValid(key: Key): Boolean = 
        {
            validArray(key, 0)    
        }

        def valuesValid(value: Value): Boolean = 
        {
            validArray(value, this.types.length - value.length)
        }

        // Checks if key in entries
        def keyExists(key: Key): Boolean = 
        {
            entries.contains(key)
        }

        // // Check validity of entire table after reading and before writing.
        // // For conflicting entires, most recent (highest) entry is dropped from buffer.
        // def checkValidity =
        // {

        // }

        // def writeFile = 
        // {

        // }

        def getTypeFromStringExtended(id: String, element: String): Int | Double | String | Array[Int] =
        {
            try
            {
                id match
                {
                    case "Ints" => this.stringToIntArray(element)
                    case _ => this.getTypeFromString(id, element)
                }
            }
            catch
            {
                case e: Exception => {println("extended");null} // Type doesn't match, couldn't convert
            }
        }

        // Convert element into the type indicated by the id
        def getTypeFromString(id: String, element: String): Int | Double | String =
        {
            try 
            {
                id match
                {
                    case "Int" => element.toInt
                    case "Double" => element.toDouble
                    case "String" => element
                }
            }
            catch
            {
                case e: Exception => {println("normal");null} // Type doesn't match, couldn't convert
            }
        }

        // Convert elements from Array[Int] to String
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

        // Convert element from String to Array[Int]
        def stringToIntArray(element: String): Array[Int] =
        {
            element.replaceAll("[\\[\\]]", "").split(";").map(x => x.toInt)
        }

        // Get valid entries based on keys and their locations
        // Note, matches entries based on String values as that is what they are
        // at time of comparison to speed up searches
        // def getEntries(keys: Array[String], locations: Array[Int]): Array[Array[Int | Double | String]] = 
        // {
            
        // }

        this.getTypes
        
        this.types.foreach(e => println(e))
        
        println()

        this.readFile

        println(this(new Key(Array(1,"5/15/2001",21))))

        // End of constructor
    }
}