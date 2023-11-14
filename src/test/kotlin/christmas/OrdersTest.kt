package christmas

import christmas.domain.Order
import christmas.domain.Orders
import christmas.domain.menu.Dessert.*
import christmas.domain.menu.Drink.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.assertj.core.api.Assertions.assertThat

class OrdersTest {

    @Nested
    @DisplayName("주문 목록 유효성")
    inner class OrdersValidation {

        @Test
        @DisplayName("메뉴 중복")
        fun `동일한 메뉴를 중복하여 주문하는 경우 예외 발생`() {
            assertThrows<IllegalArgumentException> {
                Orders(listOf(Order(ICE_CREAM, 1), Order(ICE_CREAM, 2)))
            }
        }

        @Test
        @DisplayName("음료만 주문")
        fun `음료만 주문하는 경우 예외 발생`() {
            assertThrows<IllegalArgumentException> {
                Orders(listOf(Order(ZERO_COKE, 1), Order(CHAMPAGNE, 1)))
            }
        }

        @Test
        @DisplayName("주문 개수 초과")
        fun `20개를 초과하여 메뉴를 주문하는 경우 예외 발생`() {
            assertThrows<IllegalArgumentException> {
                Orders(listOf(Order(ICE_CREAM, 10), Order(CHAMPAGNE, 11)))
            }
        }
    }

    @Nested
    @DisplayName("주문 목록 메서드")
    inner class OrdersMethod {

        @Test
        @DisplayName("총액 확인")
        fun `total 메서드 사용 시 전체 주문의 총액 반환`() {
            val orders = Orders(listOf(Order(ICE_CREAM, 1), Order(ZERO_COKE, 1)))

            assertThat(orders.total()).isEqualTo((ICE_CREAM.price * 1) + (ZERO_COKE.price * 1))
        }
    }
}