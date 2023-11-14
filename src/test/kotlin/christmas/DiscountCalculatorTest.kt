package christmas

import christmas.domain.*
import christmas.domain.Calendar.*
import christmas.domain.menu.Appetizer.*
import christmas.domain.menu.Dessert.*
import christmas.domain.menu.MainDish.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class DiscountCalculatorTest {
    private val discountCalculator = DiscountCalculator()
    private val weekDay = WEEK_DAY.days.first()
    private val weekEnd = WEEK_END.days.first()
    private val specialDay = SPECIAL_DAY.days.first()

    @Nested
    @DisplayName("디데이 할인")
    inner class DDayDiscount {

        @Test
        @DisplayName("12월 1일")
        fun `dDayDiscount 메서드 사용 시 12월 1일 방문 고객의 할인 금액은 1000 원`() {
            val visitDay = VisitDay(1)
            val orders = Orders(listOf(Order(ICE_CREAM, 1), Order(SEAFOOD_PASTA, 1)))

            assertThat(discountCalculator.dDayDiscount(visitDay, orders)).isEqualTo(1000)
        }

        @Test
        @DisplayName("12월 5일")
        fun `dDayDiscount 메서드 사용 시 12월 5일 방문 고객의 할인 금액은 1400 원`() {
            val visitDay = VisitDay(5)
            val orders = Orders(listOf(Order(ICE_CREAM, 1), Order(SEAFOOD_PASTA, 1)))

            assertThat(discountCalculator.dDayDiscount(visitDay, orders)).isEqualTo(1400)
        }

        @Test
        @DisplayName("크리스마스 이후")
        fun `dDayDiscount 메서드 사용 시 크리스마스 이후 방문 고객의 할인 금액은 0 원`() {
            val visitDay = VisitDay(26)
            val orders = Orders(listOf(Order(ICE_CREAM, 1), Order(SEAFOOD_PASTA, 1)))

            assertThat(discountCalculator.dDayDiscount(visitDay, orders)).isEqualTo(0)
        }

        @Test
        @DisplayName("총 주문 금액 미달")
        fun `dDayDiscount 메서드 사용 시 총 주문 금액 10,000 원 미만인 방문 고객의 할인 금액은 0 원`() {
            val visitDay = VisitDay(1)
            val orders = Orders(listOf(Order(ICE_CREAM, 1)))

            assertThat(discountCalculator.dDayDiscount(visitDay, orders)).isEqualTo(0)
        }
    }

    @Nested
    @DisplayName("평일 할인")
    inner class WeekDayDiscount {

        @Test
        @DisplayName("디저트 1개")
        fun `weekDayDiscount 메서드 사용 시 총 주문의 디저트가 1 개인 평일 방문 고객의 할인 금액은 2023 원`() {
            val visitDay = VisitDay(weekDay)
            val orders = Orders(listOf(Order(ICE_CREAM, 1), Order(SEAFOOD_PASTA, 1)))

            assertThat(discountCalculator.weekDayDiscount(visitDay, orders)).isEqualTo(WEEK_DAY_DISCOUNT)
        }

        @Test
        @DisplayName("디저트 없음")
        fun `weekDayDiscount 메서드 사용 시 디저트를 주문하지 않은 평일 방문 고객의 할인 금액은 0 원`() {
            val visitDay = VisitDay(weekDay)
            val orders = Orders(listOf(Order(SEAFOOD_PASTA, 1)))

            assertThat(discountCalculator.weekDayDiscount(visitDay, orders)).isEqualTo(0)
        }

        @Test
        @DisplayName("평일 이외 방문")
        fun `weekDayDiscount 메서드 사용 시 평일 이외 방문 고객의 할인 금액은 0 원`() {
            val visitDay = VisitDay(weekEnd)
            val orders = Orders(listOf(Order(ICE_CREAM, 1), Order(SEAFOOD_PASTA, 1)))

            assertThat(discountCalculator.weekDayDiscount(visitDay, orders)).isEqualTo(0)
        }

        @Test
        @DisplayName("총 주문 금액 미달")
        fun `weekDayDiscount 메서드 사용 시 주문 금액이 10,000 원 미만인 방문 고객의 할인 금액은 0 원`() {
            val visitDay = VisitDay(weekDay)
            val orders = Orders(listOf(Order(ICE_CREAM, 1)))

            assertThat(discountCalculator.weekDayDiscount(visitDay, orders)).isEqualTo(0)
        }
    }

    @Nested
    @DisplayName("주말 할인")
    inner class WeekEndDiscount {

        @Test
        @DisplayName("메인 요리 1개")
        fun `weekEndDiscount 메서드 사용 시 총 주문의 메인 요리가 1 개인 주말 방문 고객의 할인 금액은 2023 원`() {
            val visitDay = VisitDay(weekEnd)
            val orders = Orders(listOf(Order(ICE_CREAM, 1), Order(SEAFOOD_PASTA, 1)))

            assertThat(discountCalculator.weekEndDiscount(visitDay, orders)).isEqualTo(WEEK_END_DISCOUNT)
        }

        @Test
        @DisplayName("메인 요리 없음")
        fun `weekEndDiscount 메서드 사용 시 메인 요리를 주문하지 않은 주말 방문 고객의 할인 금액은 0 원`() {
            val visitDay = VisitDay(weekEnd)
            val orders = Orders(listOf(Order(TAPAS, 2), Order(ICE_CREAM, 1)))

            assertThat(discountCalculator.weekEndDiscount(visitDay, orders)).isEqualTo(0)
        }

        @Test
        @DisplayName("주말 이외 방문")
        fun `weekEndDiscount 메서드 사용 시 주말 이외 방문 고객의 할인 금액은 0 원`() {
            val visitDay = VisitDay(weekDay)
            val orders = Orders(listOf(Order(ICE_CREAM, 1), Order(SEAFOOD_PASTA, 1)))

            assertThat(discountCalculator.weekEndDiscount(visitDay, orders)).isEqualTo(0)
        }
    }

    @Nested
    @DisplayName("특별 할인")
    inner class SpecialDayDiscount {

        @Test
        @DisplayName("특별일 방문")
        fun `specialDayDiscount 메서드 사용 시 특별일 방문 고객의 할인 금액은 1,000 원`() {
            val visitDay = VisitDay(specialDay)
            val orders = Orders(listOf(Order(TAPAS, 2), Order(ICE_CREAM, 1)))

            assertThat(discountCalculator.specialDayDiscount(visitDay, orders)).isEqualTo(SPECIAL_DAY_DISCOUNT)
        }

        @Test
        @DisplayName("특별일 이외 방문")
        fun `specialDayDiscount 메서드 사용 시 특별일 이외 방문 고객의 할인 금액은 0 원`() {
            val visitDay = VisitDay(weekEnd)
            val orders = Orders(listOf(Order(TAPAS, 2), Order(ICE_CREAM, 1)))

            assertThat(discountCalculator.specialDayDiscount(visitDay, orders)).isEqualTo(0)
        }

        @Test
        @DisplayName("총 주문 금액 미달")
        fun `specialDayDiscount 메서드 사용 시 주문 금액이 10,000 원 미만인 방문 고객의 할인 금액은 0 원`() {
            val visitDay = VisitDay(specialDay)
            val orders = Orders(listOf(Order(ICE_CREAM, 1)))

            assertThat(discountCalculator.specialDayDiscount(visitDay, orders)).isEqualTo(0)
        }
    }

    companion object {
        private const val WEEK_DAY_DISCOUNT = 2023
        private const val WEEK_END_DISCOUNT = 2023
        private const val SPECIAL_DAY_DISCOUNT = 1000
    }
}