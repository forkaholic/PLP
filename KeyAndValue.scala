/* 
    Classes are simple to remove complexity for user by storing
    inputs of a key value(s) pair as a union so that Strings and Ints
    can both be used as a key, and values can be String, Int, and Double 
*/
class Key(val key: String | Int)
class Value(val value: String | Int | Double)