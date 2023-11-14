package christmas.domain

class VisitDay(day: Int) {

    init {
        require(day in MIN_DAY_OF_MONTH..MAX_DAY_OF_MONTH)
    }

    companion object {
        private const val MIN_DAY_OF_MONTH = 1
        private const val MAX_DAY_OF_MONTH = 31
    }
}