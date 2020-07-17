import java.io.IOException
import java.io.OutputStream
import java.net.Socket
import java.net.UnknownHostException
import java.nio.charset.Charset
import java.util.*
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    // Link param
    val address = "localhost"
    val port = 9999

    // Create client
    val client = Client(address, port)
    client.run()
}

class Client(address: String, port: Int) {
    private lateinit var connection: Socket
    private var connected: Boolean

    private lateinit var reader: Scanner
    private lateinit var writer: OutputStream

    /**
     * Create connection and catch errors
     */
    init {
        try{
            connection = Socket(address, port)
            println("Connected to server at $address on port $port")

            reader = Scanner(connection.getInputStream())
            writer = connection.getOutputStream()

            connected = true
        }catch(e: IOException){
            //TODO: Migrate to log
            println(e)
            connected = false
        } catch (e: UnknownHostException){
            //TODO: Migrate to log
            println(e)
            connected = false
        }
    }

    /**
     * Start connections
     */
    fun run() {
        if(connected){
            startRead()
            startWrite()
        }
    }

    private fun startRead(){
        thread {
            read()
        }
    }

    private fun startWrite(){
        while (connected) {
            val input = readLine() ?: ""
            if ("exit" in input) {
                connected = false
                reader.close()
                connection.close()
            } else {
                write(input)
            }
        }
    }

    private fun write(message: String) {
        writer.write((message + '\n').toByteArray(Charset.defaultCharset()))
    }

    private fun read() {
        while (connected)
            println(reader.nextLine())
    }
}