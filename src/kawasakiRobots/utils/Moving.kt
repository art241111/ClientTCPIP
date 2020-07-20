package kawasakiRobots.utils

import commandsProtocols.MovingCommandIn
import link.RobotEntity

class Moving(private val command: MovingCommandIn,
             private val robotEntity: RobotEntity) {

    fun moveByX(position: Int) =
            robotEntity.writer.writeDependingStatusAndChangeIt(command.MOVE_BY_X() + position)

    fun moveByY(position: Int) =
            robotEntity.writer.writeDependingStatusAndChangeIt(command.MOVE_BY_Y() + position)

    fun moveByZ(position: Int) =
            robotEntity.writer.writeDependingStatusAndChangeIt(command.MOVE_BY_Z() + position)
}