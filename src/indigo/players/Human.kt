package indigo.players

import indigo.tools.Card

class Human(name: String = "Player",
            cards: MutableList<Card> =  mutableListOf(),
            score: Int = 0,
            winnedCards: MutableList<Card> = mutableListOf())
    : Player(name, cards, score, winnedCards) {

    override fun makeMove(topCard: Card?): Card {
        println("Cards in hand: $this")
        while(true) {
            println("Choose a card to play (1-${this.amountOfCardsOnHand()}):")
            val command = readLine()!!
            if (command == "exit") throw Exception("User Exit")
            val cardNum = command.toIntOrNull()
            if (cardNum != null && cardNum >= 1 && cardNum <= this.amountOfCardsOnHand()) {
                return cards.removeAt(cardNum - 1)
            }
        }
    }

    override fun toString(): String {
        var hand = ""
        for ((count, card) in cards.withIndex()) {
            hand += "${count + 1})$card "
        }
        return hand.trim()
    }
}