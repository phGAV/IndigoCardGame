package indigo.tools

const val INITIAL_AMOUNT = 4
const val HAND = 6

class CardDeck {
    private val cards: MutableList<Card> =  mutableListOf()

    init {
        for (s in Suit.values()) {
            for (r in Rank.values()) {
                cards.add(Card(r, s))
            }
        }
    }

    fun shuffle() {
        cards.shuffle()
    }

    fun get(numberOfCards: Int): List<Card> {
        val showCards = cards.take(numberOfCards)
        cards.removeAll(showCards)
        return showCards
    }

    override fun toString(): String {
        return cards.joinToString(" ")
    }

    fun empty(): Boolean {
        return cards.isEmpty()
    }
}