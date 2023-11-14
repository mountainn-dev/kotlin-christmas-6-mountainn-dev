package christmas

import christmas.domain.Calendar.*
import christmas.domain.VisitDay
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.assertj.core.api.Assertions.assertThat

class VisitDayTest {
    private val weekDay = WEEK_DAY.days.first()
    private val weedEnd = WEEK_END.days.first()
    private val specialDay = SPECIAL_DAY.days.first()

    @Nested
    @DisplayName("방문 날짜 유효성")
    inner class VisitDayValidation {

        @ParameterizedTest
        @ValueSource(ints = [0, 32])
        @DisplayName("날짜 유효성")
        fun `방문일이 1 부터 31 사이의 숫자가 아니면 예외 발생`(day: Int) {
            assertThrows<IllegalArgumentException> {
                VisitDay(day)
            }
        }
    }

    @Nested
    @DisplayName("방문 날짜 메서드")
    inner class VisitDayMethod {

        @Test
        @DisplayName("날짜 계산")
        fun `daySinceDecemberFirst 메서드 사용 시 방문일이 12월 1일 로부터 며칠 지났는지 확인`() {
            assertThat(VisitDay(5).daySinceDecemberFirst()).isEqualTo(4)
        }

        @Test
        @DisplayName("평일 확인")
        fun `isWeekDay 메서드 사용 시 방문일이 평일인지 확인`() {
            assertThat(VisitDay(weekDay).isWeekDay()).isEqualTo(true)
            assertThat(VisitDay(weedEnd).isWeekDay()).isEqualTo(false)
        }

        @Test
        @DisplayName("주말")
        fun `isWeekEnd 메서드 사용 시 방문일이 주말인지 확인`() {
            assertThat(VisitDay(weedEnd).isWeekEnd()).isEqualTo(true)
            assertThat(VisitDay(weekDay).isWeekEnd()).isEqualTo(false)
        }

        @Test
        @DisplayName("특별일")
        fun `isSpecialDay 메서드 사용 시 방문일이 특별일인지 확인`() {
            assertThat(VisitDay(specialDay).isSpecialDay()).isEqualTo(true)
            assertThat(VisitDay(weedEnd).isSpecialDay()).isEqualTo(false)
        }
    }
}