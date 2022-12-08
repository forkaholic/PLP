import scala.collection.mutable.LinkedHashMap
import Structures._

package Structures 
{
    /*
        Second implementation of the Table class, one that
        relies upon generics for Keys and Values instead of
        really clunky generalized types like "Int | Double | String".

        Original Table will be kept in a separate folder for
        later reference
    */
    class Table[T, K <: Key with T, V <: Value with T](val file: String, val kvpType: String)
    {
        var entries = scala.collection.mutable.LinkedHashMap[K,V]()

        def apply(key: K): V = this.entries(key)

        def getAll(key: K): Iterable[V] = 
            this.entries.keys.filter(key.matches(_)).map(x => this.entries(x))


        // Key and Value assembled by ControlUnit
        def addEntry(key: K, value: V): Boolean = if(!this.contains(key) && this.validElement(key)
            && this.validElement(value)) { entries += key -> value; true } else false       

        // Attempt to update entry, if bad value, put original back
        def updateEntry(key: K, value: V) = if(this.contains(key)) 
            { val orig = this(key); this.entries -= key; if(!this.addEntry(key,value)) this.addEntry(key,orig) }

        def removeEntry(key: K) = if(this.contains(key)) this.entries -= key

        // Checks if entries contains a given Key
        def contains(key: K): Boolean = this.entries.contains(key)
        
        /*
            Checks if the element is in a valid state for an entry.
            This method does not apply to wildcard searches.
        */
        private def validElement(element: K | V): Boolean = element.validState
    }
}
