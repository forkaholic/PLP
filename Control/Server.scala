import Control.ControlUnit
import scala.io.StdIn.readLine

package Control
{
    class Server
    {
        private var running = false
        private var controller: ControlUnit = null

        def start = if(!running) {running = true; controller = new ControlUnit; controller.readFiles}
        def stop = if(running) {running = false; controller.writeFiles; controller = null}

        def readInput = 
        {
            this.start
            // println(controller.clientSearch("Activity", Array("-1","-1","-1","-1","-1")))
            while(running)
            {
                try
                {
                    println("create, read, update, delete, save, or close, keytype, arguments")
                    val split = readLine().split(",")
                    split(0) match
                    {
                        case "create" => 
                        {
                            val elements = Array.ofDim[String](split.size - 2)
                            for(i <- 2 until split.size) elements(i - 2) = split(i)
                            controller.clientAdd(split(1), elements)
                        }
                        case "read" =>
                        {
                            val elements = Array.ofDim[String](split.size - 2)
                            for(i <- 2 until split.size) elements(i - 2) = split(i)
                            println(controller.clientSearch(split(1), elements))
                        }
                        case "update" =>
                        {
                            val elements = Array.ofDim[String](split.size - 2)
                            for(i <- 2 until split.size) elements(i - 2) = split(i)
                            controller.clientUpdate(split(1), elements)                            
                        }
                        case "delete" =>
                        {
                            val elements = Array.ofDim[String](split.size - 2)
                            for(i <- 2 until split.size) elements(i - 2) = split(i)
                            controller.clientDelete(split(1), elements)     
                        }
                        case "save" => controller.writeFiles
                        case "close" => {controller.writeFiles; this.stop}
                    } 
                }
                catch
                {
                    case e => println("Incorrect format")
                }

            }
        
        }
    }
}
