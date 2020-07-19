package link

import commands.Command
import java.io.IOException
import java.io.InputStream
import java.net.Socket
import java.net.UnknownHostException

class TelnetClient(server: String,port: Int, user: String, password: String){
    private var socket: Socket = Socket()
    private val `in`: InputStream

    //closes a this client. you may want to send command "exit" beforehand
    fun disconnect() {
        try {
            socket.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //this constructor is for plain socket. So You do not have to use apache library.
    init {
        try {
            socket= Socket(server, port)
        } catch (e: UnknownHostException){
            // TODO: Migrate to log
            print("Problem with create socket. \n $e")
        } catch (e: IOException){
            // TODO: Migrate to log
            print("Problem with create socket. \n $e")
        }

        `in` = socket.getInputStream()

        val writer = RemoteWriter(socket)
        writer.write(user)
        writer.write(password)

        writer.write(Command.DELETE_ERRORS)
        writer.write(Command.TURN_ON_THE_MOTORS)

        setPause()
    }

    fun getSocket(): Socket{
        return socket
    }

    private fun setPause(millis:Long = 200L){
        try {
            Thread.sleep(millis)
        } catch (e: Exception) {
        }
    }
}