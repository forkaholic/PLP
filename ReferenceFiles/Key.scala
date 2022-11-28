package Structures {

    type KeyType = Int | Double | String

    // Order matters with Keys, same values but in different order is a different Key
    class Key(keys: Array[KeyType])
        extends ArrayToS[KeyType](keys)
    {
        def ==(other: Key): Boolean =
        {
            println(this.length)
            println(other.length)
            if(this.length == other.length)
            {
                var matches = true
                var i = 0
                while(matches && i < this.length)
                {
                    println(this(i).toString + "     " + other(i).toString)
                    if(this(i) != other(i))
                    {
                        matches = false
                    }
                    i += 1
                }
                matches
            }
            else false
        }
    }
}