package poker.betting;

import poker.dealer.DealerBettingResponse;

public abstract class BettingAction {
    private DealerBettingResponse _dealerBettingResponse;

    BettingAction(DealerBettingResponse dealerBettingResponse) {
        _dealerBettingResponse = dealerBettingResponse;
        _dealerBettingResponse.setAction(this);
    }

    public DealerBettingResponse getDealerResponse() {
        return _dealerBettingResponse;
    }
}