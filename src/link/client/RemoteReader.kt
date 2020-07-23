package link.client

import link.RobotEntity
import kawasakiRobots.handlersFromKawasakiRobots.CommandAnalyzerForKawasakiRobots
import link.protocols.Analyzer
import java.util.*
import kotlin.concurrent.thread

class RemoteReader(private val robotEntity: RobotEntity) {
    private val socket = robotEntity.socket
    private var connection = socket.isConnected

    fun startReading(analyzer: Analyzer ) {
        if(connection){
            val reader = Scanner(socket.getInputStream())

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