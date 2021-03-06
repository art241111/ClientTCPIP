package link

import kawasakiRobots.handlersFromKawasakiRobots.CommandAnalyzerForKawasakiRobots
import link.client.RemoteReader
import link.client.RemoteWriter
import link.client.TelnetClient
import utils.Delay
import java.util.*
import kotlin.concurrent.thread

class RobotEntity(client: TelnetClient) {
    val socket = client.getSocket()

    lateinit var writer: RemoteWriter
    lateinit var reader: RemoteReader

    var state:State = State.WAITING_COMMAND
    var commandsQueue: Queue<String> = LinkedList<String>()

    var position: MutableList<Float> = mutableListOf()
    var errors:MutableList<String> = mutableListOf()

    private var connection = socket.isConnected

    init{
        if(socket.isConnected){
            startQueueListener()

            writer = RemoteWriter(this)
            reader = RemoteReader(this)

            // TODO: Think about how to make an entity independent
            reader.startReading(CommandAnalyzerForKawasakiRobots(this))


        }
    }

    fun disconnect(){
        socket.close()
        reader.stopReading()
        connection = false
    }

    private fun startQueueListener(){
        thread {
            while (connection){
                if(state == State.ERROR){
                    commandsQueue.clear()
                } else if((state == State.WAITING_COMMAND) and (!commandsQueue.isEmpty())){
                    writer.write(commandsQueue.poll().trim())
                }
                Delay.little()
            }
        }
    }
}