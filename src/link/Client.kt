package link

import commands.Command
import protocols.Sender
import java.io.IOException
import java.io.OutputStream
import java.net.Socket
import java.net.UnknownHostException
import java.nio.charset.Charset
import java.util.*
import kotlin.concurrent.thread

object Client:Sender {
    private lateinit var connection: Socket
    private var connected: Boolean = false

    private lateinit var reader: Scanner
    private lateinit var writer: OutputStream

    /**
     * When create class - create connection
     */
    fun createClient(address: String, port: Int){
        createConnection(address, port)
    }

    /**
     * Start connections
     */
    fun run() {
        if (connected) {
            thread {
                startRead()
            }

            thread{
                startWrite()
            }
        }
    }

    /**
     * Start read process
     */
    private fun startRead() {
        read()
    }

    /**
     * Checking messages that the user enters.
     */
    private fun startWrite() {
        while (connected) {
            val input = readLine() ?: ""

            if ("exit" in input) {
                closeConnection()
            } else {
                write(input)
            }
        }
    }

    /**
     * Send message to the server
     * @return if server disconnect - return false
     */
     override fun write(message: String): Boolean {
        return if(connected){
            try {
                writer.write((message).toByteArray(Charset.defaultCharset()))
            } catch (e: IOException) {
                //TODO: Migrate to log
                println("Problem with send command. $e")
            }
            true
        } else{
            false
        }
    }

    /**
     * Reade data from server.
     * When server disconnect - stop program
     */
    private fun read() {
        while (connected)
            try {
                println(reader.nextLine())
            } catch (e: NoSuchElementException) {
                closeConnection()

                //TODO: Migrate to log
                println("Problem with read command. $e")
            }
    }

    /**
     * Close connection
     */
    private fun closeConnection() {
        connected = false

        reader.close()
        writer.close()
        connection.close()

        //TODO: Migrate to log
        println("Connection is close")
    }

    /**
     * Create connection
     * @param address - address of the server to connect ot
     * @param port - port for connection
     */
    private fun createConnection(address: String, port: Int) {
        try {
            connection = Socket(address, port)
            println("Connected to server at $address on port $port")

            reader = Scanner(connection.getInputStream())
            writer = connection.getOutputStream()

            connected = true
        } catch (e: IOException) {
            //TODO: Migrate to log
            println("Problem with connection. The server may not be working. \n $e")
            connected = false
        } catch (e: UnknownHostException) {
            //TODO: Migrate to log
            println("Problem with connection. The server may not be working. \n $e")
            connected = false
        }
    }
}