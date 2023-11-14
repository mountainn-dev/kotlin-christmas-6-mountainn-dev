package christmas

import christmas.domain.VisitDayController

class EventPlanner {
    private val visitDayController = VisitDayController()

    fun run() {
        visitDayController.selectVisitDay()
    }
    
}