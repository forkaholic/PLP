class Server
{
    def start = 
    {
        // Instantiate ControlUnit objects
        
    }
}

object ServerMain
{

    def main(args: Array[String]) = 
    {
        val server: Server = new Server(args[1])
        server.start
    }

}