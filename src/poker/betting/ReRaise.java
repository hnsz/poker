package poker.betting;

import poker.dealer.DealerBettingResponse;

public class ReRaise extends BettingAction {
    ReRaise(Integer amount, DealerBettingResponse dealerBettingResponse) {
        super(amount, dealerBettingResponse);
    }
}
