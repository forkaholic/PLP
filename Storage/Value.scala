package Storage {

    class Value(values: Array[Int | Double | String])
    {
        val length = values.length

        def apply(i: Int): Int | Double | String = this.values(i) 

        // Turn Key into String
        override def toString: String =
        {
            val sb = new StringBuilder()
            for(value <- this.values)
            {
                sb ++= (key.toString + ",")
            }
            if(this.length > 0)
            sb.deleteCharAt(this.length - 1)
            sb.toString
        }

    }
}