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

        private def getEntries(key: Key): String =
        {
            val sb = new StringBuilder()
            key match
            {
                case k: ActivityKey => 
                    {
                        val kvps = activityTable.entries.filter(x => x._1.matches(key.asInstanceOf[ActivityKey]))
                        val keys = kvps.keys.toArray
                        val userIdsFromKeys = keys.map(x => this.userTable(UserKey(x.userID)))
                        val exercisesFromKeys = keys.map(x => this.exerciseTable(ExerciseKey(x.exerciseID)))
                        val values = kvps.values.toArray
                        for(i <- 0 until kvps.size) sb ++= (keys(i).activityID + "," + userIdsFromKeys(i) + ","
                            + keys(i).date + "," + exercisesFromKeys(i) + "," + keys(i).weight + "," + values(i).toString + "\n")
                    }
                case k: ExerciseKey =>
                    {
                        val kvps = this.exerciseTable.entries.filter(x => x._1.matches(key.asInstanceOf[ExerciseKey]))
                        val keys = kvps.keys.toArray
                        val values = kvps.values.toArray
                        for(i <- 0 until kvps.size) sb ++= (keys(i).toString + "," + values(i).toString + "\n")   
                    }
                case k: UserKey => 
                    {
                        val kvps = this.userTable.entries.filter(x => x._1.matches(key.asInstanceOf[UserKey]))
                        val keys = kvps.keys.toArray
                        val values = kvps.values.toArray
                        for(i <- 0 until kvps.size) sb ++= (keys(i).toString + "," + values(i).toString + "\n")
                    }
            }
            sb.toString.trim
        }

        // Allow client to get KVPS
        def clientSearch(keyType: String, args: Array[String]): String =
            keyType match
            {
                case k: "Activity" => this.getEntries(this.activityFactory.searchKey(args))
                case k: "Exercise" => this.getEntries(this.exerciseFactory.searchKey(args))
                case k: "User" => this.getEntries(this.userFactory.searchKey(args))
                case _ => ""
            }

        // Add Activity kvp to Activity Table
        def addActivity(key: Array[String], value: Array[String]) = 
            activityTable.addEntry(activityFactory.createKey(key), activityFactory.createValue(value))

        // Add existing Activity kvp to Activity Table
        private def addExistingActivity(key: Array[String], value: Array[String]) = 
            activityTable.addEntry(activityFactory.existingKey(key), activityFactory.createValue(value))

        // Add Exercise kvp to Exercise Table
        def addExercise(value: Array[String]) = 
            exerciseTable.addEntry(exerciseFactory.createKey(), exerciseFactory.createValue(value))

        // Add existing Exercise kvp to Exercise Table
        private def addExistingExercise(key: Array[String], value: Array[String]) = 
            exerciseTable.addEntry(exerciseFactory.existingKey(key), exerciseFactory.createValue(value))

        // Add User kvp to User Table
        def addUser(value: Array[String]) =
            if(userTable.entries.keys.size == 0 || !UserValue(value(0)).matchesAny(userTable.entries.values))
                userTable.addEntry(userFactory.createKey(), userFactory.createValue(value))

        // Add existing User kvp to User Table
        private def addExistingUser(key: Array[String], value: Array[String]) =
            if(userTable.entries.keys.size == 0 || !UserValue(value(0)).matchesAny(userTable.entries.values))
                userTable.addEntry(userFactory.existingKey(key), userFactory.createValue(value))

        // Read from the file and occupy the table with appropriate Key and Value
        def readFile[T, K <: Key with T, V <: Value with T](table: Table[T, K, V]) = 
        {
            val openFile = Source.fromFile(table.file)
            for(line <- openFile.getLines)
            {
                // ^ separates the Key from Value
                val values = line.split("#")
                val key = values(0).split(",")
                val value = values(1).split(",")

                table.kvpType match 
                {
                    case "Activity" => this.addExistingActivity(key,value)
                    case "Exercise" => this.addExistingExercise(key,value)
                    case "User" => this.addExistingUser(key,value)
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


        // Read in Tables from files
        def readFiles = 
        {
            this.readFile[Activity, ActivityKey, ActivityValue](activityTable)
            this.readFile[Exercise, ExerciseKey, ExerciseValue](exerciseTable)
            this.readFile[User, UserKey, UserValue](userTable)
        }

        // Write out Tables to files
        def writeFiles = 
        {
            this.writeFile[Activity, ActivityKey, ActivityValue](activityTable)
            this.writeFile[Exercise, ExerciseKey, ExerciseValue](exerciseTable)
            this.writeFile[User, UserKey, UserValue](userTable)
        }
    }
}
