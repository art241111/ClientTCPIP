package link

import java.io.PrintStream
import java.net.Socket
import java.util.*

class RemoteWriter(private val socket: Socket) {
    private lateinit var out: PrintStream
    private val commands: Queue<String> = LinkedList<String>()
    private var countCommandsWithCallBack = 0

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

                try {
                    Thread.sleep(4000L)
                } catch (e: java.lang.Exception) {
                }

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

    fun writeWithCallBack(message: String):String{
        if(socket.isConnected){
            commands.add(message)

            try {
                out.println(commands.poll())
                out.flush()

                try {
                    Thread.sleep(1000L)
                } catch (e: java.lang.Exception) {
                }

                countCommandsWithCallBack++
                write("Take data$countCommandsWithCallBack")
                return RemoteReader(socket).readLine(countCommandsWithCallBack)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else{
            // TODO: Migrate to log
            println("Writer: no connection to the robot")
        }
        return "false"
    }
}