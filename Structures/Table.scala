import scala.collection.mutable.Map

// Traits are for generics
trait Required { def validState: Boolean }
trait Key extends Required
trait Value extends Required
trait Activity
trait Exercise
trait User

/*
    All case classes are super condensed and hard to read because
    they are essentially all the same thing, just with specialized implementations.
    
    I believe that given enough time I could probably generalize these
    case classes even further, but given the time period I have remaining,
    these are more than good enough. 
*/

// Key case classes
case class ActivityKey(userID: Int = -1, date: String = "//",
    exerciseID: Int = -1, weight: Double = -1.0) extends Key, Activity
{ 
    override def toString: String = s"$userID,$date,$exerciseID,$weight" 
    def validState: Boolean = (userID != -1) && (date != "//")
        && (exerciseID != -1) && (weight != -1.0)
}
case class ExerciseKey(exerciseID: Int = -1) extends Key, Exercise
{
    override def toString: String = s"$exerciseID" 
    def validState: Boolean = exerciseID != -1
}
case class UserKey(userID: Int = -1) extends Key, User
{
    override def toString: String = s"$userID"
    def validState: Boolean = userID != -1
}

// Value case classes
case class ActivityValue(reps: Array[Int] = Array[Int]()) extends Value, Activity
{ 
    override def toString: String =
    {
        val sb = new StringBuilder("[")
        reps.foreach(value => sb ++= (value.toString + ';'))
        sb.deleteCharAt(sb.length - 1)
        sb += ']'
        sb.toString 
    } 
    def validState: Boolean = reps != Array[Int]()
}
case class ExerciseValue(exercise: String = "-1") extends Value, Exercise
{
    override def toString: String = s"$exercise" 
    def validState: Boolean = exercise != "-1"
}
case class UserValue(username: String = "-1") extends Value, User
{
    override def toString: String = s"$username"
    def validState: Boolean = username != "-1"
}

/*
    Second implementation of the Table class, one that
    relies upon generics for Keys and Values instead of
    really clunky generalized types like "Int | Double | String".

    Original Table will be kept in a separate folder for
    later reference
*/
class Table[T, K <: Key with T, V <: Value with T](file: String)
{
    private var entries = scala.collection.mutable.Map[K,V]()

    // Key and Value assembled by ControlUnit
    def addEntry(key: K, value: V) = if(!this.contains(key) && this.validElement(key)
        && this.validElement(value)) entries += key -> value        

    def contains(key: K): Boolean = this.entries.contains(key)
    
    def validElement(element: K | V): Boolean = element.validState

    // Turns all entries into a zipped list ready to be turned into String
    def zipEntries: Iterable[(K, V)] = this.entries.keys.zip(this.entries.values)

    // Turn each entry into a String, place it into new list
    def stringify(entries: Iterable[(K, V)]): Iterable[String] = entries.map((k, v) => s"$k,$v")

    def readFile = 
    {
        // Open file, begin reading in lines
        // add ^ into csv as separator between Key and Value
        // K(...? possibly String*???)
    }

    // Overarching write function to file
    def writeFile =
    {
        // Preps the entries
        val zipped = this.zipEntries

        // Stringify each entry
        val stringed = this.stringify(zipped)
        
        // For now just print
        stringed.foreach(e => println(e))
    }
}

object tempmain
{
    def main(args: Array[String]): Unit = 
    {
        val table = new Table[Activity, ActivityKey, ActivityValue]("Hello")
        table.addEntry(ActivityKey(1,"5/15/2001",5,50.0), ActivityValue(Array(10,12,11)))
        table.writeFile
        // val key1 = ActivityKey(1,"5/15/2001",5,50.0)
        // val key2 = ActivityKey(1,"5/15/2001",5)


        // println(table.validKey(key1))
        // println(table.validKey(key2))
    }
}
