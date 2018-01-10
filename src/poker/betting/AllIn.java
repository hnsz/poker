package poker.betting;

import poker.dealer.DealerBettingResponse;

public class AllIn extends BettingAction {
    Integer _amount;

    AllIn(DealerBettingResponse dealerBettingResponse, Integer amount) {
        super(dealerBettingResponse);
        _amount = amount;
    }

}
