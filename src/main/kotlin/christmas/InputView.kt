package christmas

import camp.nextstep.edu.missionutils.Console

class InputView {
    private val inputValidator = InputValidator()

    fun readNumber() = validatedNumber(read())

    private fun validatedNumber(input: String): Int {
        inputValidator.checkIsNotBlank(input)
        inputValidator.checkIsDigit(input)

        return input.toInt()
    }

    private fun read() = Console.readLine()
}