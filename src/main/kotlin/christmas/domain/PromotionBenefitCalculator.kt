package christmas.domain

import christmas.domain.promotion.Discount.*
import christmas.domain.promotion.Freebie.*

class PromotionBenefitCalculator {

    fun dDayDiscount(visitDay: VisitDay, orders: Orders): Int {
        if (!satisfyDDayDiscountRequirements(visitDay, orders)) return NO_DISCOUNT

        return D_DAY_DISCOUNT.price + (D_DAY_DISCOUNT_UNIT * visitDay.daySinceDecemberFirst())
    }

    private fun satisfyDDayDiscountRequirements(visitDay: VisitDay, orders: Orders): Boolean {
        return isVisitDayBeforeChristmas(visitDay) && isTotalOverMinimumPromotionValue(orders)
    }

    fun weekDayDiscount(visitDay: VisitDay, orders: Orders): Int {
        if (!satisfyWeekDayDiscountRequirements(visitDay, orders)) return NO_DISCOUNT

        return WEEK_DAY_DISCOUNT.price * orders.totalDessertCount()
    }

    private fun satisfyWeekDayDiscountRequirements(visitDay: VisitDay, orders: Orders): Boolean {
        return visitDay.isWeekDay() && orders.totalDessertCount() > 0 && isTotalOverMinimumPromotionValue(orders)
    }

    fun weekEndDiscount(visitDay: VisitDay, orders: Orders): Int {
        if (!satisfyWeekEndDiscountRequirements(visitDay, orders)) return NO_DISCOUNT

        return WEEK_END_DISCOUNT.price * orders.totalMainDishCount()
    }

    private fun satisfyWeekEndDiscountRequirements(visitDay: VisitDay, orders: Orders): Boolean {
        return visitDay.isWeekEnd() && orders.totalMainDishCount() > 0 && isTotalOverMinimumPromotionValue(orders)
    }

    fun specialDayDiscount(visitDay: VisitDay, orders: Orders): Int {
        if (!satisfySpecialDayDiscountRequirements(visitDay, orders)) return NO_DISCOUNT

        return SPECIAL_DAY_DISCOUNT.price
    }

    private fun satisfySpecialDayDiscountRequirements(visitDay: VisitDay, orders: Orders): Boolean {
        return visitDay.isSpecialDay() && isTotalOverMinimumPromotionValue(orders)
    }

    fun champagneFreebie(orders: Orders): Int {
        if (!satisfyChampagneFreebieRequirements(orders)) return NO_DISCOUNT

        return CHAMPAGNE_FREEBIE.product.price
    }

    private fun satisfyChampagneFreebieRequirements(orders: Orders): Boolean {
        return orders.total() >= FREEBIE_AVAILABLE_PRICE
    }

    private fun isVisitDayBeforeChristmas(visitDay: VisitDay) = visitDay.daySinceDecemberFirst() < X_MAS
    private fun isTotalOverMinimumPromotionValue(orders: Orders) = orders.total() >= MINIMUM_PROMOTION_AVAILABLE_PRICE

    companion object {
        private const val D_DAY_DISCOUNT_UNIT = 100
        private const val X_MAS = 25
        private const val MINIMUM_PROMOTION_AVAILABLE_PRICE = 10000
        private const val FREEBIE_AVAILABLE_PRICE = 120000
        private const val NO_DISCOUNT = 0
    }
}