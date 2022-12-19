// Singleton object (only one instance allowed at a time, no point in class)
object HelloWorld
{
	// main method is a required entry point of any Scala program,
	// must have 1 main method somewhere
	def main(args: Array[String]) =
	{
		println(s"Hello world!")
	}
}

