package utils

import client.Client
import commands.Command
import protocols.Sender

object SendCommands{
    private lateinit var sender: Sender

    fun setClient(sender: Sender){
        this.sender = sender
    }

    fun scStopProgram(){
        if(sender.write(Command.END_PROGRAM)){
            print("Send end command")
        } else {
            print("Send don't work")
        }
    }
}