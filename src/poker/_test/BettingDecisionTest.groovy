package poker._test

import org.hamcrest.CoreMatchers
import org.junit.Assert
import poker.InternalPlayerClient
import poker.Player
import poker.PlayerStatus
import poker.betting.BettingDecision
import poker.betting.CallBB
import poker.betting.CallSB
import poker.betting.Fold
import poker.betting.Pot
import poker.betting.BettingQueueFactory

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
        BettingDecision decisionSb, decisionBb, decisionP3, decisionP4, decisionButton
        ArrayDeque<BettingDecision> queue

        queue = BettingQueueFactory.handEntryCallQueue(_pot, new ArrayDeque<Player>(_players))

        assertEquals(2, queue.size())

        decisionSb = queue.pop()
        assertEquals(1, decisionSb._options.size())
        Assert.assertThat(decisionSb._options[0], CoreMatchers.instanceOf(CallSB.class))
        decisionSb.execute(queue)
        assertEquals(PlayerStatus.CALL, decisionSb._player._status)
        Assert.assertThat(decisionSb._selected, CoreMatchers.instanceOf(CallSB.class))

        decisionBb = queue.pop()
        assertEquals(1, decisionBb._options.size())
        Assert.assertThat(decisionBb._options[0], CoreMatchers.instanceOf(CallBB.class))




    }
    void setUp() {
        super.setUp()

        ArrayList<InternalPlayerClient> clients
        Integer[][] responseValues
        responseValues =[   [10, 30, 0],
                            [20, 20, 10],
                            [20, 80],
                            [20, 80, 0],
                            [20, 0 ]    ]

        clients = TestDataFactory.makePlayerClients(responseValues)
        _players = TestDataFactory.makePlayers(clients)
        _pot = new Pot(_players)
        _decision = new BettingDecision(_players[2])
    }

}
