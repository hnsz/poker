package poker._test

import poker.InternalPlayerClient
import poker.Player
import poker.betting.AllIn
import poker.betting.Bet
import poker.betting.BettingAction
import poker.betting.BettingDecision
import poker.betting.Call
import poker.betting.Check
import poker.betting.Fold
import poker.betting.Pot

class BettingRoundTest extends GroovyTestCase {
    private ArrayList<Player> _players
    private ArrayDeque<Player> _bettingQueue
    private ArrayDeque<BettingAction> _actions
    private Pot _pot


    void testFirstDecision() {
        Player player = _bettingQueue.pop()
        BettingDecision bettingDecision = new BettingDecision(player)


    }

    void setUp() {
        super.setUp()
        _players = new ArrayList<>([
                new Player("Player 3", 3003, new InternalPlayerClient()),
                new Player("Player 4", 3004, new InternalPlayerClient()),
                new Player("Button", 3000, new InternalPlayerClient()),
                new Player("SB", 3001, new InternalPlayerClient()),
                new Player("BB", 3002, new InternalPlayerClient())
        ])
        _players[0]._stack = 1000
        _players[1]._stack = 1000
        _players[2]._stack = 1000
        _players[3]._stack = 1000
        _players[4]._stack = 1000
        _bettingQueue = new ArrayDeque<>(_players)
        _actions = new ArrayDeque<>()
        _pot = new Pot(_players)

        _pot.transfer(20, _players[3])
        _pot.transfer(40, _players[4])
    }

    void tearDown() {
    }
}
