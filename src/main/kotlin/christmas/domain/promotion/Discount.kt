package christmas.domain.promotion

enum class Discount(val discountName: String, val price: Int) {
    D_DAY_DISCOUNT("크리스마스 디데이 할인", 1000),
    WEEK_DAY_DISCOUNT("평일 할인", 2023),
    WEEK_END_DISCOUNT("주말 할인", 2023),
    SPECIAL_DAY_DISCOUNT("특별 할인", 1000)
}