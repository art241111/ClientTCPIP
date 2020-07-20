package link.client

import link.RobotEntity
import link.handlers.CommandAnalyzer
import java.util.*
import kotlin.concurrent.thread
import kotlin.system.exitProcess

class RemoteReader(private val robotEntity: RobotEntity) {
    private val socket = robotEntity.socket
    private var connection = socket.isConnected

    fun startReading() {
        if(connection){
            val reader = Scanner(socket.getInputStream())
            val analyzer = CommandAnalyzer(robotEntity)

            thread {
                while (connection){
                    try {
                        analyzer.commandAnalysis(reader.nextLine().trim())
                    }catch (e: NoSuchElementException) {
                        // TODO: Migrate to log
                        println("Problem with reading")
                    }

                }
            }
        }
    }

    fun stopReading(){
        connection = false
    }
}