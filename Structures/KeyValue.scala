package Structures 
{
    // Traits are for generics
    trait Required 
    { 
        def validState: Boolean 
        override def toString: String    
    }
    abstract class Key extends Required
    {
        def matches(other: Key): Boolean
    }
    abstract class Value extends Required
    trait Type
    trait Activity extends Type
    trait Exercise extends Type
    trait User extends Type

    /*
        All case classes are super condensed and hard to read because
        they are essentially all the same thing, just with specialized implementations.
        
        I believe that given enough time I could probably generalize these
        case classes even further, but given the time period I have remaining,
        these are more than good enough. 
    */

    // Key case classes
    case class ActivityKey(activityID: Int = -1, userID: Int = -1, date: String = "-1",
        exerciseID: Int = -1, weight: Double = -1.0) extends Key with Activity
    {
        def matches(otherKey: Key): Boolean = 
            otherKey match
            {
                case other: ActivityKey =>  
                    if((this.activityID != -1 && other.activityID != -1 && this.activityID != other.activityID)
                    || (this.userID != -1 && other.userID != -1 && this.userID != other.userID)
                    || (this.date != "-1" && other.date != "-1" && this.date != other.date)
                    || (this.exerciseID != -1 && other.exerciseID != -1 && this.exerciseID != other.exerciseID)
                    || (this.weight != -1.0 && other.weight != -1.0 && this.weight != other.weight)) false
                    else true
                case _ => false
            }
        override def toString: String = s"$activityID,$userID,$date,$exerciseID,$weight" 
        def validState: Boolean = (activityID != -1) && (userID != -1) && (date != "-1")
            && (exerciseID != -1) && (weight != -1.0)
    }
    case class ExerciseKey(exerciseID: Int = -1) extends Key with Exercise
    {
        def matches(otherKey: Key): Boolean = 
            otherKey match
            {
                case other: ExerciseKey =>  
                    if(this.exerciseID != -1 && other.exerciseID != -1 && this.exerciseID != other.exerciseID) false
                    else true
                case _ => false
            }
        override def toString: String = s"$exerciseID" 
        def validState: Boolean = exerciseID != -1
    }
    case class UserKey(userID: Int = -1) extends Key with User
    {
        def matches(otherKey: Key): Boolean = 
            otherKey match
            {
                case other: UserKey =>  
                    if(this.userID != -1 && other.userID != -1 && this.userID != other.userID) false
                    else true
                case _ => false
            }
        override def toString: String = s"$userID"
        def validState: Boolean = userID != -1
    }

    // Value case classes
    case class ActivityValue(reps: Array[Int] = Array[Int]()) extends Value with Activity
    { 
        def matches(other: ActivityValue): Boolean = 
            if(this.reps != Array[Int]() && this.reps != other.reps) false else true
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
    case class ExerciseValue(exercise: String = "-1") extends Value with Exercise
    {
        def matches(other: ExerciseValue): Boolean = 
            if(this.exercise != "-1" && this.exercise != other.exercise) false else true
        override def toString: String = s"$exercise" 
        def validState: Boolean = exercise != "-1"
    }
    case class UserValue(username: String = "-1") extends Value with User
    {
        def matches(other: UserValue): Boolean = 
            if(this.username != "-1" && other.username != "-1" && this.username != other.username) false
            else true
        def matchesAny(others: Iterable[UserValue]): Boolean = others.exists(this.matches(_))
        override def toString: String = s"$username"
        def validState: Boolean = username != "-1"
    }

    class ActivityFactory
    {
        var lastID = -1

        private def toIntArray(ints: String): Array[Int] = ints.replaceAll("[\\[\\]]", "").split(";").map(x => x.toInt)

        def createKey(values: Array[String]): ActivityKey = 
        {
            lastID += 1
            ActivityKey(lastID, values(0).toInt, values(1), values(2).toInt, values(3).toDouble)
        }

        def existingKey(values: Array[String]): ActivityKey =
        {
            lastID = values(0).toInt
            ActivityKey(values(0).toInt, values(1).toInt, values(2), values(3).toInt, values(4).toDouble)
        }

        def searchKey(values: Array[String]): ActivityKey = 
            ActivityKey(values(0).toInt, values(1).toInt, values(2), values(3).toInt, values(4).toDouble)

        def createValue(values: Array[String]): ActivityValue = ActivityValue(this.toIntArray(values(0)))
    }

    class ExerciseFactory
    {
        var lastID = -1

        def createKey(): ExerciseKey = 
        {
            lastID += 1
            ExerciseKey(lastID)
        }

        def existingKey(values: Array[String]): ExerciseKey =
        {
            lastID = values(0).toInt
            ExerciseKey(values(0).toInt)
        }

        def searchKey(values: Array[String]): ExerciseKey = ExerciseKey(values(0).toInt)

        def createValue(values: Array[String]): ExerciseValue = ExerciseValue(values(0))
    }

    class UserFactory
    {
        var lastID = -1

        def createKey(): UserKey =
        {
            lastID += 1
            UserKey(lastID)
        }

        def existingKey(values: Array[String]): UserKey =
        {
            lastID = values(0).toInt
            UserKey(values(0).toInt)
        }

        def searchKey(values: Array[String]): UserKey = UserKey(values(0).toInt)

        def createValue(values: Array[String]): UserValue = UserValue(values(0))
    }
}
