package poker.pokerhand;

import poker.cardDeck.Card;

import java.util.ArrayList;

public class Pair extends PokerHand {
    Pair(CardSorter sorter) {
        super(sorter);
        setName("Pair");
    }

    @Override
    boolean match() {
        ArrayList<Card> pair, rest, hand;
        hand = new ArrayList<>();
        pair = new ArrayList<>();
        rest = new ArrayList<>();
        for (ArrayList<Card> group : getSorter().groupRank()) {
            if (group.size() >= 2 && pair.isEmpty()) {
                pair.addAll(group.subList(0,2));
            }else if(rest.size()<3){
                rest.addAll(group);
            }
        }
        if (!pair.isEmpty()) {
            hand.addAll(pair);
            hand.addAll(rest);
            setHand(hand);
            setValue(0x200000);
            return true;
        }
        return false;
    }
}
