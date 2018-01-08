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

    void testRemoveFromAll() {
    }

    void testInsert() {
        _pot.insert(50,_players[0])
        _players[0].subtractChips(50)
        _pot.insert(200, _players[1])
        _players[1].subtractChips(200)
        SubPot sub
        sub = _pot._potlist.pop()
        assertEquals(0, sub._total)
        sub = _pot._potlist.pop()
        assertEquals(70, sub._total)
        assertEquals(70,sub._shareholders.get(_players[1]))

        sub = _pot._potlist.pop()
        assertEquals(120, sub._total)
        assertEquals(20,sub._shareholders.get(_players[0]))
        assertEquals(100,sub._shareholders.get(_players[1]))
        sub = _pot._potlist.pop()
        assertEquals(60, sub._total)
        assertEquals(30,sub._shareholders.get(_players[0]))
        assertEquals(30,sub._shareholders.get(_players[1]))

    }

    void testInitPotlist() {
        Pot pot = new Pot(_players)
        for (SubPot sub : pot._potlist) {
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
