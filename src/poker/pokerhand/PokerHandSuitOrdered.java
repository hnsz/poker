package poker.pokerhand;

import poker.cardDeck.Card;

import java.util.ArrayList;

public class PokerHandSuitOrdered extends PokerHand {
    @Override
    boolean match() {
        return false;
    }

    PokerHandSuitOrdered(ArrayList<Card> cards) {
        super(cards);
    }
}
