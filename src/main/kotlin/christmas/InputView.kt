package christmas

import camp.nextstep.edu.missionutils.Console

class InputView {

    fun readNumberWithMessage(message: String): Int {
        println(message)
        return readNumber()
    }

    private fun readNumber() = Console.readLine().toInt()
}