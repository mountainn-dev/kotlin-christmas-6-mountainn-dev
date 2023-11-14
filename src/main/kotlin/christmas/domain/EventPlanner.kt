package christmas.domain

import christmas.InputView
import christmas.OutputView
import christmas.constants.Request
import christmas.state.InputViewState.*
import christmas.state.OutputViewState.*

class EventPlanner {
    private val inputView = InputView()
    private val outputView = OutputView()
    private lateinit var visitDay: VisitDay

    fun run() {
        selectVisitDay()
    }

    private fun selectVisitDay() {
        visitDay = VisitDay(getDayFromUser())
    }

    private fun getDayFromUser() = inputView.readNumberWithMessage(Request.REQUEST_VISIT_DAY_MESSAGE)
}