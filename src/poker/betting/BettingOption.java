package poker.betting;

import poker.dealer.DealerAction;

public abstract class BettingOption {
    private Integer _amount;
    private DealerAction _dealerAction;

    BettingOption(Integer amount, DealerAction dealerAction) {
        _amount = amount;
        _dealerAction = dealerAction;
    }
    public void setAmount(Integer amount) {
        _amount = amount;
    }
    public Integer getAmount() {
        return _amount;
    }

    public DealerAction getDealerAction() {
        return _dealerAction;
    }
}