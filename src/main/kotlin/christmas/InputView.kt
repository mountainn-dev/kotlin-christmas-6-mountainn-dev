package christmas

import camp.nextstep.edu.missionutils.Console

class InputView {
    private val inputValidator = InputValidator()

    fun readNumber() = validatedNumber(read())

    fun readWords() = validatedWords(read())

    private fun validatedNumber(input: String): Int {
        inputValidator.checkIsNotBlank(input)
        inputValidator.checkIsDigit(input)

        return input.toInt()
    }

    private fun validatedWords(input: String): List<String> {
        inputValidator.checkIsNotBlank(input)
        if (inputValidator.isMultipleWords(input)) {
            inputValidator.checkHasCommaSeparator(input)
            return input.split(COMMA)
        }
        return listOf(input)
    }

    private fun read() = Console.readLine()

    companion object {
        private const val COMMA = ","
    }
}