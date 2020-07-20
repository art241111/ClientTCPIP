package link

import java.util.*
import kotlin.concurrent.thread

class RemoteReader(private val robotEntity: RobotEntity) {
    private val socket = robotEntity.socket

    fun startReading() {
        if(socket.isConnected){
            val reader = Scanner(socket.getInputStream())

            thread {
                while (socket.isConnected){
                    try {
                        val line = reader.nextLine()
                        println(line)
                        if(line.trim() == ">DO motion completed."){
                            robotEntity.state = State.WAITING_COMMAND
                        }
                    }catch (e: NoSuchElementException) {

                    }
                }
            }
        }
    }

    fun readLine(count: Int):String{
//        val reader = Scanner(socket.getInputStream())
//        val content = StringBuilder()
//
//        try {
//            var line = reader.nextLine()
//            while (line != ">Take data$count") {
//                line = reader.nextLine()
//                content.append(line)
//            }
//        } finally {
//            return content.toString()
//        }
        return ""
    }

}