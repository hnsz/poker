package poker._test

import org.hamcrest.CoreMatchers
import org.junit.Assert
import poker.InternalPlayerClient
import poker.Player
import poker.PlayerStatus
import poker.betting.BettingAction
import poker.betting.BettingDecision
import poker.betting.CallBB
import poker.betting.CallSB
import poker.betting.Fold
import poker.betting.NoAction
import poker.betting.Pot
import poker.betting.BettingQueueFactory

import java.util.stream.Collector

class BettingDecisionTest extends GroovyTestCase {
    ArrayList<Player> _players
    Pot _pot

    void tearDown() {
        _decision = null
    }

    void testBetting() {

    }

    void testOption() {

    }


    void testWithPreFlopBettingSequence() {
        BettingDecision decisionSb, decisionBb, decisionP3, decisionP4, decisionButton, noAction

        ArrayDeque<BettingDecision> queue
        BettingAction action
        Player sb, bb, p3, p4, button
        sb = _players[0]
        bb = _players[1]
        p3 = _players[2]
        p4 = _players[3]
        button = _players[4]

        queue = BettingQueueFactory.handEntryCallQueue(_pot, new ArrayDeque<Player>(_players))

        assertEquals(2, queue.size())

        decisionSb = queue.pop()
        assertSame(sb, decisionSb._player)
        assertEquals(120, sb._stack)
        assertEquals(0, _pot.getShare(sb))
        assertEquals(0, _pot.toCall(sb))
        assertEquals(0, _pot.total())
        assertEquals(1, decisionSb._options.size())
        Assert.assertThat(decisionSb._options[0], CoreMatchers.instanceOf(CallSB.class))

        decisionSb.execute(queue)
        assertEquals(PlayerStatus.CALL, sb._status)
        assertSame(decisionSb._selected, decisionSb._options[0])
        assertEquals(1, decisionSb._selected.requeuingOptions().size())
        assertEquals(2, queue.size())


        decisionBb = queue.pop()
        assertEquals(1, decisionBb._options.size())
        assertSame(bb, decisionBb._player)
        assertEquals(50, bb._stack)
        assertEquals(0, _pot.getShare(bb))
        assertEquals(10, _pot.toCall(bb))
        assertEquals(10, _pot.total())
        assertEquals(1, decisionBb._options.size())
        Assert.assertThat(decisionBb._options[0], CoreMatchers.instanceOf(CallBB.class))


        decisionBb.execute(queue)
        assertEquals(PlayerStatus.CALL, bb._status)
        assertSame(decisionBb._selected, decisionBb._options[0])
        assertEquals(1, decisionBb._selected.requeuingOptions().size())
        assertEquals(2, queue.size())

        decisionSb = queue.pop()
        assertEquals(sb, decisionSb._player)
        decisionSb.execute(queue)
        assertEquals(1, queue.size())
        decisionBb = queue.pop()
        assertEquals(bb, decisionBb._player)
        decisionBb.execute(queue)
        assertTrue(queue.isEmpty())



        queue = BettingQueueFactory.preFlop(_pot, new ArrayDeque<>(_players))

        assertEquals(5, queue.size())

        decisionP3 = queue.pop()
        assertSame(p3, decisionP3._player)
        assertEquals(100, p3._stack)
        assertEquals(0, _pot.getShare(p3))
        assertEquals(20, _pot.toCall(p3))
        assertEquals(30, _pot.total())
        assertEquals(4, decisionP3._options.size())

        decisionP3.execute(queue)
        assertEquals(PlayerStatus.CALL, p3._status)
        assertNotNull(decisionP3._selected)
        assertEquals(1, decisionP3._selected.requeuingOptions().size())
        assertEquals(5, queue.size())

        decisionP4 = queue.pop()
        assertSame(p4, decisionP4._player)
        assertEquals(120, p4._stack)
        assertEquals(0, _pot.getShare(p4))
        assertEquals(20, _pot.toCall(p4))
        assertEquals(50, _pot.total())
        assertEquals(4, decisionP4._options.size())

        decisionP4.execute(queue)
        assertEquals(PlayerStatus.CALL, p4._status)
        assertNotNull(decisionP4._selected)
        assertEquals(1, decisionP4._selected.requeuingOptions().size())
        assertEquals(5, queue.size())

        decisionButton = queue.pop()
        assertSame(button, decisionButton._player)
        assertEquals(180, button._stack)
        assertEquals(0, _pot.getShare(button))
        assertEquals(20, _pot.toCall(button))
        assertEquals(70, _pot.total())
        assertEquals(4, decisionButton._options.size())

        decisionButton.execute(queue)
        assertEquals(PlayerStatus.CALL, button._status)
        assertNotNull(decisionButton._selected)
        assertEquals(1, decisionButton._selected.requeuingOptions().size())
        assertEquals(5, queue.size())


        decisionSb = queue.pop()
        assertSame(sb, decisionSb._player)
        assertEquals(110, sb._stack)
        assertEquals( 10, _pot.getShare(sb))
        assertEquals(10, _pot.toCall(sb))
        assertEquals(90, _pot.total())
        assertEquals(4, decisionSb._options.size())

        decisionSb.execute(queue)
        assertEquals(PlayerStatus.RAISE, sb._status)
        assertNotNull(decisionSb._selected)
        assertEquals(1, decisionSb._selected.requeuingOptions().size())
        assertEquals(5, queue.size())


        decisionBb = queue.pop()
        assertSame(bb, decisionBb._player)
        assertEquals(30, bb._stack)
        assertEquals( 20, _pot.getShare(bb))
        assertEquals(20, _pot.toCall(bb))
        assertEquals(120, _pot.total())
        assertEquals(3, decisionBb._options.size())

        decisionBb.execute(queue)
        assertEquals(PlayerStatus.CALL, bb._status)
        assertNotNull(decisionBb._selected)
        assertEquals(1, decisionBb._selected.requeuingOptions().size())
        assertEquals(5, queue.size())


        decisionP3 = queue.pop()
        assertSame(p3, decisionP3._player)
        assertEquals(80, p3._stack)
        assertEquals( 20, _pot.getShare(p3))
        assertEquals(20, _pot.toCall(p3))
        assertEquals(140, _pot.total())
        assertEquals(4, decisionP3._options.size())

        decisionP3.execute(queue)
        assertEquals(PlayerStatus.ALL_IN, p3._status)
        assertNotNull(decisionP3._selected)
        assertEquals(0, decisionP3._selected.requeuingOptions().size())
        assertEquals(4, queue.size())


        decisionP4 = queue.pop()
        assertSame(p4, decisionP4._player)
        assertEquals(100, p4._stack)
        assertEquals( 20, _pot.getShare(p4))
        assertEquals(80, _pot.toCall(p4))
        assertEquals(220, _pot.total())
        assertEquals(3, decisionP4._options.size())

        decisionP4.execute(queue)
        assertEquals(PlayerStatus.CALL, p4._status)
        assertNotNull(decisionP4._selected)
        assertEquals(1, decisionP4._selected.requeuingOptions().size())


        decisionButton = queue.pop()
        assertSame(button, decisionButton._player)
        assertEquals(160, button._stack)
        assertEquals(20, _pot.getShare(button))
        assertEquals(80, _pot.toCall(button))
        assertEquals(300, _pot.total())
        assertEquals(4, decisionButton._options.size())

        decisionButton.execute(queue)
        assertEquals(PlayerStatus.FOLD, button._status)
        assertNotNull(decisionButton._selected)
        assertEquals(0, decisionButton._selected.requeuingOptions().size())
        assertEquals(3, queue.size())


        decisionSb = queue.pop()
        assertSame(sb, decisionSb._player)
        assertEquals(80, sb._stack)
        assertEquals( 40, _pot.getShare(sb))
        assertEquals(60, _pot.toCall(sb))
        assertEquals(300, _pot.total())
        assertEquals(3, decisionSb._options.size())

        decisionSb.execute(queue)
        assertEquals(PlayerStatus.FOLD, sb._status)
        assertNotNull(decisionSb._selected)
        assertEquals(0, decisionSb._selected.requeuingOptions().size())
        assertEquals(2, queue.size())


        decisionBb = queue.pop()
        assertSame(bb, decisionBb._player)
        assertEquals(10, bb._stack)
        assertEquals( 40, _pot.getShare(bb))
        assertEquals(60, _pot.toCall(bb))
        assertEquals(300, _pot.total())
        assertEquals(2, decisionBb._options.size())

        decisionBb.execute(queue)
        assertEquals(PlayerStatus.ALL_IN, bb._status)
        assertNotNull(decisionBb._selected)
        assertEquals(0, decisionBb._selected.requeuingOptions().size())
        assertEquals(1, queue.size())

        decisionP4 = queue.pop()
        assertEquals(p4, decisionP4._player)
        decisionP4.execute(queue)
        Assert.assertThat(decisionP4._options[0], CoreMatchers.instanceOf(NoAction.class))
        assertEquals(decisionP4._selected, decisionP4._options[0])
        assertTrue(queue.isEmpty())

        assertEquals(80, sb._stack)
        assertEquals(0, bb._stack)
        assertEquals(0, p3._stack)
        assertEquals(20, p4._stack)
        assertEquals(160, button._stack)
        assertEquals(310, _pot.total())
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
        _players = TestDataFactory.makePlayers(clients)
        _pot = new Pot(_players)
    }

}
