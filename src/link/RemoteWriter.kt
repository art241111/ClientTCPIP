package link

import protocols.Sender
import java.io.PrintStream
import java.net.Socket

class RemoteWriter(private val socket: Socket): Sender {
    private var out: PrintStream = PrintStream(socket.getOutputStream())

    //this method writes to server, but waits no prompt.
    override fun write(message: String): Boolean {
        try {
            out!!.println(message)
            out!!.flush()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
}