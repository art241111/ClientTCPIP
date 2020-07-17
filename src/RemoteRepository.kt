import client.Client
import utils.SendCommands
import kotlin.concurrent.thread

const val ADDRESS = "192.168.0.2"
const val PORT = 49152

fun main(args: Array<String>) {
    // Create client
    val client = createClient(ADDRESS,PORT)

    // Create sender
    val senderCommands = SendCommands
    senderCommands.setClient(client)


    // Send stop command
    senderCommands.scStopProgram()
}

private fun createClient(address:String,
                         port:Int
                        ): Client {
    val client = Client
    client.createClient(address, port)
    client.run()

    return client
}