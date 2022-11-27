package Structures {

    // Order matters with Keys, same values but in different order is a different Key
    class Key(keys: Array[Int | Double | String])
        extends ArrayToS[Int | Double | String](keys)
    {
        def ==(other: Key): Boolean =
        {
            if(this.length == other.length)
            {
                var matches = true
                var i = 0
                while(matches && i < this.length)
                {
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

        def !=(other: Key): Boolean = !(this == other)
    }
}