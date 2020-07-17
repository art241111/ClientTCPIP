import client.Client

const val ADDRESS = "192.168.31.63"
const val PORT = 9999

fun main(args: Array<String>) {
    // Create client
    val client = createClient(ADDRESS,PORT)

    // Send message
    client.scStopProgram()
}

private fun createClient(address:String = "localhost",
                         port:Int = 9999
                        ): Client {
    val client = Client
    client.createClient(address, port)
    client.run()

    return client
}