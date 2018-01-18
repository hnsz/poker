package poker._test

import poker.InternalPlayerClient
import poker.Player
import poker.betting.Pot

class BettingRoundTest extends GroovyTestCase {
    private ArrayList<Player> _players
    private Pot _pot


    void testRound() {

    }

    void setUp() {
        super.setUp()

        ArrayList<InternalPlayerClient> clients
        Integer[][] responseValues
        responseValues =[   [10, 30, 0], //sb
                            [20, 20, 10],//bb
                            [20, 80],    //p3
                            [20, 80, 0], //p4
                            [20, 0 ]    ]//button

        clients = TestDataFactory.makePlayerClients(responseValues)
        _players = TestDataFactory.makePlayers(clients)
        _pot = new Pot(_players)
    }

    void tearDown() {
    }
}
