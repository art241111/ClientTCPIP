import client.Client

fun main(args: Array<String>) {
    // Link param
    val address = "192.168.31.63"
    val port = 9999

    // Create client
    val client = createClient(address,port)

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