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

    fun getPosition(): String {
        var coordinates = robotEntity.writer
                .writeDependingStatus(commands.ROBOT_POSITION())

//        // Bounding the array on both sides
//        coordinates = coordinates.substringAfter("JT6")
//                                 .substringBefore(">")
//                                 .trim()
//
//        // Cut out the inner part
//        val coordinatesJT = coordinates.substringBefore("X").trim()
//        coordinates = coordinates.substringAfter("T[deg]").trim()
//
//        // Creating an array from the received values
//        val coordinatesArray: MutableList<String> = coordinatesJT.split("   ").toMutableList()
//        coordinatesArray.addAll(coordinates.split("   "))

        return coordinates.toString()
    }


}