package christmas.domain.menu

enum class MainDish(override val menuName: String, override val price: Int): Orderable {
    T_BONE_STEAK("티본스테이크", 55000),
    BARBEQUE_RIB("바비큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000)
}