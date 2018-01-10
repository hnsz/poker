package poker.betting;

import poker.dealer.DealerBettingResponse;

public abstract class BettingAction {
    private Integer _amount;
    private DealerBettingResponse _dealerBettingResponse;

    BettingAction(Integer amount, DealerBettingResponse dealerBettingResponse) {
        _amount = amount;
        _dealerBettingResponse = dealerBettingResponse;
        _dealerBettingResponse.setOption(this);
    }
    public void setAmount(Integer amount) {
        _amount = amount;
    }
    public Integer getAmount() {
        return _amount;
    }

    public DealerBettingResponse getDealerAction() {
        return _dealerBettingResponse;
    }
}