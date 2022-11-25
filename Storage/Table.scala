package Storage {
    import Key
    import scala.collection.mutable.ArrayBuffer
    import scala.io.Source

    /*
        Acts like a basic SQL table to a certain extent.
        Missing functionality such as dropping tables because...
        I don't hate myself and that will make things much safer (hopefully)
    */
    class Table(val file: String, val numEntries: Int)
    {
        private var entries: Map[Key,Value] = Map()

        private val types = new Array[String](this.numEntries)

        // Get the types of each key and value
        def getTypes = 
        {
            val openFile = Source.fromFile(this.file)
            val splitLine = openFile.getLines.next().split(",")
            for(i <- 0 until this.numEntries)
            {
                types(i) = splitLine(i).split("#")(0)
            }
            openFile.close()
        }

        // Read file into entries buffer
        // Not sure if this is going to be the final implementation,
        // might read from the file whenever a record needs to be found
        def readFile = 
        {       
            val openFile = Source.fromFile(this.file)
            openFile.getLines.next()
            for(line <- openFile.getLines)
            {
                val splitLine = line.split(",")

                if (i == 0)
                {
                    i = 1
                }
                else
                {
                    val row = Array[Int | Double | String | Array[Int]](this.numEntries)
                    for(a <- 0 until this.numEntries)
                    {
                        // Match the type of this column, then convert current value to that type
                        row(a) = getTypeFromString(types(a), splitLine(a))
                    }
                    // Add to entries here
                }
            }
            openFile.close()
        }

        // Check key and value to determine if the types
        // match and that key is not in entries.
        // Only used when trying to create a new entry
        def checkNewEntry(key: Key, value: Value): Boolean = 
        {
            if(this.keyValid(key) && !this.keyExists(key) && this.valuesValid(value))
        }

        // Check key against types to make sure the types match
        def keyValid(key: Key): Boolean = 
        {
            var i = 0
            var ok = true
            while(i < key.length && ok)
            {
                var element = key.keys(i)
                if(element != this.getTypeFromString(this.types(i), element.toString))
                {
                    ok = false
                }
                i += 1
            }
            ok
        }

        // Checks if key in entries
        def keyExists(key: Key): Boolean = 
        {
            entries.contains(key)
        }

        def valuesValid(value: Value): Boolean = 
        {

        }

        // // Check validity of entire table after reading and before writing.
        // // For conflicting entires, most recent (highest) entry is dropped from buffer.
        // def checkValidity =
        // {

        // }

        def writeFile = 
        {

        }

        // Convert element into the type indicated by the id
        def getTypeFromString(id: String, element: String): Int | Double | String | Array[Int] | Unit =
        {
            try 
            {
                id match
                {
                    case "Int" => element.toInt
                    case "Double" => element.toDouble
                    case "String" => element
                    // case "Ints" => this.stringToIntArray(element)
                }
            }
            catch
            {
                case e: Exception => {return /* Type doesn't match, return Unit value*/}
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
        this.readFile
        // End of constructor
    }
}