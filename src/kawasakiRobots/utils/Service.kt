package kawasakiRobots.utils

import link.RemoteWriter
import link.TelnetClient
import CommandsProtocols.ServiceCommandIn

class Service(private var commands: ServiceCommandIn, client: TelnetClient) {
    private var commandWriter: RemoteWriter = RemoteWriter(client.getSocket())

    fun turnOnTheMotors() =
        commandWriter.write(commands.TURN_ON_THE_MOTORS())


    fun deleteErrors() =
        commandWriter.write(commands.DELETE_ERRORS())

}