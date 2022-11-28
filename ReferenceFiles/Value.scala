package Structures {

    type ValueType = Int | Double | String | IntArray

    // No change to TextArray, just makes it so Key cannot be filled in for Value
    class Value(values: Array[ValueType])
        extends ArrayToS[ValueType](values)

}