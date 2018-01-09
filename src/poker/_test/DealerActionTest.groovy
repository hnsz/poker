package poker._test

import poker.InternalPlayerClient
import poker.Player
import poker.PlayerStatus
import poker.betting.Call
import poker.betting.Pot
import poker.dealer.DealerCallAction
import poker.dealer.DealerCheckAction
import poker.dealer.DealerAction
import poker.dealer.DealerFoldAction

class DealerActionTest extends GroovyTestCase {
    Player _player
    Pot _pot

    void testFoldAction() {
        Integer beforeStack = _player._stack
        Integer beforeShare = _pot.getShare(_player)
        assertTrue(_pot._activeInPot.contains(_player))

        DealerAction action = new DealerFoldAction(_player, _pot)
        action.execute()

        assertFalse(_pot._activeInPot.contains(_player))
        assertEquals(beforeStack, _player._stack)
        assertEquals(beforeShare, _pot.getShare(_player))
        assertEquals(PlayerStatus.FOLD, _player._status)
    }

    void testCheckAction() {
        Integer beforeStack = _player._stack
        Integer beforeShare = _pot.getShare(_player)
        assertTrue(_pot._activeInPot.contains(_player))

        DealerAction action = new DealerCheckAction(_pot, _player)
        action.execute()

        assertTrue(_pot._activeInPot.contains(_player))
        assertEquals(beforeStack, _player._stack)
        assertEquals(beforeShare, _pot.getShare(_player))
        assertEquals(PlayerStatus.CHECK, _player._status)

    }

    void testCallAction() {
        Integer beforeStack = _player._stack
        Integer beforeShare = _pot.getShare(_player)
        DealerAction action = new DealerCallAction(_pot, _player)
        action.execute()

        assertEquals(beforeStack, _player._stack + 50)
        assertEquals(beforeShare, _pot.getShare(_player) - 50)
        assertEquals(PlayerStatus.CALL, _player.status())
    }


    void testRaiseAction() {

    }



    @Override
    void setUp() {
        super.setUp()
        _player = new Player("Nickname", 666, new InternalPlayerClient())
        _player._stack = 100
        _pot = new Pot(new ArrayList<Player>([_player]))
    }
}
