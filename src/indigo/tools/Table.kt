package indigo.tools

class Table(cards: MutableList<Card> =  mutableListOf()): CardHolder(cards) {

    fun top() : Card? {
        if (cards.isEmpty()) return null
        return cards.last()
    }

    fun printStats() {
        println()
        if (cards.isEmpty())
            println("No cards on the table")
        else
            println("${cards.size} cards on the table, and the top card is ${top()}")
    }

    fun countCurrentScore(): Int {
        var score = 0
        for (card in cards) {
            score += card.cardScore()
        }
        return score
    }

    fun cleanTheTable() {
        this.cards.clear()
    }
}