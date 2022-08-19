package indigo.tools

open class CardHolder(var cards: MutableList<Card> = mutableListOf()) {
    fun take(cardsToAdd: List<Card>) {
        this.cards.addAll(cardsToAdd)
    }

    override fun toString(): String {
        return cards.joinToString(" ")
    }
}