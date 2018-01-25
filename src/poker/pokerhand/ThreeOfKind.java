package poker.pokerhand;

import poker.cardDeck.Card;

import java.util.ArrayList;

public class ThreeOfKind extends PokerHand {
    ThreeOfKind(CardSorter sorter) {
        super(sorter);
        setName("Set");
    }

    @Override
    boolean match() {
        ArrayList<Card> hand = new ArrayList<>();
        for (ArrayList<Card> group : getSorter().groupRank()) {
            if (group.size() == 3) {
                hand.addAll(group);
                for(Card c : getSorter().sortRank()) {
                    if (!hand.contains(c) && hand.size() < 5) {
                        hand.add(c);
                    }
                }
                setHand(hand);
                setValue(0x400000);
                return true;
            }
        }
        return false;
    }
}
