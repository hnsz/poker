package poker.betting;

import poker.dealer.DealerBettingResponse;

public class Call extends BettingAction {
    Call(Integer amount, DealerBettingResponse dealerBettingResponse) {
        super(amount, dealerBettingResponse);
    }
}
