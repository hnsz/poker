package poker.betting;

import poker.dealer.DealerAction;

public class Fold extends BettingOption {
    Fold(Integer amount, DealerAction dealerAction) {
        super(amount, dealerAction);
    }
}
