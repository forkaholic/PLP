import scala.io.Source
import Structures._

package Control
{
    /*
        ControlUnit holds all tables and performs functions 
        necessary to interact with tables such as key/value creation,
        turning key/value into string, etc.

        Note that Table inputs all use [Required,Key,Value] despite
        Required not being the intended type for that generic position.
    */
    class ControlUnit
    {
        val activityTable = new Table[ActivityKey, ActivityValue]("Tables\\Activities.csv")
        val exerciseTable = new Table[ExerciseKey, ExerciseValue]("Tables\\Exercises.csv")
        val userTable = new Table[UserKey, UserValue]("Tables\\Users.csv")
        val activityFactory = new ActivityFactory
        val exerciseFactory = new ExerciseFactory
        val userFactory = new UserFactory

        // Turns all entries into a zipped list ready to be turned into String
        def zipEntries[K <: Key, V <: Value](table: Table[K, V]): Iterable[(K, V)] = table.entries.keys.zip(table.entries.values)

        // Turn each entry into a String, place it into new list
        def stringify[K <: Key, V <: Value](entries: Iterable[(K, V)]): Iterable[String] = entries.map((k, v) => s"$k`$v")

        // Read from the file and occupy the table with appropriate Key and Value
        def readFile[K <: Key, V <: Value](table: Table[K, V]) = 
        {
            val openFile = Source.fromFile(table.file)
            val firstLine = openFile.getLines.next().split(",")
            val kvpType: String = firstLine(0)
            val keyLength = firstLine(1)
            val valueLength = firstLine(2)

            for(line <- openFile.getLines)
            {
                // ^ separates the Key from Value
                val values = line.split("#")
                val key = values(0).split(",")
                val value = values(1).split(",")
                kvpType match 
                {
                    case "Activity" => this.activityTable.addEntry(
                        this.activityFactory.createKey(key), 
                        this.activityFactory.createValue(value) 
                    )
                    case "Exercise" => this.exerciseTable.addEntry(
                        this.exerciseFactory.createKey(key),
                        this.exerciseFactory.createValue(value)
                    )
                    case "User" => this.userTable.addEntry(
                        this.userFactory.createKey(key),
                        this.userFactory.createValue(value)
                    )
                }
            }

            openFile.close()
        }

        // Overarching write function to file
        def writeFile[K <: Key, V <: Value](table: Table[K, V]) =
        {
            // Preps the entries
            val zipped = this.zipEntries(table)

            // Stringify each entry
            val stringed = this.stringify(zipped)
            
            // For now just print
            stringed.foreach(e => println(e))
        }

        this.readFile[ActivityKey, ActivityValue](activityTable)
        // this.writeFile[ActivityKey, ActivityValue](activityTable)
        this.readFile[ExerciseKey, ExerciseValue](exerciseTable)
        // this.writeFile[ExerciseKey, ExerciseValue](exerciseTable)
        this.readFile[UserKey, UserValue](userTable)
        // this.writeFile[UserKey, UserValue](userTable)
    }
}