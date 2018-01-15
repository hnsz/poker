package poker._test

import poker.InternalPlayerClient
import poker.Player
import poker.betting.Bet
import poker.betting.BettingDecision
import poker.betting.BettingAction
import poker.betting.Call
import poker.betting.Check
import poker.betting.Fold
import poker.betting.NoAction
import poker.betting.Pot

class BettingDecisionTest extends GroovyTestCase {
    BettingDecision _decision
    ArrayList<Player> _players
    Pot _pot

    void tearDown() {
        _decision = null
    }

    void testBetting() {

    }

    void testOption() {

        assertEquals(5,_decision.getOptions().size())
    }


    void testChoice() {
        Player BB = _players[1]
        Player player3 = _players[2]



        BettingAction action =  new Bet(_pot, _players[1])
        _pot.transfer(20, BB)
        println(_pot.toCall(player3))
        _decision.setOptions(action.followUps(player3))

        println(_decision.getOptions())
        _decision.select(0)
        println(_decision._selected)


        _decision._selected.execute()
    }
    void setUp() {
        super.setUp()
        _players = new ArrayList<>([
                new Player("SB", 3001, new InternalPlayerClient()),
                new Player("BB", 3002, new InternalPlayerClient()),
                new Player("Player 3", 3003, new InternalPlayerClient()),
                new Player("Player 4", 3004, new InternalPlayerClient()),
                new Player("Button", 3000, new InternalPlayerClient())
                ])
        _players[0]._stack = 120
        _players[1]._stack = 20
        _players[2]._stack = 100
        _players[3]._stack = 150
        _players[4]._stack = 180

        _pot = new Pot(_players)
        _decision = new BettingDecision(_players[2])


    }
}
