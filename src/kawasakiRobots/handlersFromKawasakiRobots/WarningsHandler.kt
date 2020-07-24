package kawasakiRobots.handlersFromKawasakiRobots

import link.RobotEntity
import link.State

class WarningsHandler(private val robotEntity: RobotEntity) {
    fun listener(command: String) {
        if (command.contains("(P", ignoreCase = true)) {
            // TODO: Migrate to log
            println(command)
            robotEntity.errors.add(command)
        }
    }
}