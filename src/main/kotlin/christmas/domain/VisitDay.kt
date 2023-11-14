package christmas.domain

import christmas.domain.Calendar.*

class VisitDay(private val day: Int) {

    init {
        require(day in MIN_DAY_OF_MONTH..MAX_DAY_OF_MONTH)
    }

    fun daySinceDecemberFirst() = day - DECEMBER_FIRST

    fun isWeekDay() = day in WEEK_DAY.days

    fun isWeekEnd() = day in WEEK_END.days

    fun isSpecialDay() = day in SPECIAL_DAY.days

    companion object {
        private const val MIN_DAY_OF_MONTH = 1
        private const val MAX_DAY_OF_MONTH = 31
        private const val DECEMBER_FIRST = 1
    }
}