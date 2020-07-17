package protocols

interface Sender {
    fun write(message:String):Boolean
}