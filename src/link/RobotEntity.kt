package link

import utils.Delay
import java.net.Socket
import java.util.*
import kotlin.concurrent.thread

class RobotEntity(var client: TelnetClient) {
    val socket = client.getSocket()

    lateinit var writer:RemoteWriter
    lateinit var reader:RemoteReader

    var state:State = State.WAITING_COMMAND
    var commandsQueue: Queue<String> = LinkedList<String>()

    var position: MutableList<String> = mutableListOf()

    init{
        if(socket.isConnected){
            writer = RemoteWriter(this)
            reader = RemoteReader(this)

            reader.startReading()

            startQueueListener()
        }
    }

    fun startQueueListener(){
        thread {
            while (socket.isConnected){
                if((state == State.WAITING_COMMAND) and (!commandsQueue.isEmpty())){
                    writer.write(commandsQueue.poll().trim())
                }
                Delay.little()
            }
        }
    }
}