package utils

import client.Client
import commands.Command
import protocols.Sender

object SendCommands{
    private lateinit var senser: Sender

    fun setClient(senser: Sender){
        this.senser = senser
    }

    fun scStopProgram(){
        if(senser.write(Command.END_PROGRAM)){
            print("Send end command")
        } else {
            print("Send don't work")
        }

    }
}