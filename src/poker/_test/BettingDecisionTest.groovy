package poker._test

import poker.InternalPlayerClient
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
        Player sb, bb, player3, player4, button
        sb = _players[0]
        bb = _players[1]
        player3 = _players[2]
        player4 = _players[3]
        button = _players[4]

        _pot.transfer(TableRules.SB, sb)
        _pot.transfer(TableRules.BB, bb)
        bettingQueue =  new ArrayDeque<BettingDecision>()

        bettingQueue.add(new BettingDecision(player3))
        bettingQueue.add(new BettingDecision(player4))
        bettingQueue.add(new BettingDecision(button))
        bettingQueue.add(new BettingDecision(sb))
       for (BettingDecision dec : bettingQueue) {

            dec.setOptions(BettingQueueFactory.getPreflop(_pot, dec._player))
        }
        decision = new BettingDecision(bb)
        decision.setOptions(BettingQueueFactory.getBB(_pot, bb))
        bettingQueue.add(decision)

        while(!bettingQueue.isEmpty()) {
            println("Size Queue: " + bettingQueue.size())
            for (BettingDecision bd : bettingQueue) {
                print(bd._player)
            }
            println()

            decision = bettingQueue.pop()

            println(decision._options)
            decision.execute(bettingQueue)
        }
    }
    void setUp() {
        super.setUp()
        _players = new ArrayList<>([
                new Player("SB", 3001, new InternalPlayerClient([30,0])),
                new Player("BB", 3002, new InternalPlayerClient([20,10])),
                new Player("Player 3", 3003, new InternalPlayerClient([20,80])),
                new Player("Player 4", 3004, new InternalPlayerClient([20,80])),
                new Player("Button", 3000, new InternalPlayerClient([20,0]))
                ])
        _players[0]._stack = 120
        _players[1]._stack = 50
        _players[2]._stack = 100
        _players[3]._stack = 150
        _players[4]._stack = 180

        _pot = new Pot(_players)
        _decision = new BettingDecision(_players[2])


    }
}
