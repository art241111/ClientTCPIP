package link

class CommandAnalyzer(private var robotEntity: RobotEntity) {
    private var counter = -1
    private var position: MutableList<Float> = mutableListOf()

    fun commandAnalysis(command: String){
        when(command){
            ">DO motion completed." -> robotEntity.state = State.WAITING_COMMAND
        }

        handlerWhere(command)
        handlerErrors(command)
    }

    private fun handlerErrors(command: String) {
        if (command.contains("(E", ignoreCase = true) ||
                command.contains("(P", ignoreCase = true)) {
            // TODO: Migrate to log
            println(command)
            robotEntity.errors.add(command)
        }
    }

    private fun handlerWhere(command: String){
        when {
            command.contains("JT1", ignoreCase = true) -> {
                counter = 2
                position = mutableListOf()
            }
            counter == 2  -> {
                position.addAll(strToFloatArray(command))
                counter--
            }

            counter == 1  -> {
                counter--
            }

            counter == 0 -> {
                position.addAll(strToFloatArray(command))
                robotEntity.position = position
                counter--
            }
        }
    }

    private fun strToFloatArray(command: String): MutableList<Float>{
        val floatArray = mutableListOf<Float>()
        var number: String = ""

        for(char in command){
            if(char.toString() != " "){
                number += char
            }else if(number != ""){
                floatArray.add(number.trim().toFloat())
                number = ""
            }
        }

        return floatArray
    }

}