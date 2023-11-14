package christmas.domain.menu

enum class Appetizer(override val menuName: String, override val price: Int): Orderable {
    MUSHROOM_CREAM_SOUP("양송이수프", 6000),
    TAPAS("타파스", 5500),
    CAESAR_SALAD("시저샐러드", 8000)
}