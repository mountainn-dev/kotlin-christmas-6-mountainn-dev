package christmas

import christmas.domain.OrdersController
import christmas.domain.VisitDayController

class EventPlanner {
    private val visitDayController = VisitDayController()
    private val ordersController = OrdersController()

    fun run() {
        visitDayController.selectVisitDay()
        ordersController.order()
    }

}