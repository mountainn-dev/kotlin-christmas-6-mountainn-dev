package christmas.controller

import christmas.OutputView
import christmas.domain.PromotionBenefitCalculator
import christmas.domain.Orders
import christmas.domain.VisitDay

class PromotionController {
    private val outputView = OutputView()
    private val promotionBenefitCalculator = PromotionBenefitCalculator()

    fun apply(visitDay: VisitDay, orders: Orders) {

    }
}