package poker._test

import poker.Player
import poker.betting.BettingDecision
import poker.betting.Pot
import poker.betting.BettingQueueFactory
import poker.game.TableRules

class BettingDecisionTest extends GroovyTestCase {
    BettingDecision _decision
    ArrayList<Player> _players
    Pot _pot

    void tearDown() {
        _decision = null
    }

    void testBetting() {

    }

    void testOption() {

    }


    void testChoice() {
        ArrayDeque<BettingDecision> bettingQueue
        BettingDecision decision
        Player sb, bb
        sb = _players[0]
        bb = _players[1]

        _pot.transfer(TableRules.SB, sb)
        _pot.transfer(TableRules.BB, bb)
        println("stacks at start of round")
        for (Player p : _players) {
            println("" + p + " " + p.getStack())
        }
        bettingQueue = BettingQueueFactory.preFlop(_pot, new ArrayDeque<Player>(_players))


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
        _decision = new BettingDecision(_players[2])
    }

}
