import java.io.IOException
import java.io.OutputStream
import java.net.Socket
import java.net.UnknownHostException
import java.nio.charset.Charset
import java.util.*
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    // Link param
    val address = "192.168.31.63"
    val port = 9999

    // Create client
    val client = Client(address, port)
    client.run()
}

class Client(address: String, port: Int) {
    private lateinit var connection: Socket
    private var connected: Boolean = false

    private lateinit var reader: Scanner
    private lateinit var writer: OutputStream

    /**
     * Create connection and catch errors
     */
    init {
        createConnection(address, port)
    }

    /**
     * Start connections
     */
    fun run() {
        if(connected){
            thread {
                startRead()
            }

            startWrite()
        }
    }

    private fun startRead(){
        read()
    }

    private fun startWrite(){
        while (connected) {
            val input = readLine() ?: ""

            if ("exit" in input) {
                closeConnection()
            } else {
                write(input)
            }
        }
    }

    private fun write(message: String) {
        try{
            writer.write((message + '\n').toByteArray(Charset.defaultCharset()))
        } catch (e: IOException){
            //TODO: Migrate to log
            println("Problem with send command. $e")
        }

    }

    private fun read() {
        while (connected)
            try{
                println(reader.nextLine())
            } catch (e: NoSuchElementException){
                closeConnection()

                //TODO: Migrate to log
                println("Problem with read command. $e")
            }
    }

    private fun closeConnection(){
        connected = false
        reader.close()
        connection.close()
    }

    private fun createConnection(address: String, port: Int){
        try{
            connection = Socket(address, port)
            println("Connected to server at $address on port $port")

            reader = Scanner(connection.getInputStream())
            writer = connection.getOutputStream()

            connected = true
        }catch(e: IOException){
            //TODO: Migrate to log
            println("Problem with connection. $e")
            connected = false
        } catch (e: UnknownHostException){
            //TODO: Migrate to log
            println("Problem with connection. $e")
            connected = false
        }
    }
}