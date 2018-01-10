package poker._test

import poker.InternalPlayerClient
import poker.Player
import poker.PlayerStatus
import poker.betting.Bet
import poker.betting.BettingAction
import poker.betting.Call
import poker.betting.Pot
import poker.betting.Raise
import poker.betting.ReRaise
import poker.dealer.DealerResponseForBet
import poker.dealer.DealerResponseForCall
import poker.dealer.DealerResponseForCheck
import poker.dealer.DealerBettingResponse
import poker.dealer.DealerResponseForFold
import poker.dealer.DealerResponseForRaise
import poker.dealer.DealerResponseForReRaise

class DealerBettingResponseTest extends GroovyTestCase {
    Player _player
    Pot _pot

    void testResponseForFold() {
        Integer beforeStack = _player._stack
        Integer beforeShare = _pot.getShare(_player)
        assertTrue(_pot._activeInPot.contains(_player))

        DealerBettingResponse response = new DealerResponseForFold(_player, _pot)
        response.execute()

        assertFalse(_pot._activeInPot.contains(_player))
        assertEquals(beforeStack, _player._stack)
        assertEquals(beforeShare, _pot.getShare(_player))
        assertEquals(PlayerStatus.FOLD, _player._status)
    }

    void testResponseForCheck() {
        Integer beforeStack = _player._stack
        Integer beforeShare = _pot.getShare(_player)
        assertTrue(_pot._activeInPot.contains(_player))

        DealerBettingResponse response = new DealerResponseForCheck(_pot, _player)
        response.execute()

        assertTrue(_pot._activeInPot.contains(_player))
        assertEquals(beforeStack, _player._stack)
        assertEquals(beforeShare, _pot.getShare(_player))
        assertEquals(PlayerStatus.CHECK, _player._status)

    }

    void testResponseForCall() {
        Integer beforeStack = _player._stack
        Integer beforeShare = _pot.getShare(_player)
        DealerBettingResponse response = new DealerResponseForCall(_pot, _player)
        BettingAction action = new Call(50, response)
        response.execute()

        assertEquals(beforeStack, _player._stack + 50)
        assertEquals(beforeShare, _pot.getShare(_player) - 50)
        assertEquals(PlayerStatus.CALL, _player.status())
    }

    void testResponseForBet() {
        Integer beforeStack = _player._stack
        Integer beforeShare = _pot.getShare(_player)
        DealerBettingResponse response = new DealerResponseForBet(_pot, _player)
        BettingAction action = new Bet(50, response)
        response.execute()

        assertEquals(beforeStack, _player._stack + 50)
        assertEquals(beforeShare, _pot.getShare(_player) - 50)
        assertEquals(PlayerStatus.BET, _player.status())

    }

    void testResponseForRaise() {
        Integer beforeStack = _player._stack
        Integer beforeShare = _pot.getShare(_player)
        DealerBettingResponse response = new DealerResponseForRaise(_pot, _player)
        BettingAction action = new Raise(50, response)
        response.execute()

        assertEquals(beforeStack, _player._stack + 50)
        assertEquals(beforeShare, _pot.getShare(_player) - 50)
        assertEquals(PlayerStatus.RAISE, _player.status())

    }

    void testResponseForReRaise() {
        Integer beforeStack = _player._stack
        Integer beforeShare = _pot.getShare(_player)
        DealerBettingResponse response = new DealerResponseForReRaise(_pot, _player)
        BettingAction action = new ReRaise(50, response)
        response.execute()

        assertEquals(beforeStack, _player._stack + 50)
        assertEquals(beforeShare, _pot.getShare(_player) - 50)
        assertEquals(PlayerStatus.RERAISE, _player.status())

    }


    @Override
    void setUp() {
        super.setUp()
        _player = new Player("Nickname", 666, new InternalPlayerClient())
        _player._stack = 100
        _pot = new Pot(new ArrayList<Player>([_player]))
    }
}
