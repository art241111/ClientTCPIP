package link.client

import link.RobotEntity
import link.handlers.CommandAnalyzer
import java.util.*
import kotlin.concurrent.thread

class RemoteReader(private val robotEntity: RobotEntity) {
    private val socket = robotEntity.socket

    fun startReading() {
        if(socket.isConnected){
            val reader = Scanner(socket.getInputStream())
            val analyzer = CommandAnalyzer(robotEntity)

            thread {
                while (socket.isConnected){
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
}