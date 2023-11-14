package christmas.domain

import christmas.InputView
import christmas.OutputView
import christmas.domain.menu.*
import christmas.state.OrdersControllerState.*

class OrdersController {
    private val inputView = InputView()
    private val outputView = OutputView()
    private var controllerState = NORMAL
    private lateinit var orders: Orders
    private lateinit var order: Order

    fun order() {
        outputView.printOrderRequest()

        setOrders()
    }

    private fun setOrders() {
        val orders = mutableListOf<Order>()

        inputView.readWords().map {
            orders.add(Order(findMenuByName(getMenuNameInWords(it)), getMenuCountInWords(it)))
        }

        this.orders = Orders(orders)
    }

    private fun getMenuNameInWords(words: String) = words.split(DASH)[0]

    private fun getMenuCountInWords(words: String) = words.split(DASH)[1]

    private fun findMenuByName(menuName: String): Orderable {
        val appetizer = Appetizer.values().find { it.menuName == menuName }
        val mainDish = MainDish.values().find { it.menuName == menuName }
        val dessert = Dessert.values().find { it.menuName == menuName }
        val drink = Drink.values().find { it.menuName == menuName }

        if (appetizer != null) return appetizer
        if (mainDish != null) return mainDish
        if (dessert != null) return dessert
        if (drink != null) return drink
    }

    companion object {
        private const val DASH = "-"
    }
}