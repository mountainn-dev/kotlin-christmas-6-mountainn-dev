package christmas.domain.promotion

import christmas.domain.Order
import christmas.domain.menu.Drink.*

enum class Freebie(val freebieName: String, val product: Order) {
    CHAMPAGNE_FREEBIE("증정 이벤트", Order(CHAMPAGNE, 1))
}