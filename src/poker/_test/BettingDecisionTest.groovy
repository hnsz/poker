package poker._test

import poker.InternalPlayerClient
import poker.Player
import poker.betting.Bet
import poker.betting.BettingDecision
import poker.betting.BettingOption
import poker.betting.Call
import poker.betting.Check
import poker.betting.Fold
import poker.betting.Raise

class BettingDecisionTest extends GroovyTestCase {
    BettingDecision _decision
    Player _player

    void setUp() {
        super.setUp()

        Player _player =  new Player("playername", 3001, new InternalPlayerClient())
        _decision = new BettingDecision(_player)


        _decision.addOption(new Fold(0))
        _decision.addOption(new Call(100))
        _decision.addOption(new Check(0))
        _decision.addOption(new Bet(200))
        _decision.addOption(new Raise(200))
    }

    void tearDown() {
        _decision = null
    }

    void testBetting() {
        ArrayList<BettingOption> options = _decision.getOptions()
        for (BettingOption opt : options) {
            if (opt.class == Call.class) {
                println(opt.amount)
            }

        }
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

        ArrayList<BettingOption> options = _decision.getOptions()
        _decision.choice(options.get(0))

        assertTrue(_decision._options.contains(_decision._choice))
    }
}
