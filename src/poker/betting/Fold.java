package poker.betting;

import poker.dealer.DealerBettingResponse;

public class Fold extends BettingAction {
    Fold(Integer amount, DealerBettingResponse dealerBettingResponse) {
        super(amount, dealerBettingResponse);
    }
}
