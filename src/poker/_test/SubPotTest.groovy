package poker._test

import poker.InternalPlayerClient
import poker.Player
import poker.betting.SubPot

class SubPotTest extends GroovyTestCase {
    private SubPot _subPot
    private ArrayList<Player> _players



    void testWinner() {
        assertEquals(1001, _subPot.upperBound())
        _players[0].subtractChips(100)
        _players[1].subtractChips(50)
        _players[3].subtractChips(100)
        _subPot.insert(100, _players[0])
        _subPot.insert(50, _players[1])
        _subPot.insert(100, _players[3])

        assertEquals(1001, _subPot.upperBound())
        assertEquals(250, _subPot._total)


        _subPot.removeShareholder(_players[0])
        _subPot.removeShareholder(_players[1])
        _subPot.removeShareholder(_players[2])
        _subPot.removeShareholder(_players[4])
        assertEquals(1, _subPot.getShareholders().size())
        _subPot.payout()
        assertEquals(1300, _players[3]._stack)
        assertEquals(0, _subPot._total)
    }

    void testSplitPot() {
        _subPot.insert(100, _players[0])
        _subPot.insert(100, _players[1])
        _subPot.insert(30, _players[2])
        _subPot.insert(100, _players[3])
        _subPot.insert(30, _players[4])

        _players[0].subtractChips(100)
        _players[1].subtractChips(100)
        _players[2].subtractChips(30)
        _players[3].subtractChips(100)
        _players[4].subtractChips(30)

        _subPot.removeShareholder(_players[2])
        _subPot.removeShareholder(_players[4])
        assertEquals(3, _subPot.getShareholders().size())

        _subPot.payout()

        assertEquals(0, _subPot._total)
        assertEquals(1021, _players[0]._stack)
        assertEquals(1220, _players[1]._stack)
        assertEquals(1170, _players[3]._stack)
    }

    void setUp() {
        super.setUp()
        _players = new ArrayList<Player>([
                new Player("player 1", 1001, new InternalPlayerClient()),
                new Player("player 2", 1002, new InternalPlayerClient()),
                new Player("player 3", 1003, new InternalPlayerClient()),
                new Player("player 4", 1004, new InternalPlayerClient()),
                new Player("player 5", 1005, new InternalPlayerClient())
        ])
        _players[0].receiveChips(1001)
        _players[1].receiveChips(1200)
        _players[2].receiveChips(1149)
        _players[3].receiveChips(1150)
        _players[4].receiveChips(1800)
        _subPot = new SubPot(_players, 1001)
    }

    void tearDown() {
        _subPot = null
        _players = null
    }
}
