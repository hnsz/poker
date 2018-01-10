package poker.betting;

import poker.dealer.DealerBettingResponse;

public class ReRaise extends Raise {
    ReRaise(DealerBettingResponse dealerBettingResponse, Integer minimum) {
        super(dealerBettingResponse, minimum);
    }
}
