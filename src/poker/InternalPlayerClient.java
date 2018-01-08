package poker;

import poker.betting.BettingDecision;
import poker.betting.BettingOption;

import java.util.ArrayList;

public class InternalPlayerClient implements PlayerClient {
    @Override
    public void getResponse(BettingDecision decision) {
        ArrayList<BettingOption> options = decision.getOptions();
//           pick an option
//           according to decision queue
//          command pattern to encapsulate all decisions


        decision.choice(options.get(0));
    }
}
