package kawasakiRobots.utils

import link.RemoteWriter
import link.TelnetClient
import CommandsProtocols.MovingCommandIn

class Moving(private val command: MovingCommandIn,
             client: TelnetClient,
             private var commandWriter: RemoteWriter) {

    fun moveByX(position: Int) =
        commandWriter.writeDependingStatus(command.MOVE_BY_X() + position)

    fun moveByY(position: Int) =
            commandWriter.writeDependingStatus(command.MOVE_BY_Y() + position)

    fun moveByZ(position: Int) =
            commandWriter.writeDependingStatus(command.MOVE_BY_Z() + position)
}