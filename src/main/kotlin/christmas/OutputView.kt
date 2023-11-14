package christmas

import christmas.constants.Exception
import christmas.constants.Request

class OutputView {

    fun printVisitDayRequest() {
        println(Request.REQUEST_VISIT_DAY_MESSAGE)
    }

    fun printOrderRequest() {
        println(Request.REQUEST_ORDER_MESSAGE)
    }

    fun printVisitDayError() {
        println(ERROR + BLANK + Exception.ERROR_VISIT_DAY_MESSAGE)
    }

    fun printOrderError() {
        println(ERROR + BLANK + Exception.ERROR_ORDER_MESSAGE)
    }

    companion object {
        private const val ERROR = "[ERROR]"
        private const val BLANK = " "
    }
}