package link

import kawasakiRobots.commands.service.ServiceCommand
import java.io.PrintStream
import java.util.*
import kotlin.concurrent.thread

class RemoteWriter(private val client: TelnetClient) {
    private lateinit var out: PrintStream
    private val socket = client.getSocket()
    private val commands: Queue<String> = LinkedList<String>()
    private var countCommandsWithCallBack = 0
    private var connection = false

    init {
        if (socket.isConnected){
            out = PrintStream(socket.getOutputStream())
            connection = true
            sendCommandsFromQueue()
        }
    }

    fun write(message: String): Boolean {
        if(connection){
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
        if(connection) {
            commands.add(message)

            try {
                Thread.sleep(1000L)
            } catch (e: java.lang.Exception) {
            }
            return true
        }
        return false
    }

    private fun sendCommandsFromQueue(){
        thread {
            while (connection){
                if((client.state == State.WAITING_COMMAND) and (!commands.isEmpty())){
                    val command = commands.poll().trim()
                    write(command)

                    if(command != "WHERE"){
                        client.state = State.COMMAND_EXECUTION
                    }
                }
                try {
                    Thread.sleep(1L)
                } catch (e: java.lang.Exception) {
                }
            }
        }
    }

    fun stopWriting(){
        connection = false
    }

    fun writeWithCallBack(message: String):String{
        if(socket.isConnected){
            writeDependingStatus(message)

            countCommandsWithCallBack++
//            write("Take data$countCommandsWithCallBack")

            return RemoteReader(client).readLine(countCommandsWithCallBack)
        } else{
            // TODO: Migrate to log
            println("Writer: no connection to the robot")
        }
        return "false"
    }
}