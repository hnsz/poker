package poker._test

import poker.InternalPlayerClient
import poker.Player
import poker.betting.Bet
import poker.betting.BettingDecision
import poker.betting.BettingAction
import poker.betting.Call
import poker.betting.Check
import poker.betting.Fold
import poker.betting.Raise

class BettingDecisionTest extends GroovyTestCase {
    BettingDecision _decision
    Player _player


    void tearDown() {
        _decision = null
    }

    void testBetting() {
        // A betting decision is set up by dealer
        // Dealer prompts player and presents betting decision
        /// setup
        // Player makes a choice by getting response from client
        ArrayList<BettingAction> options = _decision.getOptions()
        _decision.choice(new Call(100))
        // Dealer determines next action

        //  Fold: Remove player from pot
        //  Check: Nothing
        //  Call, Bet, Raise: A transfer from chips to the pot


    }

    void testOption() {

        assertEquals(5,_decision.getOptions().size())
    }


    void testChoice() {
        Fold fold = new Fold(0)
        Call call = new Call(100)
        Raise raise = new Raise(200)

        _decision.addOption(fold)
        _decision.addOption(call)
        _decision.addOption(raise)

        ArrayList<BettingAction> options = _decision.getOptions()
        _decision.choice(options.get(0))

        assertTrue(_decision._options.contains(_decision._choice))
    }
    void setUp() {
        super.setUp()

        ArrayList<Player> _players = new ArrayList<>([
                new Player("SB", 3001, new InternalPlayerClient()),
                new Player("BB", 3002, new InternalPlayerClient()),
                new Player("Player 3", 3003, new InternalPlayerClient()),
                new Player("Player 4", 3004, new InternalPlayerClient()),
                new Player("Button", 3000, new InternalPlayerClient())

                ])
        _decision = new BettingDecision(_player)


        _decision.addOption(new Fold(0))
        _decision.addOption(new Call(100))
        _decision.addOption(new Check(0))
        _decision.addOption(new Bet(200))
        _decision.addOption(new Raise(200))
    }
}
