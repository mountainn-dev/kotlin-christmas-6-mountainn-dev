package christmas

class InputValidator {

    fun checkIsNotBlank(input: String) {
        require(input.isNotBlank())
    }

    fun checkIsDigit(input: String) {
        require(!input.map { Character.isDigit(it) }.contains(false))
    }

    fun checkHasCommaSeparator(input: String) {
        require(input.contains(COMMA))
    }

    companion object {
        private const val COMMA = ","
    }
}