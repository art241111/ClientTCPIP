package link

import protocols.Sender
import java.io.PrintStream
import java.net.Socket

class RemoteWriter(private val socket: Socket): Sender {
    private lateinit var out: PrintStream

    init {
        if (socket.isConnected){
            out = PrintStream(socket.getOutputStream())
        }
    }

    //this method writes to server, but waits no prompt.
    override fun write(message: String): Boolean {
        if(socket.isConnected){
            try {
                out.println(message)
                out.flush()
                return true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else{
            // TODO: Migrate to log
            println("Writer: no connection to the robot")
        }

        return false
    }
}