package christmas

import christmas.domain.Calendar
import christmas.domain.VisitDay
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.assertj.core.api.Assertions.assertThat

class VisitDayTest {

    @ParameterizedTest
    @ValueSource(ints = [0, 32])
    @DisplayName("날짜 유효성")
    fun `방문일이 1 부터 31 사이의 숫자가 아니면 예외 발생`(day: Int) {
        assertThrows<IllegalArgumentException> {
            VisitDay(day)
        }
    }

    @Test
    @DisplayName("날짜 계산")
    fun `daySinceDecemberFirst 메서드 사용 시 방문일이 12월 1일 로부터 며칠 지났는지 확인`() {
        assertThat(VisitDay(5).daySinceDecemberFirst()).isEqualTo(4)
    }

    @Nested
    @DisplayName("날짜 구분")
    inner class DayCheck {

        @Test
        @DisplayName("평일")
        fun `isWeekDay 메서드 사용 시 방문일이 평일이면 true`() {
            assertThat(VisitDay(WEEK_DAY).isWeekDay()).isEqualTo(true)
        }

        @Test
        @DisplayName("평일 X")
        fun `isWeekDay 메서드 사용 시 방문일이 평일이 아니면 false`() {
            assertThat(VisitDay(WEEK_END).isWeekDay()).isEqualTo(false)
        }

        @Test
        @DisplayName("주말")
        fun `isWeekEnd 메서드 사용 시 방문일이 주말이면 true`() {
            assertThat(VisitDay(WEEK_END).isWeekEnd()).isEqualTo(true)
        }

        @Test
        @DisplayName("주말 X")
        fun `isWeekEnd 메서드 사용 시 방문일이 주말이 아니면 false`() {
            assertThat(VisitDay(WEEK_DAY).isWeekEnd()).isEqualTo(false)
        }

        @Test
        @DisplayName("특별일")
        fun `isSpecialDay 메서드 사용 시 방문일이 특별일이면 true`() {
            assertThat(VisitDay(SPECIAL_DAY).isSpecialDay()).isEqualTo(true)
        }

        @Test
        @DisplayName("특별일 X")
        fun `isSpecialDay 메서드 사용 시 방문일이 특별일이 아니면 false`() {
            assertThat(VisitDay(WEEK_END).isSpecialDay()).isEqualTo(false)
        }
    }

    companion object {
        private const val WEEK_DAY = Calendar.WEEK_DAY.days.first()
        private const val WEEK_END = Calendar.WEEK_END.days.first()
        private const val SPECIAL_DAY = Calendar.SPECIAL_DAY.days.first()
    }
}