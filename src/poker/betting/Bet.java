package poker.betting;

import poker.dealer.DealerAction;

public class Bet extends BettingOption {

    Bet(Integer amount, DealerAction dealerAction) {
        super(amount, dealerAction);
    }
}
