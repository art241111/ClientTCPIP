package link

import protocols.Sender
import java.io.IOException
import java.io.InputStream
import java.io.PrintStream
import java.net.Socket

class TelnetClient(server: String,port: Int, user: String, password: String): Sender {
    private val socket: Socket?
    private val `in`: InputStream
    private val out: PrintStream
    private val login: String
    private val password: String

    //just sends text to server
    fun print(value: String) {
        try {
            out.println("$value;")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //this method writes to server, but waits no prompt.
    override fun write(message: String): Boolean {
        try {
            out.println(message)
            out.flush()

            return true
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    //closes a this client. you may want to send command "exit" beforehand
    fun disconnect() {
        try {
            socket?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //this constructor is for plain socket. So You do not have to use apache library.
    init {
        socket = Socket(server, port)
        `in` = socket.getInputStream()
        out = PrintStream(socket.getOutputStream())
        login = user
        this.password = password

//        setPause()
        write(user)

//        setPause()
        write(password)

        write("ZPOW ON")
        //write("do draw 100")
    }

    private fun setPause(millis:Long = 200L){
        try {
            Thread.sleep(millis)
        } catch (e: Exception) {
        }
    }
}