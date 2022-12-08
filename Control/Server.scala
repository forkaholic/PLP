import Control.ControlUnit


package Control
{
    class Server
    {
        private var running = false
        private var controller: ControlUnit = null

        def start = if(!running) {controller = new ControlUnit; controller.readFiles}
        def stop = if(running) {controller.writeFiles; controller = null}



        def mainTest = 
        {
            start
            println(controller.clientSearch("Activity", Array("-1","-1","-1","-1","-1")))
        }
    }
}
