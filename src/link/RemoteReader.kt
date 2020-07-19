package link

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket
import java.util.*
import java.util.stream.Collectors
import kotlin.concurrent.thread


class RemoteReader(private val socket: Socket) {
    private var connection = false

    fun startReading() {
        if(socket.isConnected){
            connection = true
            val reader = Scanner(socket.getInputStream())

            thread {
                while (connection){
                    try {
                        println(reader.nextLine())
                    }catch (e: NoSuchElementException) {

                    }
                }
            }
        }
    }

    fun readLine(count: Int):String{
        val reader = Scanner(socket.getInputStream())
        val content = StringBuilder()

        try {
            var line = reader.nextLine()
            while (line != ">Take data$count") {
                line = reader.nextLine()
                content.append(line)
            }
        } finally {
            return content.toString()
        }
    }
    fun stopReading(){
        connection = false
        println("Reading end")
    }
}