package christmas.domain.menu

enum class Drink(val menuName: String, val price: Int): Orderable {
    ZERO_COKE("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000)
}