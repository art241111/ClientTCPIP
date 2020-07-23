package kawasakiRobots.commands.moving


enum class MovingCommand(val command: String) {
    MOVE_BY_X( "do draw "),
    MOVE_BY_Y( "do draw ,"),
    MOVE_BY_Z( "do draw ,,");
}