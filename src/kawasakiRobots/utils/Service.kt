package kawasakiRobots.utils

import commandsProtocols.ServiceCommandIn
import link.RobotEntity

class Service(private var commands: ServiceCommandIn,
              private var robotEntity: RobotEntity
             ) {

    fun turnOnTheMotors() =
            robotEntity.writer.write(commands.TURN_ON_THE_MOTORS())

    fun resetErrors() =
            robotEntity.writer.write(commands.DELETE_ERRORS())

    fun updateInfoAboutPosition() = robotEntity.writer
            .writeDependingStatus(commands.ROBOT_POSITION())
}