package utils

import client.Client
import commands.Command

object SendCommands{
    private lateinit var client: Client

    fun setClient(client: Client){
        this.client = client
    }

    fun scStopProgram(){
        client.write(Command.END_PROGRAM)
        print("Send end command")
    }
}