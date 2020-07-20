package link.handlers

import link.RobotEntity
import link.State

class CommandAnalyzer(private var robotEntity: RobotEntity) {

    fun commandAnalysis(command: String){
        when(command){
            ">DO motion completed." -> robotEntity.state = State.WAITING_COMMAND
        }

        WhereHandler(robotEntity).listener(command)
        ErrorsHandler(robotEntity).listener(command)
    }
}