package kawasakiRobots.utils

import kawasakiRobots.commands.moving.MovingCommand.*
import link.RobotEntity

class Moving(private val robotEntity: RobotEntity) {
    fun moveByX(position: Int) =
            robotEntity.writer.writeDependingStatusAndChangeIt(MOVE_BY_X.command + position)

    fun moveByY(position: Int) =
            robotEntity.writer.writeDependingStatusAndChangeIt(MOVE_BY_Y.command + position)

    fun moveByZ(position: Int) =
            robotEntity.writer.writeDependingStatusAndChangeIt(MOVE_BY_Z.command + position)
}