package commandsProtocols

interface MovingCommandIn {
    fun MOVE_BY_X(): String
    fun MOVE_BY_Y(): String
    fun MOVE_BY_Z(): String
}