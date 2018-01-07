package poker._test

import poker.Player
import poker.betting.Bet
import poker.betting.BettingDecision
import poker.betting.BettingOption
import poker.betting.Call
import poker.betting.Check
import poker.betting.Fold
import poker.betting.Raise

class BettingDecisionTest extends GroovyTestCase {
    BettingDecision _bettingDecision
    Player _player

    void setUp() {
        super.setUp()

        Player _player =  new Player("playername", 3001)
        _bettingDecision = new BettingDecision(_player)
    }

    void tearDown() {
        _bettingDecision = null
    }

    void testOption() {
        Fold fold = new Fold(0)
        Call call = new Call(100)
        Check check = new Check(0)
        Bet bet = new Bet(200)
        Raise raise = new Raise(200)

        _bettingDecision.addOption(fold)
        _bettingDecision.addOption(call)
        _bettingDecision.addOption(check)
        _bettingDecision.addOption(bet)
        _bettingDecision.addOption(raise)

        assertEquals(5,_bettingDecision.getOptions().size())
    }


    void testChoice() {
        Fold fold = new Fold(0)
        Call call = new Call(100)
        Raise raise = new Raise(200)

        _bettingDecision.addOption(fold)
        _bettingDecision.addOption(call)
        _bettingDecision.addOption(raise)

        ArrayList<BettingOption> options = _bettingDecision.getOptions()
        _bettingDecision.choice(new Fold(0))

    }
}
