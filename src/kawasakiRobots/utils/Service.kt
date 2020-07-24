package kawasakiRobots.utils

import kawasakiRobots.commands.service.ServiceCommand.*
import link.RobotEntity

class Service(private var robotEntity: RobotEntity) {
    fun turnOnTheMotors() =
            robotEntity.writer.writeDependingStatus(TURN_ON_THE_MOTORS.command)

    fun turnOffTheMotors() =
            robotEntity.writer.writeDependingStatus(TURN_OFF_THE_MOTORS.command)

    fun resetErrors() =
            robotEntity.writer.writeDependingStatus(DELETE_ERRORS.command)

    fun updateInfoAboutPosition() = robotEntity.writer
            .writeDependingStatus(ROBOT_POSITION.command)
}