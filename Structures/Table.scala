import scala.collection.mutable.Map
import Structures._
import Control.ControlUnit

package Structures 
{
    /*
        Second implementation of the Table class, one that
        relies upon generics for Keys and Values instead of
        really clunky generalized types like "Int | Double | String".

        Original Table will be kept in a separate folder for
        later reference
    */
    class Table[T, K <: Key with T, V <: Value with T](val file: String)
    {
        var entries = scala.collection.mutable.Map[K,V]()

        def apply(key: K): Iterable[V] = 
            this.entries.keys.filter(key.matches(_)).map(x => this.entries(x))

        // Key and Value assembled by ControlUnit
        def addEntry(key: K, value: V) = if(!this.contains(key) && this.validElement(key)
            && this.validElement(value)) entries += key -> value        

        // Checks if entries contains a given Key
        def contains(key: K): Boolean = this.entries.contains(key)
        
        /*
            Checks if the element is in a valid state for an entry.
            This method does not apply to wildcard searches.
        */
        private def validElement(element: K | V): Boolean = element.validState
    }
}
