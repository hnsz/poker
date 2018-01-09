package poker.betting;

import poker.dealer.DealerAction;

public class Raise extends BettingOption {
    Raise(Integer amount, DealerAction dealerAction) {
        super(amount, dealerAction);
    }
}
