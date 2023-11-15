package christmas.domain

enum class Badge(val badgeName: String, val minimumBenefit: Int) {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000)
}