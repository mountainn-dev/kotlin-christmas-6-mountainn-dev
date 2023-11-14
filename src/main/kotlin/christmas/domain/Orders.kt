package christmas.domain

class Orders(private val orders: List<Order>) {

    init {
        require(hasNotSameMenu())
        require(hasNotOnlyDrink())
        require(isUnderMinOrderCount())
    }

    private fun hasNotSameMenu(): Boolean {
        val menuNameGroup = orders.groupBy { it.menu().menuName }.mapValues { it.value.size }

        return menuNameGroup.all { it.value == 1 }
    }

    private fun hasNotOnlyDrink() = orders.map { it.isDrink() }.contains(false)

    private fun isUnderMinOrderCount(): Boolean {
        var totalCount = 0

        orders.map { totalCount += it.count() }

        return totalCount <= MAX_ORDER_COUNT
    }

    companion object {
        private const val MAX_ORDER_COUNT = 20
    }
}