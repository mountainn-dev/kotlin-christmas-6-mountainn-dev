package christmas

import christmas.controller.PromotionController
import christmas.controller.OrdersController
import christmas.controller.VisitDayController

class EventPlanner {
    private val visitDayController = VisitDayController()
    private val ordersController = OrdersController()
    private val promotionController = PromotionController()

    fun run() {
        selectVisitDay()
        order()
        applyPromotion()
    }

    private fun selectVisitDay() {
        visitDayController.selectVisitDay()
    }

    private fun order() {
        ordersController.order()
    }

    private fun applyPromotion() {
        promotionController.apply(
            visitDayController.getVisitDay(), ordersController.getOrders()
        )
    }
}