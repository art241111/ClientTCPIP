import client.Client
import utils.SendCommands

const val ADDRESS = "192.168.0.2"
const val PORT = 49152

fun main(args: Array<String>) {
    // Create client
    val client = createClient(ADDRESS,PORT)

    val senderCommands = SendCommands
    senderCommands.setClient(client)

    senderCommands.scStopProgram()
}

private fun createClient(address:String = "localhost",
                         port:Int = 9999
                        ): Client {
    val client = Client
    client.createClient(address, port)
    client.run()

    return client
}