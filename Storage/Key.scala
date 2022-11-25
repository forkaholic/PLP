package Storage {

    // Order matters with Keys, same values but in different order is a different Key
    class Key(keys: Array[Int | Double | String])
    {
        val length = keys.length

        def apply(i: Int): Int | Double | String = this.keys(i)

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

        // Turn Key into String
        override def toString: String =
        {
            val sb = new StringBuilder()
            for(key <- keys)
            {
                sb ++= (key.toString + ",")
            }
            if(this.length > 0) sb.deleteCharAt(sb.length - 1)
            sb.toString
        }

    }
}