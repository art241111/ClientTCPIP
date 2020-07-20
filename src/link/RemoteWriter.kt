package link

import java.io.PrintStream
import java.net.Socket
import java.util.*

class RemoteWriter(private val client: TelnetClient) {
    private lateinit var out: PrintStream
    private val socket = client.getSocket()
    private val commands: Queue<String> = LinkedList<String>()
    private var countCommandsWithCallBack = 0

    init {
        if (socket.isConnected){
            out = PrintStream(socket.getOutputStream())
        }
    }

    fun write(message: String): Boolean {
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

    fun writeDependingStatus(message: String): Boolean {
        if(socket.isConnected) {
            commands.add(message)

            write(commands.poll())
            client.state = State.COMMAND_EXECUTION

            try {
                Thread.sleep(4000L)
            } catch (e: java.lang.Exception) {
            }
            return true
        }
        return false
    }

    fun writeWithCallBack(message: String):String{
        if(socket.isConnected){
            writeDependingStatus(message)

            countCommandsWithCallBack++
            write("Take data$countCommandsWithCallBack")

            return RemoteReader(client).readLine(countCommandsWithCallBack)
        } else{
            // TODO: Migrate to log
            println("Writer: no connection to the robot")
        }
        return "false"
    }
}