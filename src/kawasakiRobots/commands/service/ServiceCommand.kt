package kawasakiRobots.commands.service

import commandsProtocols.ServiceCommandIn

class ServiceCommand: ServiceCommandIn {
    override fun TURN_ON_THE_MOTORS() = "ZPOW ON"
    override fun DELETE_ERRORS(): String = "ERESET"
    override fun ROBOT_POSITION(): String = "WHERE"
}