package christmas.controller

import christmas.OutputView
import christmas.domain.promotion.Discount.*
import christmas.domain.promotion.Freebie.*
import christmas.domain.Badge.*
import christmas.domain.PromotionBenefitCalculator
import christmas.domain.Orders
import christmas.domain.VisitDay

class PromotionController {
    private val outputView = OutputView()
    private val promotionBenefitCalculator = PromotionBenefitCalculator()
    private var dDayDiscount = ZERO
    private var weekDayDiscount = ZERO
    private var weekEndDiscount = ZERO
    private var specialDayDiscount = ZERO
    private var champagneFreebie = ZERO


    fun apply(visitDay: VisitDay, orders: Orders) {
        applyPromotion(visitDay, orders)
        showPromotionResult(visitDay, orders)
    }

    private fun applyPromotion(visitDay: VisitDay, orders: Orders) {
        dDayDiscount =  promotionBenefitCalculator.dDayDiscount(visitDay, orders)
        weekDayDiscount =  promotionBenefitCalculator.weekDayDiscount(visitDay, orders)
        weekEndDiscount =  promotionBenefitCalculator.weekEndDiscount(visitDay, orders)
        specialDayDiscount = promotionBenefitCalculator.specialDayDiscount(visitDay, orders)
        champagneFreebie = promotionBenefitCalculator.champagneFreebie(orders)
    }

    private fun showPromotionResult(visitDay: VisitDay, orders: Orders) {
        showResultTitle(visitDay)
        showOrderMenu(orders)
        showTotalBeforePromotion(orders)
        showFreebieMenu()
        showBenefitList()
        showTotalBenefits()
        showTotalAfterPromotion(orders)
        showEventBadge()
    }

    private fun showResultTitle(visitDay: VisitDay) {
        outputView.printPromotionTitle(visitDay)
    }

    private fun showOrderMenu(orders: Orders) {
        outputView.printOrderMenu(orders)
    }

    private fun showTotalBeforePromotion(orders: Orders) {
        outputView.printTotalBeforePromotion(orders)
    }

    private fun showFreebieMenu() {
        if (hasFreebie()) outputView.printFreebieMenu(CHAMPAGNE_FREEBIE.product)
        else outputView.printNoFreebieMenu()
    }

    private fun showBenefitList() {
        if (hasAnyPromotion()) outputView.printBenefitList(getPromotionNames(), getBenefits())
        else outputView.printNoBenefitList()
    }

    private fun getPromotionNames(): List<String> {
        val promotionNames = mutableListOf<String>()

        if (dDayDiscount != ZERO) promotionNames.add(D_DAY_DISCOUNT.discountName)
        if (weekDayDiscount != ZERO) promotionNames.add(WEEK_DAY_DISCOUNT.discountName)
        if (weekEndDiscount != ZERO) promotionNames.add(WEEK_END_DISCOUNT.discountName)
        if (specialDayDiscount != ZERO) promotionNames.add(SPECIAL_DAY_DISCOUNT.discountName)
        if (champagneFreebie != ZERO) promotionNames.add(CHAMPAGNE_FREEBIE.freebieName)

        return promotionNames
    }

    private fun getBenefits(): List<Int> {
        val benefits = mutableListOf<Int>()

        if (dDayDiscount != ZERO) benefits.add(dDayDiscount)
        if (weekDayDiscount != ZERO) benefits.add(weekDayDiscount)
        if (weekEndDiscount != ZERO) benefits.add(weekEndDiscount)
        if (specialDayDiscount != ZERO) benefits.add(specialDayDiscount)
        if (champagneFreebie != ZERO) benefits.add(champagneFreebie)

        return benefits
    }

    private fun showTotalBenefits() {
        if (hasAnyPromotion()) outputView.printTotalBenefits(totalBenefits())
        else outputView.printNoTotalBenefits()
    }

    private fun showTotalAfterPromotion(orders: Orders) {
        outputView.printTotalAfterPromotion(orders, totalBenefits() - champagneFreebie)
    }

    private fun showEventBadge() {
        if (isStarBadge()) outputView.printEventBadge(STAR)
        else if (isTreeBadge()) outputView.printEventBadge(TREE)
        else if (isSantaBadge()) outputView.printEventBadge(SANTA)
        else outputView.printNoEventBadge()
    }

    private fun hasFreebie() = champagneFreebie != ZERO
    private fun hasAnyPromotion() = totalBenefits() != ZERO
    private fun totalBenefits() =
        dDayDiscount + weekDayDiscount + weekEndDiscount + specialDayDiscount + champagneFreebie
    private fun isStarBadge() = totalBenefits() >= STAR.minimumBenefit
    private fun isTreeBadge() = totalBenefits() >= TREE.minimumBenefit
    private fun isSantaBadge() = totalBenefits() >= SANTA.minimumBenefit

    companion object {
        private const val ZERO = 0
    }
}