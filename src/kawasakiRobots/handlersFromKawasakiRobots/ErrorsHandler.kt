package kawasakiRobots.handlersFromKawasakiRobots

import link.RobotEntity
import link.State

class ErrorsHandler(private val robotEntity: RobotEntity) {
    fun listener(command: String) {
        if (command.contains("(E", ignoreCase = true) ||
                command.contains("(P", ignoreCase = true)) {
            // TODO: Migrate to log
            println(command)
            robotEntity.errors.add(command)
            robotEntity.state = State.ERROR
        }
    }
}