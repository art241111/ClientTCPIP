package utils

import commands.Command
import protocols.Sender

object SendCommands{
    private lateinit var sender: Sender

    fun setClient(sender: Sender){
        this.sender = sender
    }

    fun scMoveByX(distance: Int = 10){
        if(sender.write(Command.MOVE_BY_X + distance)){
            print("Send end command")
        } else {
            print("Send don't work")
        }
    }

    fun scMoveByY(distance: Int = 10){
        if(sender.write(Command.MOVE_BY_Y + distance)){
            print("Send end command")
        } else {
            print("Send don't work")
        }
    }

    fun scMoveByZ(distance: Int = 10){
        if(sender.write(Command.MOVE_BY_Z + distance)){
            print("Send end command")
        } else {
            print("Send don't work")
        }
    }
}