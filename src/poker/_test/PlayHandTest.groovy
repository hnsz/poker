package poker._test

import poker.dealer.Dealer
import poker.InternalPlayerClient
import poker.Player
import poker.game.PlayHand
import poker.table.Table
import poker.table.TableRules

class PlayHandTest extends GroovyTestCase {
    private ArrayList<Player> _players

    void testConstructor() {
        PlayHand hand =  new PlayHand(new Dealer(new Table(new TableRules())), _players);
        assertEquals(hand._players,_players)
        ArrayList<Player> list = new ArrayList<>(hand._players)

    }
    void testStart() {
    }

    void testBettingRounds() {
    }

    void testShowdown() {
    }

    void testPayout() {
    }

    void testEnd() {
    }
    void setUp() {
        super.setUp()
        _players = new ArrayList<>([
                new Player("player 1", 201, new InternalPlayerClient()),
                new Player("player 2", 202, new InternalPlayerClient()),
                new Player("player 3", 203, new InternalPlayerClient()),
                new Player("player 4", 204, new InternalPlayerClient())
        ])
    }
}
