package Structures {

    // No change to TextArray, just makes it so Key cannot be filled in for Value
    class Value(values: Array[Int | Double | String | Array[Int]])
        extends ArrayToS[Int | Double | String | Array[Int]](values)

}