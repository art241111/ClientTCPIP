package kawasakiRobots.handlersFromKawasakiRobots

import link.RobotEntity

class WhereHandler(private val robotEntity: RobotEntity) {
    private var counter = -1
    private var position: MutableList<Float> = mutableListOf()

    fun listener(command: String){
        // TODO:
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