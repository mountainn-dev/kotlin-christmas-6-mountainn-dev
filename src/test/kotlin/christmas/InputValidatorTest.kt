package christmas

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InputValidatorTest {
    private val inputValidator = InputValidator()

    @Test
    @DisplayName("공백")
    fun `checkIsNotBlank 메서드 사용 시 입력값이 공백이면 예외 발생`() {
        val input = ""
        
        assertThrows<IllegalArgumentException> {
            inputValidator.checkIsNotBlank(input)
        }
    }

    @Test
    @DisplayName("숫자")
    fun `checkIsDigit 메서드 사용 시 입력값이 숫자가 아니면 예외 발생`() {
        val input = "a"

        assertThrows<IllegalArgumentException> {
            inputValidator.checkIsDigit(input)
        }
    }
}