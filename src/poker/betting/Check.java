package poker.betting;

import poker.dealer.DealerBettingResponse;

public class Check extends BettingAction {
    Check(Integer amount, DealerBettingResponse dealerBettingResponse) {
        super(amount, dealerBettingResponse);
    }
}
