package link.handlers

import link.RobotEntity

class ErrorsHandler(private val robotEntity: RobotEntity) {
    fun listener(command: String) {
        if (command.contains("(E", ignoreCase = true) ||
                command.contains("(P", ignoreCase = true)) {
            // TODO: Migrate to log
            println(command)
            robotEntity.errors.add(command)
        }
    }
}