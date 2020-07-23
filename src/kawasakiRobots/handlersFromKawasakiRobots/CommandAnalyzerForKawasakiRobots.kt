package kawasakiRobots.handlersFromKawasakiRobots

import link.RobotEntity
import link.State
import link.protocols.Analyzer

class CommandAnalyzerForKawasakiRobots(private var robotEntity: RobotEntity): Analyzer {

    override fun commandAnalysis(command: String){
        when(command){
            ">DO motion completed." -> robotEntity.state = State.WAITING_COMMAND
        }

        WhereHandler(robotEntity).listener(command)
        ErrorsHandler(robotEntity).listener(command)
    }
}