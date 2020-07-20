package link

import utils.Delay
import java.io.PrintStream
import java.util.*
import kotlin.concurrent.thread

class RemoteWriter(private val robotEntity: RobotEntity) {
    private lateinit var out: PrintStream
    private val socket = robotEntity.socket
    private var countCommandsWithCallBack = 0


    init {
        if (socket.isConnected){
            out = PrintStream(socket.getOutputStream())
            sendCommandsFromQueue()
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
            robotEntity.commandsQueue.add(message)

            return true
        }
        return false
    }

    private fun sendCommandsFromQueue(){
//        thread {
//            while (socket.isConnected){
//                if((robotEntity.state == State.WAITING_COMMAND) and
//                        (!robotEntity.commandsQueue.isEmpty())){
//
//                    val command = robotEntity.commandsQueue.poll().trim()
//                    write(command)
//
//                    if(command != "WHERE"){
//                        robotEntity.state = State.COMMAND_EXECUTION
//                    }
//                }
//                Delay.little()
//            }
//        }
    }

    fun writeWithCallBack(message: String):String{
        if(socket.isConnected){
            writeDependingStatus(message)

            countCommandsWithCallBack++
//            write("Take data$countCommandsWithCallBack")

            return RemoteReader(robotEntity).readLine(countCommandsWithCallBack)
        } else{
            // TODO: Migrate to log
            println("Writer: no connection to the robot")
        }
        return "false"
    }
}