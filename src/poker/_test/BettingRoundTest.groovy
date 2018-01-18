package poker._test

import poker.Player
import poker.betting.BettingDecision
import poker.betting.BettingQueueFactory
import poker.betting.Pot

class BettingRoundTest extends GroovyTestCase {
    private ArrayList<Player> _players
    private Pot _pot


    void testEntryCalls() {
        ArrayDeque<BettingDecision> bettingQueue
        BettingDecision decision

        bettingQueue = BettingQueueFactory.handEntryCallQueue(_pot, new ArrayDeque<Player>(_players))

        while(!bettingQueue.isEmpty()) {
            println("Size Queue: " + bettingQueue.size())
            for (BettingDecision bd : bettingQueue) {
                print(bd._player)
            }
            println()

            decision = bettingQueue.pop()

            println("\n" + decision._options)
            decision.execute(bettingQueue)
        }
    }

    void setUp() {
        super.setUp()
        _players = TestDataFactory.makePlayers()
        _pot = new Pot(_players)

    }

    void tearDown() {
    }
}
