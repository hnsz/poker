package poker._test

import poker.InternalPlayerClient
import poker.Player
import poker.betting.Pot
import poker.betting.SubPot

class PotTest extends GroovyTestCase {
    private ArrayList<Player> _players

    void tearDown() {
    }

    void testRemoveFromAll() {
    }

    void testInsert() {
    }

    void testInitPotlist() {
        Pot pot = new Pot(_players)
        for (SubPot sub : pot._potlist) {
            print(sub.getShareholders())
            println(sub.upperBound())
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
    }
}
