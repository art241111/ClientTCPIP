package kawasakiRobots.utils

import link.RemoteWriter
import link.TelnetClient
import CommandsProtocols.ServiceCommandIn

class Service(private var commands: ServiceCommandIn, client: TelnetClient) {
    private var commandWriter: RemoteWriter = RemoteWriter(client.getSocket())

    fun turnOnTheMotors() =
        commandWriter.write(commands.TURN_ON_THE_MOTORS())

    fun resetErrors() =
        commandWriter.write(commands.DELETE_ERRORS())

    fun getPosition(): String {
        var coordinates = commandWriter.writeWithCallBack(commands.ROBOT_POSITION())
        println(coordinates)

        // Bounding the array on both sides
        coordinates = coordinates.substringAfter("JT6")
                                 .substringBefore(">")
                                 .trim()

        // Cut out the inner part
        val coordinatesJT = coordinates.substringBefore("X").trim()
        coordinates = coordinates.substringAfter("T[deg]").trim()

        // Creating an array from the received values
        val coordinatesArray: MutableList<String> = coordinatesJT.split("   ").toMutableList()
        coordinatesArray.addAll(coordinates.split("   "))

        return coordinatesArray.toString()
    }


}