package poker._test

import poker.Player
import poker.betting.SubPot

class SubPotTest extends GroovyTestCase {
    private SubPot _pot
    private ArrayList<Player> _players


    void testPayout() {
        _pot.insert(100, _players[0])
        _pot.insert(50, _players[1])
        _pot.insert(100, _players[3])

        assertEquals(250, _pot._total)
        _pot.payout(_players[3])
        assertEquals(250, _players[3]._stack)
        assertEquals(0, _pot._total)
    }

    void testSplit() {
        _pot._shareHolders = _players
        _pot._total = 400

        _pot.split()

        assertEquals(0, _pot._total)
        for (Player p : _players) {
            assertEquals(100, p._stack)
        }
    }

    void setUp() {
        super.setUp()
        _pot = new SubPot()
        _players = new ArrayList<Player>([
                new Player("player 1", 1001),
                new Player("player 2", 1002),
                new Player("player 3", 1003),
                new Player("player 4", 1004)
        ])
    }

    void tearDown() {
    }
}
