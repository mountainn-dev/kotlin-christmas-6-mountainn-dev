package christmas.domain

import christmas.domain.menu.*

class Order(private val menu: Orderable, private val count: Int) {

    init {
        require(count >= MIN_ORDER_COUNT)
    }

    fun menu() = menu

    fun count() = count

    fun total() = menu.price * count

    fun isAppetizer() = menu is Appetizer

    fun isMainDish() = menu is MainDish

    fun isDessert() = menu is Dessert

    fun isDrink() = menu is Drink

    companion object {
        private const val MIN_ORDER_COUNT = 1
    }
}