package poker.betting;

import poker.dealer.DealerBettingResponse;

public class Bet extends BettingAction {
    private Integer _minimum;
    private Integer _amount;

    Bet(DealerBettingResponse dealerBettingResponse, Integer minimum) {
        super(dealerBettingResponse);
        _minimum = minimum;
        _amount = minimum;
    }

    public Integer getMinimum() {
        return _minimum;
    }
    public void setAmount(Integer amount) {
        assert amount >= _minimum: "Minimum bet is " + getMinimum();
        _amount = amount;
    }

    public Integer getAmount() {
        return _amount;
    }
}
