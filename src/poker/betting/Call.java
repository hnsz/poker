package poker.betting;

import poker.dealer.DealerAction;

public class Call extends BettingOption {
    Call(Integer amount, DealerAction dealerAction) {
        super(amount, dealerAction);
    }
}
