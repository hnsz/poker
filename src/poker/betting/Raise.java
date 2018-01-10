package poker.betting;

import poker.dealer.DealerBettingResponse;

public class Raise extends Bet {

    Raise(DealerBettingResponse dealerBettingResponse, Integer minimumRaise) {
        super(dealerBettingResponse, minimumRaise);
    }

}
