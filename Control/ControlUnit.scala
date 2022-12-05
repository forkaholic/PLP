import scala.io.Source
import java.io._
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
        val activityTable = new Table[Activity, ActivityKey, ActivityValue]("Tables\\Activities.csv", "Activity")
        val exerciseTable = new Table[Exercise, ExerciseKey, ExerciseValue]("Tables\\Exercises.csv", "Exercise")
        val userTable = new Table[User, UserKey, UserValue]("Tables\\Users.csv", "User")
        val activityFactory = new ActivityFactory
        val exerciseFactory = new ExerciseFactory
        val userFactory = new UserFactory

        // Turns all entries into a zipped list ready to be turned into String
        def zipEntries[T, K <: Key with T, V <: Value with T](table: Table[T, K, V]): Iterable[(K, V)] = table.entries.keys.zip(table.entries.values)

        // Turn each entry into a String, place it into new list
        def stringify[T, K <: Key with T, V <: Value with T](entries: Iterable[(K, V)]): Iterable[String] = entries.map((k, v) => s"$k#$v")

        // Add Activity kvp to Activity Table
        def addActivity(key: Array[String], value: Array[String]) = 
            activityTable.addEntry(activityFactory.createKey(key), activityFactory.createValue(value))
            
        // Add existing Activity kvp to Activity Table
        def addExistingActivity(key: Array[String], value: Array[String]) = 
            activityTable.addEntry(activityFactory.existingKey(key), activityFactory.createValue(value))

        // Add Exercise kvp to Exercise Table
        def addExercise(value: Array[String]) = 
            exerciseTable.addEntry(exerciseFactory.createKey(), exerciseFactory.createValue(value))

        // Add existing Exercise kvp to Exercise Table
        def addExistingExercise(key: Array[String], value: Array[String]) = 
            exerciseTable.addEntry(exerciseFactory.existingKey(key), exerciseFactory.createValue(value))

        // Add User kvp to User Table
        def addUser(key: Array[String]) =
            if(userTable.entries.keys.size == 0 || !UserKey(-1, key(0)).matchesAny(userTable.entries.keys))
                userTable.addEntry(userFactory.createKey(key), userFactory.createValue())

        // Add existing User kvp to User Table
        def addExistingUser(key: Array[String]) =
            if(userTable.entries.keys.size == 0 || !UserKey(-1, key(1)).matchesAny(userTable.entries.keys))
                userTable.addEntry(userFactory.existingKey(key), userFactory.createValue())

        // Read from the file and occupy the table with appropriate Key and Value
        def readFile[T, K <: Key with T, V <: Value with T](table: Table[T, K, V]) = 
        {
            val openFile = Source.fromFile(table.file)
            for(line <- openFile.getLines)
            {
                // ^ separates the Key from Value
                val values = line.split("#")
                val key = values(0).split(",")

                table.kvpType match 
                {
                    case "Activity" => this.addExistingActivity(key,values(1).split(","))
                    case "Exercise" => this.addExistingExercise(key,values(1).split(","))
                    case "User" => this.addExistingUser(key)
                }
            }

            openFile.close()
        }

        // Overarching write function to file
        def writeFile[T, K <: Key with T, V <: Value with T](table: Table[T, K, V]) =
        {
            // Preps the entries
            val zipped = this.zipEntries(table)

            // Stringify each entry
            val stringed = this.stringify(zipped)
            
            // Write to file
            val fileWriter = new FileWriter(new File(table.file))
            stringed.foreach(e => fileWriter.write(s"$e\n"))
            fileWriter.close()
        }

        this.readFile[Activity, ActivityKey, ActivityValue](activityTable)
        this.readFile[Exercise, ExerciseKey, ExerciseValue](exerciseTable)
        this.readFile[User, UserKey, UserValue](userTable)

        this.writeFile[Activity, ActivityKey, ActivityValue](activityTable)
        this.writeFile[Exercise, ExerciseKey, ExerciseValue](exerciseTable)
        this.writeFile[User, UserKey, UserValue](userTable)
    }
}
