package christmas

import christmas.constants.Exception
import christmas.constants.Request
import christmas.constants.Result
import christmas.domain.Badge
import christmas.domain.Order
import christmas.domain.Orders
import christmas.domain.VisitDay

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

    fun printPromotionTitle(visitDay: VisitDay) {
        println(String.format(Result.TITLE_RESULT_PROMOTION_MESSAGE, visitDay.day()))
    }

    fun printOrderMenu(orders: Orders) {
        printTitle(Result.TITLE_ORDER_MENU)
        printOrderMenuMessage(orders)
    }

    private fun printOrderMenuMessage(orders: Orders) {
        val menuNames = orders.menuNames()
        val menuCounts = orders.menuCounts()

        for (i in orders.menuNames().indices) {
            println(String.format(Result.ORDER_MENU_MESSAGE, menuNames[i], menuCounts[i]))
        }
    }

    fun printTotalBeforePromotion(orders: Orders) {
        printTitle(Result.TITLE_TOTAL_BEFORE_PROMOTION)
        printTotalBeforePromotionMessage(orders)
    }

    private fun printTotalBeforePromotionMessage(orders: Orders) {
        println(String.format(Result.TOTAL_MESSAGE, orders.total()))
    }

    fun printFreebieMenu(order: Order) {
        printTitle(Result.TITLE_FREEBIE_MENU)
        printFreebieMenuMessage(order)
    }

    fun printNoFreebieMenu() {
        printTitle(Result.TITLE_FREEBIE_MENU)
        printNoPromotion()
    }

    private fun printFreebieMenuMessage(order: Order) {
        println(String.format(Result.ORDER_MENU_MESSAGE, order.menu().menuName, order.count()))
    }

    fun printBenefitList(promotionNames: List<String>, benefits: List<Int>) {
        printTitle(Result.TITLE_BENEFIT_LIST)
        printBenefitListMessage(promotionNames, benefits)
    }

    fun printNoBenefitList() {
        printTitle(Result.TITLE_BENEFIT_LIST)
        printNoPromotion()
    }

    private fun printBenefitListMessage(promotionNames: List<String>, benefits: List<Int>) {
        for (i in promotionNames.indices) {
            println(String.format(Result.BENEFIT_MESSAGE, promotionNames[i], benefits[i]))
        }
    }

    fun printTotalBenefits(totalBenefits: Int) {
        printTitle(Result.TITLE_TOTAL_BENEFITS)
        printTotalBenefitsMessage(totalBenefits)
    }

    fun printNoTotalBenefits() {
        printTitle(Result.TITLE_TOTAL_BENEFITS)
        printNoPromotion()
    }

    private fun printTotalBenefitsMessage(totalBenefits: Int) {
        println(String.format(Result.TOTAL_MESSAGE, totalBenefits))
    }

    fun printTotalAfterPromotion(orders: Orders, totalBenefits: Int) {
        printTitle(Result.TITLE_TOTAL_AFTER_PROMOTION)
        printTotalAfterPromotionMessage(orders, totalBenefits)
    }

    private fun printTotalAfterPromotionMessage(orders: Orders, totalBenefits: Int) {
        println(String.format(Result.TOTAL_MESSAGE, orders.total() - totalBenefits))
    }

    fun printEventBadge(badge: Badge) {
        printTitle(Result.TITLE_DECEMBER_EVENT_BADGE)
        printEventBadgeMessage(badge)
    }

    fun printNoEventBadge() {
        printTitle(Result.TITLE_DECEMBER_EVENT_BADGE)
        printNoPromotion()
    }

    private fun printEventBadgeMessage(badge: Badge) {
        println(String.format(Result.BADGE_MESSAGE, badge.badgeName))
    }

    private fun printTitle(title: String) {
        println(NEW_LINE + String.format(Result.TITLE_MESSAGE, title))
    }

    private fun printNoPromotion() {
        println(NONE)
    }

    companion object {
        private const val ERROR = "[ERROR]"
        private const val BLANK = " "
        private const val NEW_LINE = "\n"
        private const val NONE = "없음"
    }
}