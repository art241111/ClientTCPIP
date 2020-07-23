package kawasakiRobots.handlersFromKawasakiRobots

import link.RobotEntity
import link.State

class ChangeStateHandler(private val robotEntity: RobotEntity) {
    fun listener(command: String) {
        when(command){
            ">DO motion completed." -> robotEntity.state = State.WAITING_COMMAND
        }
    }
}