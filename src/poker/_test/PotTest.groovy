package poker._test

import poker.InternalPlayerClient
import poker.Player
import poker.betting.Pot
import poker.betting.SubPot

class PotTest extends GroovyTestCase {
    private ArrayList<Player> _players
    private Pot _pot

    void tearDown() {
    }

    void testDistinctUpperBoundSubPot() {
        _players[0]._stack = 1000
        _players[1]._stack = 1000
        _players[2]._stack = 1000
        _players[3]._stack = 1200

        Pot pot = new Pot(_players)

        assertEquals(2, pot._potList.size())

        _players[0]._stack = 1000
        _players[1]._stack = 1800
        _players[2]._stack = 1000
        _players[3]._stack = 1799

        pot = new Pot(_players)

        assertEquals(3, pot._potList.size())

        _players[0]._stack = 0
        _players[1]._stack = 0
        _players[2]._stack = 0
        _players[3]._stack = 100

        pot = new Pot(_players)

        //  Actually 0 shouldn't be in the pot but don't know if this should be checked here at all. This logic works.
        assertEquals(2, pot._potList.size())
    }

    void testSimulatebettingAndPayout() {
        //startstack    100, 2000, 30, 400
        //bet           30, 200, 30, 300
        //potwin        0, 0, 120, 440
        //endstack      70, 1800, 120, 540
        ArrayList<Player> p = _players
        ArrayList<ArrayList<Player>> winnerOrder

        winnerOrder = new ArrayList<>()
        winnerOrder.add(new ArrayList<Player>([p[2]]))
        winnerOrder.add(new ArrayList<Player>([p[3]]))

        // p0 bet
        _pot.transfer(10, p[0])
        // p1 call
        _pot.transfer(10, p[1])
        // p2 all-in raise
        _pot.transfer(30, p[2])
        // p3 call
        _pot.transfer(30, p[3])
        // p0 call
        _pot.transfer(20, p[0])
        // p1 re-raise
        _pot.transfer(190, p[1])
        // p3 re-raise
        _pot.transfer(270, p[3])
        // p0 fold
        _pot.removeShareholder(p[0])
        // p1 fold
        _pot.removeShareholder(p[1])

        for (SubPot pot : _pot._potList) {
            print(pot._shareholders)
            println(    pot._total)
        }
        assertEquals(70, p[0]._stack)
        assertEquals(1800, p[1]._stack)
        assertEquals(0, p[2]._stack)
        assertEquals(100, p[3]._stack)

        _pot.setWinners(winnerOrder)
        _pot.payout()
        // p0 wins nothing
        assertEquals(70, p[0]._stack)
        // p1 nothing
        assertEquals(1800, p[1]._stack)
        // p2 gets main pot 120
        assertEquals(120, p[2]._stack)
        // p3 get side pot 470
        assertEquals(540, p[3]._stack)


    }

    void testInsert() {
        _pot.insert(50,_players[0])
        _players[0].subtractChips(50)
        _pot.insert(200, _players[1])
        _players[1].subtractChips(200)
        SubPot sub
        sub = _pot._potList.pop()
        assertEquals(0, sub._total)
        sub = _pot._potList.pop()
        assertEquals(70, sub._total)
        assertEquals(70,sub._shareholders.get(_players[1]))

        sub = _pot._potList.pop()
        assertEquals(120, sub._total)
        assertEquals(20,sub._shareholders.get(_players[0]))
        assertEquals(100,sub._shareholders.get(_players[1]))
        sub = _pot._potList.pop()
        assertEquals(60, sub._total)
        assertEquals(30,sub._shareholders.get(_players[0]))
        assertEquals(30,sub._shareholders.get(_players[1]))

    }

    void testInitPotlist() {
        Pot pot = new Pot(_players)
        for (SubPot sub : pot._potList) {
            for (Player p : sub.getShareholders()) {
                assertTrue(p.getStack() >= sub.upperBound())
            }
        }

    }
    void setUp() {
        super.setUp()
        _players = new ArrayList<>([
                new Player("player 1", 201, new InternalPlayerClient()),
                new Player("player 2", 202, new InternalPlayerClient()),
                new Player("player 3", 203, new InternalPlayerClient()),
                new Player("player 4", 204, new InternalPlayerClient())
        ])
        _players[0].recieveChips(100)
        _players[1].recieveChips(2000)
        _players[2].recieveChips(30)
        _players[3].recieveChips(400)
        _pot = new Pot(_players)
    }
}
