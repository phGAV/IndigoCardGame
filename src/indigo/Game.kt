package indigo

import indigo.players.Computer
import indigo.players.Human
import indigo.players.Player
import indigo.tools.Card
import indigo.tools.CardDeck
import indigo.tools.Table
import indigo.tools.CardHolder

import kotlin.system.exitProcess

import indigo.tools.INITIAL_AMOUNT
import indigo.tools.HAND

const val USER_EXIT = 1
const val GAME_OVER = 0
const val FINAL_POINTS = 3

class Game {
    private var cardDeck = CardDeck()
    private var table = Table()

    private val human = Human()
    private val computer = Computer()

    private var isPlayerMove: Boolean? = null
    private var lastWinner: Player

    init {
        println("Indigo Card Game")

        while (isPlayerMove == null) {
            println("Play first?")
            isPlayerMove = when (readLine()!!.lowercase()) {
                "yes"   -> true
                "no"    -> false
                else    -> continue
            }
        }
        lastWinner = if (isPlayerMove!!) human else computer

        cardDeck.shuffle()
        passCards(table, INITIAL_AMOUNT)
        passCardsToPlayers()

        println("Initial cards on the table: $table")
    }

    fun start() {
        while(true) {
            table.printStats()
            passCardsToPlayers()
            if (isPlayerMove!!) {
                try {
                    checkTheCard(human.makeMove(), human)
                } catch (e: Exception) {
                    exitTheGame(USER_EXIT)
                }
            } else {
                checkTheCard(computer.makeMove(table.top()), computer)
            }
            nextPlayer()
        }
    }

    private fun passCardsToPlayers() {
        if (human.amountOfCardsOnHand() == 0) {
            passCards(human)
        }
        if (computer.amountOfCardsOnHand() == 0) {
            passCards(computer)
        }
        if (human.amountOfCardsOnHand() == 0
            && computer.amountOfCardsOnHand() == 0
            && cardDeck.empty()) {
            endTheGame()
        }
    }

    private fun passCards(cardHolder: CardHolder, numberOfCards: Int = HAND) {
        cardHolder.take(cardDeck.get(numberOfCards))
    }

    private fun nextPlayer() {
        isPlayerMove = !isPlayerMove!!
    }

    private fun checkTheCard(card: Card, player: Player) {
        val tableCard = table.top()
        table.cards.add(card)
        if (tableCard?.isSameRankOrSuit(card) == true) {
            lastWinner = player
            playerWinsTableCards(player)
            printScore(player)
        }
    }

    private fun playerWinsTableCards(player: Player) {
        player.increaseScore(table.countCurrentScore())
        player.winCards(table.cards)
        table.cleanTheTable()
    }

    private fun printScore(player: Player? = null) {
        if (player != null)
            println("${player.name} wins cards")
        println("Score: Player ${human.score} - Computer ${computer.score}")
        println("Cards: Player ${human.amountOfCardsWon()} - Computer ${computer.amountOfCardsWon()}")
    }

    private fun endTheGame() {
        // cards on the table go to one who won last round
        playerWinsTableCards(lastWinner)
        addFinalPoints()
        printScore()
        exitTheGame()
    }

    private fun addFinalPoints() {
        if (human.amountOfCardsWon() > computer.amountOfCardsWon()) {
            human.increaseScore(FINAL_POINTS)
        } else if (human.amountOfCardsWon() < computer.amountOfCardsWon()) {
            computer.increaseScore(FINAL_POINTS)
        } else {
            lastWinner.increaseScore(FINAL_POINTS)
        }
    }

    private fun exitTheGame(status: Int = GAME_OVER) {
        println("Game Over")
        exitProcess(status)
    }
}