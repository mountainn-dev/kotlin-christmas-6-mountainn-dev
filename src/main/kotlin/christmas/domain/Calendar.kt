package christmas.domain

enum class Calendar(val days: List<Int>) {
    WEEK_DAY(listOf(3,4,5,6,7,10,11,12,13,14,17,18,19,20,21,24,25,26,27,28)),
    WEEK_END(listOf(1,2,8,9,15,16,22,23,29,30)),
    SPECIAL_DAY(listOf(3,10,17,24,25,31))
}