import link.TelnetClient
import utils.SendCommands


    private const val ADDRESS = "127.0.0.1"
    private const val PORT = 9105
    private const val LOGIN = "as"
    private const val PASSWORD = "as"

    private lateinit var client: TelnetClient
    private val sender = SendCommands

    fun main(args: Array<String>) {
        createClient(ADDRESS,PORT,LOGIN,PASSWORD)

        val sender = SendCommands
        sender.setClient(client)

//        moveByX(100)
//        moveByY(100)
//        moveByZ(100)
    }

    fun createClient(address: String, port: Int, login: String, password: String) {
        client = TelnetClient(address,port, login, password)
    }

    fun moveByX(distance: Int = 10){
        sender.scMoveByX(distance)
    }

    fun moveByY(distance: Int = 10){
        sender.scMoveByY(distance)
    }

    fun moveByZ(distance: Int = 10){
        sender.scMoveByZ(distance)
    }



