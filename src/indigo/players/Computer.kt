package indigo.players

import indigo.tools.Card
import indigo.tools.Rank
import indigo.tools.Suit

class Computer(name: String = "Computer",
               cards: MutableList<Card> =  mutableListOf(),
               score: Int = 0,
               winnedCards: MutableList<Card> = mutableListOf())
    : Player(name, cards, score, winnedCards) {

    override fun makeMove(topCard: Card?): Card {
        // println(this) // to show computer cards before move
        //  1) If there is only one card in hand, put it on the table
        if (cards.size == 1) {
            return playTheCard(cards[0])
        }
        var candidateList: List<Card> = getCandidates(topCard)
        //  2) If there is only one candidate card, put it on the table
        if (candidateList.size == 1) {
           return playTheCard(candidateList[0])
        }
        //  3) If there are no cards on the table
        //  4) If there are cards on the table but no candidate cards
        if (candidateList.isEmpty()) {
            //  If there are cards in hand with the same suit, throw one of them at random
            candidateList = searchRepeatingSuit()
            if (candidateList.isNotEmpty())
                return playTheCard(candidateList.random())

            //  If there are no cards with the same rank, then throw one of them at random
            candidateList = searchRepeatingRank()
            if (candidateList.isNotEmpty())
                return playTheCard(candidateList.random())

            //  If there are no cards in hand with the same suit or rank, throw any card at random
            return playTheCard(cards.random())
        }
        //  5) If there are two or more candidate cards:
        //  If there are 2 or more candidate cards with the same suit, throw one of them at random
        var filterTheCandidates = searchRepeatingSuit(candidateList)
        if (filterTheCandidates.isNotEmpty())
            return playTheCard(filterTheCandidates.random())

        //  If there are 2 or more candidate cards with the same rank, throw one of them at random
        filterTheCandidates = searchRepeatingRank(candidateList)
        if (filterTheCandidates.isNotEmpty())
            return playTheCard(filterTheCandidates.random())

        //  else any candidate at random
        return playTheCard(candidateList.random())
    }

    private fun searchRepeatingSuit(cards: List<Card> = this.cards): List<Card> {
        // find suit that repeats or return empty
        val map: MutableMap<Suit, Int> = mutableMapOf()
        for (card in cards) {
            map[card.getSuit()] = map.getOrDefault(card.getSuit(), 0) + 1
        }
        val max = map.maxByOrNull { e -> e.value }
        if (max?.value == 1) return emptyList()

        return searchSameSuitAsGiven(max!!.key)
    }

    private fun searchSameSuitAsGiven(suit: Suit): List<Card> {
        val list = mutableListOf<Card>()

        for (card in cards) {
            if (card.isSameSuit(suit)) {
                list.add(card)
            }
        }
        return list
    }

    private fun searchRepeatingRank(cards: List<Card> = this.cards): List<Card> {
        // find rank that repeats or return empty
        val map: MutableMap<Rank, Int> = mutableMapOf()
        for (card in cards) {
            map[card.getRank()] = map.getOrDefault(card.getRank(), 0) + 1
        }
        val max = map.maxByOrNull { e -> e.value }
        if (max?.value == 1) return emptyList()

        return searchSameRankAsGiven(max!!.key)
    }

    private fun searchSameRankAsGiven(rank: Rank): List<Card> {
        val list = mutableListOf<Card>()

        for (card in cards) {
            if (card.isSameRank(rank)) {
                list.add(card)
            }
        }
        return list
    }

    private fun playTheCard(card: Card): Card {
        println("Computer plays $card")
        cards.remove(card)
        return card
    }

    private fun getCandidates(topCard: Card?): List<Card> {
        if (topCard == null) return emptyList()
        val list = mutableListOf<Card>()
        for (card in cards) {
            if (card.isSameRankOrSuit(topCard))
                list.add(card)
        }
        return list
    }
}
