package poker._test

import poker.Player
import poker.betting.SubPot

class SubPotTest extends GroovyTestCase {
    private SubPot _subPot
    private ArrayList<Player> _players


    void testWinner() {
        _subPot.insert(100, _players[0])
        _subPot.insert(50, _players[1])
        _subPot.insert(100, _players[3])

        assertEquals(250, _subPot._total)

        _subPot.remove(_players[0])
        _subPot.remove(_players[1])
        _subPot.remove(_players[2])
        _subPot.remove(_players[4])
        assertEquals(1, _subPot.getShareholders().size())
        _subPot.payout()
        assertEquals(250, _players[3]._stack)
        assertEquals(0, _subPot._total)
    }

    void testSplitPot() {
        _subPot.insert(100, _players[0])
        _subPot.insert(100, _players[1])
        _subPot.insert(100, _players[3])

        _subPot.remove(_players[2])
        _subPot.remove(_players[4])
        assertEquals(3, _subPot.getShareholders().size())

        _subPot.payout()

        assertEquals(0, _subPot._total)
        assertEquals(100, _players[0]._stack)
        assertEquals(100, _players[1]._stack)
        assertEquals(100, _players[3]._stack)
    }

    void setUp() {
        super.setUp()
        _players = new ArrayList<Player>([
                new Player("player 1", 1001),
                new Player("player 2", 1002),
                new Player("player 3", 1003),
                new Player("player 4", 1004)
        ])
        _subPot = new SubPot(_players)
    }

    void tearDown() {
        _subPot = null
        _players = null
    }
}
