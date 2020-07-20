package kawasakiRobots

import kawasakiRobots.utils.Moving
import kawasakiRobots.utils.Service
import link.TelnetClient
import kawasakiRobots.commands.moving.MovingCommand
import kawasakiRobots.commands.service.ServiceCommand
import link.RemoteReader
import link.RemoteWriter
import link.State

class KawasakiRobot(address: String = "127.0.0.1",
                    port: Int = 9105,
                    login: String = "as",
                    password: String = "as") {
    private var client: TelnetClient = TelnetClient(address, port, login, password)
    private var reader = RemoteReader(client)
    private var writer = RemoteWriter(client)

    val moving = Moving(MovingCommand(), client, writer)
    val service = Service(ServiceCommand(), client, writer)

    init {
        reader.startReading()
    }

    fun switchRobotOff(){
        try {
            Thread.sleep(50000L)
        } catch (e: java.lang.Exception) {
        }

        reader.stopReading()
        client.disconnect()
        writer.stopWriting()
    }

}