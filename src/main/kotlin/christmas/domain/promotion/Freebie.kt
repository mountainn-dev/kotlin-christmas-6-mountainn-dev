package christmas.domain.promotion

import christmas.domain.menu.Drink.*
import christmas.domain.menu.Orderable

enum class Freebie(val freebieName: String, val product: Orderable) {
    CHAMPAGNE_FREEBIE("증정 이벤트", CHAMPAGNE)
}