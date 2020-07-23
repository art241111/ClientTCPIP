package kawasakiRobots.handlersFromKawasakiRobots

import link.RobotEntity
import link.State
import link.protocols.Analyzer

class CommandAnalyzerForKawasakiRobots(private var robotEntity: RobotEntity): Analyzer {

    override fun commandAnalysis(command: String){
        // If you receive a notification that the action has been
        // performed, change the status
        ChangeStateHandler(robotEntity).listener(command)

        // Processing the WHERE command
        WhereHandler(robotEntity).listener(command)

        // Error handling
        ErrorsHandler(robotEntity).listener(command)
    }
}