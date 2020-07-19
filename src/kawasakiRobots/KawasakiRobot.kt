package kawasakiRobots

import kawasakiRobots.utils.Moving
import kawasakiRobots.utils.Service
import link.TelnetClient
import kawasakiRobots.commands.moving.MovingCommand
import kawasakiRobots.commands.service.ServiceCommand
import link.RemoteReader

class KawasakiRobot(address: String = "127.0.0.1",
                    port: Int = 9105,
                    login: String = "as",
                    password: String = "as") {
    private var client: TelnetClient = TelnetClient(address, port, login, password)

    val moving = Moving(MovingCommand(), client)
    val service = Service(ServiceCommand(), client)


}