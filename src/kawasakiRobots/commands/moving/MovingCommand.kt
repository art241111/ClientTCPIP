package kawasakiRobots.commands.moving

import CommandsProtocols.MovingCommandIn

class MovingCommand: MovingCommandIn {
    override fun MOVE_BY_X(): String = "do draw "
    override fun MOVE_BY_Y(): String = "do draw ,"
    override fun MOVE_BY_Z(): String = "do draw ,,"
}