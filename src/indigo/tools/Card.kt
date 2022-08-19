package indigo.tools

enum class Rank(val pic: String, val score: Int) {
    ACE("A", 1),
    TWO("2", 0),
    THREE("3", 0),
    FOUR("4", 0),
    FIVE("5", 0),
    SIX("6", 0),
    SEVEN("7", 0),
    EIGHT("8", 0),
    NINE("9", 0),
    TEN("10", 1),
    JACK("J", 1),
    QUEEN("Q", 1),
    KING("K", 1)
}

enum class Suit(val pic: String){
    SPADES("♠"),
    HEARTS("♥"),
    CLUBS("♣"),
    DIAMONDS("♦");
}

class Card (private val rank: Rank, private val suit: Suit) {
    override fun toString(): String {
        return "${rank.pic}${suit.pic}"
    }

    fun isSameRankOrSuit(card: Card): Boolean {
        return this.rank == card.rank || this.suit == card.suit
    }

    fun isSameRank(rank: Rank): Boolean {
        return this.rank == rank
    }

    fun isSameSuit(suit: Suit): Boolean {
        return this.suit == suit
    }

    fun cardScore(): Int {
        return this.rank.score
    }

    fun getSuit(): Suit {
        return this.suit
    }

    fun getRank(): Rank {
        return this.rank
    }
}