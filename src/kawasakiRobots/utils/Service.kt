package kawasakiRobots.utils

import kawasakiRobots.commands.service.ServiceCommand.*
import link.RobotEntity

class Service(private var robotEntity: RobotEntity) {
    fun turnOnTheMotors() =
            robotEntity.writer.write(TURN_ON_THE_MOTORS.command)

    fun resetErrors() =
            robotEntity.writer.write(DELETE_ERRORS.command)

    fun updateInfoAboutPosition() = robotEntity.writer
            .writeDependingStatus(ROBOT_POSITION.command)
}