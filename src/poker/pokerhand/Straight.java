package poker.pokerhand;

import poker.cardDeck.Card;

import java.util.ArrayList;

public class Straight extends PokerHand {
    Straight(CardSorter sorter) {
        super(sorter);
        setName("Straight");
    }

    @Override
    boolean match() {
        ArrayList<Card> hand = new ArrayList<>();
        for (Card c : getSorter().sortRank()) {
            if (hand.isEmpty() || hand.get(hand.size()-1).connected(c)) {
                hand.add(c);
            }else if(hand.get(hand.size()-1).distance(c) > 1) {
                hand.clear();
            }

            if (hand.size() == 5) {
                setHand(hand);
                setValue(0x500000);
                return true;
            }
        }
        return false;
    }
}
