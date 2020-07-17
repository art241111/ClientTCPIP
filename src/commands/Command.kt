package commands

object Command {
    // Moving the manipulator along the X axis
    const val DELETE_ERRORS: String = "ERESET"

    // Turn on the motors
    const val TURN_ON_THE_MOTORS: String = "ZPOW ON"

    // Moving the manipulator along the X axis
    const val MOVE_BY_X: String = "do draw "

    // Moving the manipulator along the Y axis
    const val MOVE_BY_Y: String = "do draw ,"

    // Moving the manipulator along the Z axis
    const val MOVE_BY_Z: String = "do draw ,,"
}