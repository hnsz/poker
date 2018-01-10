package poker.betting;

import poker.dealer.DealerBettingResponse;

public class Bet extends BettingAction {

    Bet(Integer amount, DealerBettingResponse dealerBettingResponse) {
        super(amount, dealerBettingResponse);
    }
}
