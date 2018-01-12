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
import poker.betting.Raise

class BettingDecisionTest extends GroovyTestCase {
    BettingDecision _decision
    ArrayList<Player> _players
    Pot _pot

    void tearDown() {
        _decision = null
    }

    void testBetting() {
        // A betting decision is set up by dealer
        // Dealer prompts player and presents betting decision
        /// setup
        // Player makes a choice by getting response from client
        // Dealer determines next action

        //  Fold: Remove player from pot
        //  Check: Nothing
        //  Call, Bet, Raise: A transfer from chips to the pot


    }

    void testOption() {

        assertEquals(5,_decision.getOptions().size())
    }


    void testChoice() {
        Player player = _players[2]
        player._stack = 100
        _players[1].recieveChips(100)
        _pot.transfer(20, _players[1])
        println(_pot.toCall(player))
        println(_pot.getShare(_players[1]))

        BettingAction noAction =  new Bet(_pot, _players[1])
        _decision.setOptions(noAction.followUps(player))

        println(_decision.getOptions())
        _decision.select(20)
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

        _pot = new Pot(_players)
        _decision = new BettingDecision(_players[2])


    }
}
