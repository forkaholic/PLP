package Structures {

    /*
        Would extend Array to make this easier, but it is final
        Generic type as Key and Value use different types
    */
    class ArrayToS[T](values: Array[T]) 
    {
        val length = values.length

        def apply(i: Int): T = this.values(i) 

        // Turn Key into String
        override def toString: String =
        {
            if(this.length == 0)
            {
                ""
            }
            else
            {
                val sb = new StringBuilder()
                this.values.foreach(value => {sb ++= value.toString + ","})
                sb.deleteCharAt(this.length - 1)
                sb.toString
            }

        }

    }
}
