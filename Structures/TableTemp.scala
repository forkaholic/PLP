import scala.collection.mutable.Map

// Traits are for generics
trait Key { def validState: Boolean }
trait Value { def validState: Boolean }
trait Activity
trait Exercise
trait User

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
case class ExerciseValue(exercise: String = "") extends Value, Exercise
{
    override def toString: String = s"$exercise" 
    def validState: Boolean = exercise != ""
}
case class UserValue(username: String = "") extends Value, User
{
    override def toString: String = s"$username"
    def validState: Boolean = username != ""
}


class Table[T, K <: Key with T, V <: Value with T](file: String)
{
    private var entries = scala.collection.mutable.Map[K,V]()

    // Key and Value assembled by ControlUnit
    def addEntry(key: K, value: V) = if(!this.contains(key) 
        && this.validKey(key)) entries += key -> value        

    def contains(key: K): Boolean = this.entries.contains(key)
    
    def validKey(key: K): Boolean = !key.toString.matches("\\bnull\\b")


    // Turns all entries into a zipped list ready to be turned into String
    def zipEntries: Iterable[(K, V)] = this.entries.keys.zip(this.entries.values)

    // Turn each entry into a String, place it into new list
    def stringify(entries: Iterable[(K, V)]): Iterable[String] = entries.map((k, v) => s"$k,$v")

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
        // table.addEntry(ActivityKey(1,"5/15/2001",5,50.0), ActivityValue(Array(10,12,11)))
        // table.writeFile
        println(table.validKey(ActivityKey(1,"5/15/2001",5,50.0)))
        println(table.validKey(ActivityKey(1,"5/15/2001",5)))
    
    }
}