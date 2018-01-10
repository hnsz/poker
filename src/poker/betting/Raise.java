package poker.betting;

import poker.dealer.DealerBettingResponse;

public class Raise extends BettingAction {
    Raise(Integer amount, DealerBettingResponse dealerBettingResponse) {
        super(amount, dealerBettingResponse);
    }
}
