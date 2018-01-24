package poker.pokerhand;

import poker.cardDeck.Card;

import java.util.ArrayList;

public class FullHouse extends PokerHand {
    FullHouse(ArrayList<Card> cards) {
        super(cards);
        setName("Full House");
    }

    @Override
    boolean match() {
        ArrayList<ArrayList<Card>> groups = getSorter().groupRank();
        ArrayList<Card> set = new ArrayList<>();
        ArrayList<Card> pair = new ArrayList<>();
        ArrayList<Card> hand = new ArrayList<>();
        for(ArrayList<Card> group : groups) {
            if (group.size() == 3 && set.isEmpty()) {
                set.addAll(group);
            } else if (group.size() >= 2 && pair.isEmpty()) {
                pair.addAll(group.subList(0,2));
            }
        }
        if (set.isEmpty() || pair.isEmpty()) {
            return false;
        }
        else {
            hand.addAll(set);
            hand.addAll(pair);
            setHand(hand);
            setValue(0x700000);
            return true;
        }
    }
}
