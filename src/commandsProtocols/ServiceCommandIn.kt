package commandsProtocols

interface ServiceCommandIn {
    fun TURN_ON_THE_MOTORS(): String
    fun DELETE_ERRORS(): String
    fun ROBOT_POSITION(): String
}
