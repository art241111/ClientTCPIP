package link

import java.io.PrintStream
import java.net.Socket
import java.util.*

class RemoteWriter(private val socket: Socket) {
    private lateinit var out: PrintStream
    private val commands: Queue<String> = LinkedList<String>()

    init {
        if (socket.isConnected){
            out = PrintStream(socket.getOutputStream())
        }
    }

    fun write(message: String): Boolean {
        if(socket.isConnected){
            commands.add(message)

            try {
                out.println(commands.poll())
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