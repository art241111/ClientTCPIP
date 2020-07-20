package link

import java.io.IOException
import java.net.Socket
import java.net.UnknownHostException

class TelnetClient(server: String,
                   port: Int,
                   private val user: String,
                   private val password: String){
    private var socket: Socket = Socket()
    var state = State.WAITING_COMMAND

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

        authorization()
    }

    fun getSocket(): Socket{
        return socket
    }

    //closes a this client. you may want to send command "exit" beforehand
    fun disconnect() {
        try {
            socket.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun authorization(){
        val writer = RemoteWriter(socket)
        writer.write(user)
        writer.write(password)
    }
}