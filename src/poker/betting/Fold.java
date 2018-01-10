package poker.betting;

import poker.dealer.DealerBettingResponse;

public class Fold extends BettingAction {
    Fold(DealerBettingResponse dealerBettingResponse) {
        super(dealerBettingResponse);
    }
}
