package Structures
{
    class IntArray(values: Array[Int])
    {

        val length = values.length

        def apply(i: Int): Int = this.values(i)

        override def toString: String = 
        {
            val sb = new StringBuilder('[')
            this.values.foreach(value => sb ++= (value.toString + ','))
            sb.deleteCharAt(sb.length - 1)
            sb += ']'
            sb.toString
        }
    }
}