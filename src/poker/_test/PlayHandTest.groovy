package poker._test

import groovy.json.JsonSlurper
import poker.Board
import poker.PlayerStatus
import poker.betting.BettingDecision
import poker.betting.BettingQueueFactory
import poker.InternalPlayerClient
import poker.Player
import poker.dealer.DealingRound
import poker.game.PlayHand


class PlayHandTest extends GroovyTestCase {
    private ArrayList<Player> _players
    private Board _board


    void testStart() {
    }

    void testBettingRounds() {
        PlayHand hand = [_players, _board]
        ArrayDeque<BettingDecision> handEntryCallQ, preFlopQ, flopQ, turnQ
        ArrayList<Integer> stackValues = []
        ArrayList<PlayerStatus> statusList = []
        handEntryCallQ = BettingQueueFactory.handEntryCallQueue(hand._pot, new ArrayDeque<Player>(hand._players))
        hand.bettingRound(handEntryCallQ)

        for (p in hand._players) {

            println(p._nick + ", " + p._status + ", " + p._stack + ", " + p._holecards)
        }
        DealingRound.holecards(hand._deck, new ArrayList<Player>(hand._players))

        preFlopQ = BettingQueueFactory.preFlop(hand._pot, new ArrayDeque<Player>(hand._players))
        hand.bettingRound(preFlopQ)

        for (p in hand._players) {

            println(p._nick + p._holecards + ", " + p._status + ", " + p._stack)
        }
        println("Board: " + hand._board._cards)
        println("Pot total: " + hand._pot.total())
        hand.flop()
        println("Board: " + hand._board._cards)
        println("Pot total: " + hand._pot.total())
        hand.turn()
        println("Board: " + hand._board._cards)
        println("Pot total: " + hand._pot.total())
        hand.river()
        println("Board: " + hand._board._cards)
        println("Pot total: " + hand._pot.total())
        hand.showdown()
        println("Pot total: " + hand._pot.total())
        hand.payout()
        println("Pot total: " + hand._pot.total())

    }


    void testShowdown() {
    }

    void testPayout() {
    }

    void testEnd() {
    }
    void setUp() {
        super.setUp()

        File inputFile = new File("/home/hans-rudolf/IdeaProjects/Inception/src/poker/_test/betting_responses.json" )

        JsonSlurper json = new JsonSlurper()
        def input = json.parseFile(inputFile, "UTF-8")
        assert input instanceof List
        ArrayList<InternalPlayerClient> clients

        clients = TestDataFactory.makePlayerClients(input)
        _board = new Board()
        _players = TestDataFactory.makePlayers(clients)
    }
}
