package link

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

                    }
                }
            }
        }
    }


}