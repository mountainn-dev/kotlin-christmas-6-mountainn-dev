package christmas

import christmas.domain.Order
import christmas.domain.menu.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.assertj.core.api.Assertions.assertThat

class OrderTest {

    @Nested
    @DisplayName("주문 유효성")
    inner class OrderValidation {

        @ParameterizedTest
        @ValueSource(strings = ["양송이스프", "양송이 수프"])
        @DisplayName("메뉴 이름 불일치")
        fun `메뉴판에 없는 메뉴를 주문하는 경우 예외 발생`(menu: String) {
            assertThrows<IllegalArgumentException> {
                Order(menu, 1)
            }
        }

        @ParameterizedTest
        @ValueSource(ints = [0, -1])
        @DisplayName("주문 개수 미달")
        fun `1개 미만으로 메뉴를 주문하는 경우 예외 발생`(count: Int) {
            assertThrows<IllegalArgumentException> {
                Order(SEAFOOD_PASTA, count)
            }
        }
    }

    @Nested
    @DisplayName("주문 메서드")
    inner class OrderMethod {

        @Test
        @DisplayName("총액 확인")
        fun `total 메서드 사용 시 해당 주문의 총액 반환`() {
            val order = Order(SEAFOOD_PASTA, 1)

            assertThat(order.total()).isEqualTo(SEAFOOD_PASTA.price)
        }

        @Test
        @DisplayName("에피타이저 확인")
        fun `isAppetizer 메서드 사용 시 해당 주문이 에피타이저인지 확인`() {
            val order1 = Order(TAPAS, 1)
            val order2 = Order(SEAFOOD_PASTA, 1)

            assertThat(order1.isAppetizer()).isEqualTo(true)
            assertThat(order2.isAppetizer()).isEqualTo(false)
        }

        @Test
        @DisplayName("메인 요리 확인")
        fun `isMainDish 메서드 사용 시 해당 주문이 메인 요리인지 확인`() {
            val order1 = Order(SEAFOOD_PASTA, 1)
            val order2 = Order(TAPAS, 1)

            assertThat(order1.isMainDish()).isEqualTo(true)
            assertThat(order2.isMainDish()).isEqualTo(false)
        }

        @Test
        @DisplayName("디저트 확인")
        fun `isDessert 메서드 사용 시 해당 주문이 디저트인지 확인`() {
            val order1 = Order(ICECREAM, 1)
            val order2 = Order(TAPAS, 1)

            assertThat(order1.isDessert()).isEqualTo(true)
            assertThat(order2.isDessert()).isEqualTo(false)
        }

        @Test
        @DisplayName("음료 확인")
        fun `isDrink 메서드 사용 시 해당 주문이 음료인지 확인`() {
            val order1 = Order(ZERO_COKE, 1)
            val order2 = Order(TAPAS, 1)

            assertThat(order1.isDrink()).isEqualTo(true)
            assertThat(order2.isDrink()).isEqualTo(false)
        }
    }
}