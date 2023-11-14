package christmas.domain

import christmas.InputView
import christmas.OutputView
import christmas.state.EventPlannerState.*
import java.lang.IllegalArgumentException

class EventPlanner {
    private val inputView = InputView()
    private val outputView = OutputView()
    private var eventPlannerState = NORMAL
    private lateinit var visitDay: VisitDay

    fun run() {
        selectVisitDay()
    }

    private fun selectVisitDay() {
        outputView.printVisitDayRequest()

        setValidVisitDay()
    }

    private fun setValidVisitDay() {
        do {
            setVisitDay()
        } while (isEventPlannerOnError())
    }

    private fun setVisitDay() {
        try {
            visitDay = VisitDay(inputView.readNumber())
            eventPlannerState = NORMAL
        } catch (e: IllegalArgumentException) {
            outputView.printVisitDayError()
            eventPlannerState = ERROR
        }
    }

    private fun isEventPlannerOnError() = eventPlannerState == ERROR
}