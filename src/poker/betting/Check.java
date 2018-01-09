package poker.betting;

import poker.dealer.DealerAction;

public class Check extends BettingOption {
    Check(Integer amount, DealerAction dealerAction) {
        super(amount, dealerAction);
    }
}
