package kawasakiRobots.commands.service

enum class ServiceCommand(val command: String) {
    TURN_ON_THE_MOTORS ("ZPOW ON"),
    DELETE_ERRORS ("ERESET"),
    ROBOT_POSITION ("WHERE");
}