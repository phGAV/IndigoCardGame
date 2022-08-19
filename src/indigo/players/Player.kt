package indigo.players

import indigo.tools.Card
import indigo.tools.CardHolder

open class Player(val name: String = "Unknown",
                  cards: MutableList<Card> = mutableListOf(),
                  var score: Int = 0,
                  var winnedCards: MutableList<Card> = mutableListOf())
    : CardHolder(cards) {

    open fun makeMove(topCard: Card? = null): Card? {
        return null
    }

    open fun amountOfCardsOnHand(): Int {
        return this.cards.size
    }

    open fun amountOfCardsWon() : Int {
        return this.winnedCards.size
    }

    open fun winCards(cards: List<Card>) {
        winnedCards.addAll(cards)
    }

    open fun increaseScore(score: Int) {
        this.score += score
    }
}