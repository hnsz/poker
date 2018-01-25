package poker.pokerhand;

import poker.cardDeck.Card;

import java.util.ArrayList;

public class HighCard extends PokerHand {
    HighCard(CardSorter sorter) {
        super(sorter);
        setName("High Card");
    }

    @Override
    boolean match() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.addAll(getSorter().sortRank().subList(0, 5));
        setHand(hand);
        setValue(0x100000);
        return true;
    }
}
