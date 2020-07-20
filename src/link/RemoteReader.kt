package link

import java.util.*
import kotlin.concurrent.thread

class RemoteReader(private val client: TelnetClient) {
    private val socket = client.getSocket()
    private var connection = false

    fun startReading() {
        if(socket.isConnected){
            connection = true
            val reader = Scanner(socket.getInputStream())

            thread {
                while (connection){
                    try {
                        val line = reader.nextLine()
                        println(line)
                        if(line == ">DO motion completed."){
                            client.state = State.WAITING_COMMAND
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
    fun stopReading(){
        connection = false
        println("Reading end")
    }
}