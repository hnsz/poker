package poker._test

import poker.InternalPlayerClient
import poker.Player
import poker.PlayerStatus
import poker.betting.AllIn
import poker.betting.Bet
import poker.betting.BettingAction
import poker.betting.Call
import poker.betting.CallBB
import poker.betting.CallSB
import poker.betting.Check
import poker.betting.Fold
import poker.betting.Pot
import poker.betting.Raise
import poker.betting.ReRaise
import poker.game.TableRules

class BettingActionTest extends GroovyTestCase {
    ArrayList<Player> _players
    Pot _pot

    void testExecuteFold() {
        Player player = _players[0]
        Integer beforeStack = player._stack
        Integer beforeShare = _pot.getShare(player)
        assertTrue(_pot._activeInPot.contains(player))

        BettingAction action = new Fold(_pot, player)
        action.execute()

        assertFalse(_pot._activeInPot.contains(player))
        assertEquals(beforeStack, player._stack)
        assertEquals(beforeShare, _pot.getShare(player))
        assertEquals(PlayerStatus.FOLD, player._status)
    }

    void testExecuteCheck() {
        Player player = _players[0]
        Integer beforeStack = player._stack
        Integer beforeShare = _pot.getShare(player)
        assertTrue(_pot._activeInPot.contains(player))

        BettingAction action = new Check(_pot, player)
        action.execute()

        assertTrue(_pot._activeInPot.contains(player))
        assertEquals(beforeStack, player._stack)
        assertEquals(beforeShare, _pot.getShare(player))
        assertEquals(PlayerStatus.CHECK, player._status)

    }

    void testExecuteCall() {
        Player player = _players[0]
        Integer beforeStack = player._stack
        Integer beforeShare = _pot.getShare(player)
        _pot.transfer(50, _players[3])
        BettingAction action = new Call(_pot, player)
        action.execute()

        assertEquals(beforeStack, player._stack + 50)
        assertEquals(beforeShare, _pot.getShare(player) - 50)
        assertEquals(PlayerStatus.CALL, player.status())
    }

    void testExecuteBet() {
        Player player = _players[0]
        Integer beforeStack = player._stack
        Integer beforeShare = _pot.getShare(player)
        BettingAction action = new Bet(_pot, player)
        action.setAmount(50)
        action.execute()

        assertEquals(beforeStack, player._stack + 50)
        assertEquals(beforeShare, _pot.getShare(player) - 50)
        assertEquals(PlayerStatus.BET, player.status())

    }

    void testExecuteRaise() {
        Player player = _players[0]
        Integer beforeStack = player._stack
        Integer beforeShare = _pot.getShare(player)
        _pot.transfer(50, _players[3])
        BettingAction action = new Raise(_pot, player)
        action.setAmount(75)
        action.execute()

        assertEquals(beforeStack, player._stack + 75)
        assertEquals(beforeShare, _pot.getShare(player) - 75)
        assertEquals(PlayerStatus.RAISE, player.status())

    }

    void testExecuteReRaise() {
        Player player = _players[0]
        Integer beforeStack = player._stack
        Integer beforeShare = _pot.getShare(player)
        BettingAction action = new ReRaise(_pot, player)
        action.setAmount(50)
        action.execute()

        assertEquals(beforeStack, player._stack + 50)
        assertEquals(beforeShare, _pot.getShare(player) - 50)
        assertEquals(PlayerStatus.RERAISE, player.status())

    }

    void testExecuteAllIn() {
        Player player = _players[0]
        Integer beforeStack = player._stack
        Integer beforeShare = _pot.getShare(player)
        BettingAction action = new AllIn(_pot, player, player._stack)
        action.execute()

        assertEquals(beforeStack, action.getAmount())
        assertEquals(beforeShare + action.getAmount(), _pot.getShare(player))
        assertEquals(PlayerStatus.ALL_IN, player.status())

    }

    void testBettingSequence() {
        Player sb,bb,p3,button
        BettingAction action
        sb = _players[0]
        bb = _players[1]
        p3 = _players[2]
        button = _players[3]
        //SB 10
        action = new CallSB(_pot, sb)
        assertTrue(action.matchesConstraints(TableRules.SB))
        println(action)
        action.execute()
        assertEquals(100, TableRules.SB + sb._stack)
        //BB 20
        print("Options" + bb + ":")
        println(action.followUps(bb))
        action = new CallBB(_pot, bb)
        assertTrue(action.matchesConstraints(TableRules.BB))
        println(action)
        action.execute()
        assertEquals(100, TableRules.BB + bb._stack)
        //Player 3 raise 40
        print("Options" + p3 + ":")
        println(action.followUps(p3))
        action = new Raise(_pot, p3)
        assertTrue(action.matchesConstraints(40))
        action.setAmount(40)
        println(action)
        action.execute()
        assertEquals(100, p3._stack + 40)
        //Button reraise 70
        print("Options" + button + ":")
        println(action.followUps(button))
        action = new ReRaise(_pot, button)
        assertTrue(action.matchesConstraints(70))
        action.setAmount(70)
        println(action)
        action.execute()
        assertEquals(100, button._stack + 70)
        //SB all in 100
        print("Options" + sb + ":")
        println(action.followUps(sb))
        action = new AllIn(_pot, sb)
        assertTrue(action.matchesConstraints(90))
        println(action)
        action.execute()
        assertEquals(0, sb._stack)
        //BB Fold
        print("Options" + bb + ":")
        println(action.followUps(bb))
        action = new Fold(_pot, bb)
        assertTrue(action.matchesConstraints(0))
        println(action)
        action.execute()
        assertEquals(80, bb._stack)
        //Player 3 Fold
        print("Options" + p3 + ":")
        println(action.followUps(p3))
        action = new Fold(_pot, p3)
        assertTrue(action.matchesConstraints(0))
        println(action)
        action.execute()
        assertEquals(60, p3._stack)
        //Button call
        print("Options" + button + ":")
        println(action.followUps(button))
        action = new AllIn(_pot, button)
        assertTrue(action.matchesConstraints(30))
        println(action)
        action.execute()
        assertEquals(0, button._stack)

        println("People in pot:" + _pot._activeInPot)
    }
    @Override
    void setUp() {
        super.setUp()

        _players = new ArrayList<>([
                new Player("SB", 3001, new InternalPlayerClient()),
                new Player("BB", 3002, new InternalPlayerClient()),
                new Player("Player 3", 3003, new InternalPlayerClient()),
                new Player("Button", 3000, new InternalPlayerClient())
                ])

        _players[0]._stack = 100
        _players[1]._stack = 100
        _players[2]._stack = 100
        _players[3]._stack = 100

        _pot = new Pot(_players)
    }
}
