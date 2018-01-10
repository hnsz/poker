package poker.betting;

import poker.dealer.DealerBettingResponse;

public class Call extends BettingAction {
    Integer _amount;

    Call(DealerBettingResponse dealerBettingResponse, Integer amount) {
        super(dealerBettingResponse);
        _amount = amount;
    }

    public Integer getAmount() {
        return _amount;
    }
}
