package link

import java.net.Socket
import java.util.*
import kotlin.concurrent.thread

class RemoteReader(socket: Socket) {
    private var connection = false

    init {
        if(socket.isConnected){
            connection = true
            val reader = Scanner(socket.getInputStream())
            thread {
                while (connection){
                    println(reader.nextLine())
                }
            }
        }
    }

    fun stopReading(){
        connection = false
        println("Reading end")
    }
}