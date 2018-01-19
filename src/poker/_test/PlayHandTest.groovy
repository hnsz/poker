package poker._test

import poker.Board
import poker.betting.BettingDecision
import poker.betting.BettingQueueFactory
import poker.betting.Pot
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
        PlayHand hand = new PlayHand(_players, _board)
        ArrayDeque<BettingDecision> handEntryCallQ, preFlopQ
        handEntryCallQ = BettingQueueFactory.handEntryCallQueue(hand._pot, new ArrayDeque<Player>(hand._players))
        hand.bettingRound(handEntryCallQ)

        DealingRound.holecards(hand._deck, new ArrayList<Player>(hand._players))
        preFlopQ = BettingQueueFactory.preFlop(hand._pot, new ArrayDeque<Player>(hand._players))
        hand.bettingRound(preFlopQ)

        for (Player p : hand._players) {
            println(p._nick + ", " + p._status + ", " + p._stack + ", " + p._holecards)
        }

    }

    void testShowdown() {
    }

    void testPayout() {
    }

    void testEnd() {
    }
    void setUp() {
        super.setUp()
        ArrayList<InternalPlayerClient> clients
        Integer[][] responseValues
        responseValues =[   [10, 30, 0], //sb
                            [20, 20, 10],//bb
                            [20, 80],    //p3
                            [20, 80, 0], //p4
                            [20, 0 ]    ]//button

        clients = TestDataFactory.makePlayerClients(responseValues)
        _board = new Board()
        _players = TestDataFactory.makePlayers(clients)
    }
}
