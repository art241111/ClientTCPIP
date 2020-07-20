package link

import utils.Delay
import java.net.Socket
import java.util.*
import kotlin.concurrent.thread

class RobotEntity(var client: TelnetClient) {
    val socket = client.getSocket()

    val writer = RemoteWriter(this)
    val reader = RemoteReader(this)

    var state:State = State.WAITING_COMMAND
    var commandsQueue: Queue<String> = LinkedList<String>()

    var position: MutableList<String> = mutableListOf()

    init{
        reader.startReading()

        startQueueListener()
    }

    fun startQueueListener(){
        thread {
            while (socket.isConnected){
                if((state == State.WAITING_COMMAND) and
                        (!commandsQueue.isEmpty())){

                    val command = commandsQueue.poll().trim()
                    writer.write(command)

                    if(command != "WHERE"){
                        state = State.COMMAND_EXECUTION
                    }
                }
                Delay.little()
            }
        }
    }
}