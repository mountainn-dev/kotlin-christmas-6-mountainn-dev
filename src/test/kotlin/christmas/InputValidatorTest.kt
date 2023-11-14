package christmas

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InputValidatorTest {
    private val inputValidator = InputValidator()

    @Test
    @DisplayName("공백")
    fun `checkIsNotBlank 메서드 사용 시 입력값이 공백이면 예외 발생`() {
        assertThrows<IllegalArgumentException> {
            inputValidator.checkIsNotBlank("")
        }
    }

    @Test
    @DisplayName("숫자")
    fun `checkIsDigit 메서드 사용 시 입력값이 숫자가 아니면 예외 발생`() {
        assertThrows<IllegalArgumentException> {
            inputValidator.checkIsDigit("a")
        }
    }

    @Test
    @DisplayName("구분자 쉼표")
    fun `checkHasCommaSeparator 메서드 사용 시 입력값이 쉼표(',') 로 구분되어있지 않을 때 예외 발생`() {
        assertThrows<IllegalArgumentException> {
            inputValidator.checkHasCommaSeparator("1 2")
        }
    }

    @Test
    @DisplayName("구분자 대쉬")
    fun `checkHasDashSeparator 메서드 사용 시 입력값이 대쉬('-') 로 구분되어있지 않을 때 예외 발생`() {
        assertThrows<IllegalArgumentException> {
            inputValidator.checkHasDashSeparator("1,2")
        }
    }
}