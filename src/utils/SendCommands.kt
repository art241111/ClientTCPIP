package utils

import commands.Command
import protocols.Sender

object SendCommands{
    private lateinit var sender: Sender

    fun setClient(sender: Sender){
        this.sender = sender
    }

    fun scMoveByX(distance: Int = 10){
        sendCommandToTheRobot(Command.MOVE_BY_X + distance)
    }

    fun scMoveByY(distance: Int = 10){
        sendCommandToTheRobot(Command.MOVE_BY_Y + distance)
    }

    fun scMoveByZ(distance: Int = 10){
        sendCommandToTheRobot(Command.MOVE_BY_Z + distance)
    }

    fun turnOnTheMotors(){
        sendCommandToTheRobot(Command.TURN_ON_THE_MOTORS)
    }

    fun deleteErrors(){
        sendCommandToTheRobot(Command.DELETE_ERRORS)
    }

    private fun sendCommandToTheRobot(command:String){
        if(sender.write(command)){
            print("Send command")
        } else {
            print("Sender commands don't work")
        }
        setPause()
    }

    private fun setPause(millis:Long = 1000L){
        try {
            Thread.sleep(millis)
        } catch (e: Exception) {
        }
    }
}