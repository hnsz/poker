package poker.pokerhand;

import poker.cardDeck.Card;

import java.util.ArrayList;

public class PokerHandRankOrdered extends PokerHand {
    PokerHandRankOrdered(ArrayList<Card> cards) {
        super(cards);
    }

    @Override
    boolean match() {
        return false;
    }
}
