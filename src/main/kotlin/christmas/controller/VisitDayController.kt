package christmas.controller

import christmas.InputView
import christmas.OutputView
import christmas.domain.VisitDay
import christmas.state.VisitDayControllerState.*
import java.lang.IllegalArgumentException

class VisitDayController {
    private val inputView = InputView()
    private val outputView = OutputView()
    private var controllerState = NORMAL
    private lateinit var visitDay: VisitDay

    fun getVisitDay() = this.visitDay

    fun selectVisitDay() {
        outputView.printVisitDayRequest()

        setValidVisitDay()
    }

    private fun setValidVisitDay() {
        do {
            setVisitDay()
        } while (isControllerOnError())
    }

    private fun setVisitDay() {
        try {
            visitDay = VisitDay(inputView.readNumber())
            controllerState = NORMAL
        } catch (e: IllegalArgumentException) {
            outputView.printVisitDayError()
            controllerState = ERROR
        }
    }

    private fun isControllerOnError() = controllerState == ERROR
}