package kawasakiRobots

import kawasakiRobots.utils.Moving
import kawasakiRobots.utils.Service
import kawasakiRobots.commands.moving.MovingCommand
import kawasakiRobots.commands.service.ServiceCommand
import link.*
import link.client.TelnetClient

class KawasakiRobot(address: String = "127.0.0.1",
                    port: Int = 9105,
                    login: String = "as",
                    password: String = "as") {
    private var client: TelnetClient = TelnetClient(address, port, login, password)
    private var robotEntity = RobotEntity(client)

    val moving = Moving(MovingCommand(), robotEntity)
    val service = Service(ServiceCommand(), robotEntity)

    fun switchRobotOff(){
        client.disconnect()
    }
}