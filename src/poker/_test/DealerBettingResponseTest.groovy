package poker._test

import poker.InternalPlayerClient
import poker.Player
import poker.PlayerStatus
import poker.betting.AllIn
import poker.betting.Bet
import poker.betting.BettingAction
import poker.betting.Call
import poker.betting.Check
import poker.betting.Fold
import poker.betting.Pot
import poker.betting.Raise
import poker.betting.ReRaise

class DealerBettingResponseTest extends GroovyTestCase {
    Player _player
    Pot _pot

    void testResponseForFold() {
        Integer beforeStack = _player._stack
        Integer beforeShare = _pot.getShare(_player)
        assertTrue(_pot._activeInPot.contains(_player))

        BettingAction action = new Fold(_pot, _player)
        action.execute()

        assertFalse(_pot._activeInPot.contains(_player))
        assertEquals(beforeStack, _player._stack)
        assertEquals(beforeShare, _pot.getShare(_player))
        assertEquals(PlayerStatus.FOLD, _player._status)
    }

    void testResponseForCheck() {
        Integer beforeStack = _player._stack
        Integer beforeShare = _pot.getShare(_player)
        assertTrue(_pot._activeInPot.contains(_player))

        BettingAction action = new Check(_pot, _player)
        action.execute()

        assertTrue(_pot._activeInPot.contains(_player))
        assertEquals(beforeStack, _player._stack)
        assertEquals(beforeShare, _pot.getShare(_player))
        assertEquals(PlayerStatus.CHECK, _player._status)

    }

    void testResponseForCall() {
        Integer beforeStack = _player._stack
        Integer beforeShare = _pot.getShare(_player)
        BettingAction action = new Call(_pot, _player, 50)
        action.execute()

        assertEquals(beforeStack, _player._stack + 50)
        assertEquals(beforeShare, _pot.getShare(_player) - 50)
        assertEquals(PlayerStatus.CALL, _player.status())
    }

    void testResponseForBet() {
        Integer beforeStack = _player._stack
        Integer beforeShare = _pot.getShare(_player)
        BettingAction action = new Bet(_pot, _player, 50)
        action.execute()

        assertEquals(beforeStack, _player._stack + 50)
        assertEquals(beforeShare, _pot.getShare(_player) - 50)
        assertEquals(PlayerStatus.BET, _player.status())

    }

    void testResponseForRaise() {
        Integer beforeStack = _player._stack
        Integer beforeShare = _pot.getShare(_player)
        BettingAction action = new Raise(_pot, _player, 50)
        action.execute()

        assertEquals(beforeStack, _player._stack + 50)
        assertEquals(beforeShare, _pot.getShare(_player) - 50)
        assertEquals(PlayerStatus.RAISE, _player.status())

    }

    void testResponseForReRaise() {
        Integer beforeStack = _player._stack
        Integer beforeShare = _pot.getShare(_player)
        BettingAction action = new ReRaise(_pot, _player, 50)
        action.execute()

        assertEquals(beforeStack, _player._stack + 50)
        assertEquals(beforeShare, _pot.getShare(_player) - 50)
        assertEquals(PlayerStatus.RERAISE, _player.status())

    }

    void testResponseForAllIn() {
        Integer beforeStack = _player._stack
        Integer beforeShare = _pot.getShare(_player)
        BettingAction action = new AllIn(_pot, _player, _player._stack)
        action.execute()

        assertEquals(beforeStack, action.getAmount())
        assertEquals(beforeShare + action.getAmount(), _pot.getShare(_player))
        assertEquals(PlayerStatus.ALL_IN, _player.status())

    }

    @Override
    void setUp() {
        super.setUp()
        _player = new Player("Nickname", 666, new InternalPlayerClient())
        _player._stack = 100
        _pot = new Pot(new ArrayList<Player>([_player]))
    }
}
