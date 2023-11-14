package christmas.domain

import christmas.InputValidator
import christmas.InputView
import christmas.OutputView
import christmas.domain.menu.*
import christmas.state.OrdersControllerState.*
import java.lang.IllegalArgumentException

class OrdersController {
    private val inputView = InputView()
    private val outputView = OutputView()
    private var controllerState = NORMAL
    private lateinit var orders: Orders
    private lateinit var order: Order

    fun order() {
        outputView.printOrderRequest()

        setValidOrders()
    }

    private fun setValidOrders() {
        do {
            setOrders()
        } while (isControllerOnError())
    }

    private fun setOrders() {
        try {
            orders = Orders(ordersFrom(inputView.readWords()))
            controllerState = NORMAL
        } catch (e: IllegalArgumentException) {
            outputView.printOrderError()
            controllerState = ERROR
        }
    }

    private fun ordersFrom(words: List<String>): MutableList<Order> {
        val orders = mutableListOf<Order>()

        words.map {
            setOrder(it)
            orders.add(order)
        }

        return orders
    }

    private fun setOrder(words: String) {
        validateWordsAsOrder(words)
        order = Order(menuFrom(words), menuCountFrom(words))
    }

    private fun menuFrom(words: String): Orderable {
        return findMenuByName(words.split(DASH)[0]) ?: throw IllegalArgumentException()
    }

    private fun menuCountFrom(words: String): Int {
        val inputValidator = InputValidator()
        val input = words.split(DASH)[1]

        inputValidator.checkIsNotBlank(input)
        inputValidator.checkIsDigit(input)

        return input.toInt()
    }

    private fun validateWordsAsOrder(words: String) {
        val inputValidator = InputValidator()

        inputValidator.checkHasDashSeparator(words)
    }

    private fun findMenuByName(menuName: String): Orderable? {
        val appetizer = Appetizer.values().find { it.menuName == menuName }
        val mainDish = MainDish.values().find { it.menuName == menuName }
        val dessert = Dessert.values().find { it.menuName == menuName }
        val drink = Drink.values().find { it.menuName == menuName }

        if (appetizer != null) return appetizer
        if (mainDish != null) return mainDish
        if (dessert != null) return dessert
        if (drink != null) return drink
        return null
    }

    private fun isControllerOnError() = controllerState == ERROR

    companion object {
        private const val DASH = "-"
    }
}