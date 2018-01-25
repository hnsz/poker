package poker.pokerhand;

import poker.cardDeck.Card;
import poker.cardDeck.NullCard;

import java.util.ArrayList;

public class TwoPair extends PokerHand {
    TwoPair(CardSorter sorter) {
        super(sorter);
        setName("Two Pair");
    }

    @Override
    boolean match() {
        ArrayList<Card> highPair, lowPair, hand;
        Card kicker;
        hand = new ArrayList<>();
        highPair = new ArrayList<>();
        lowPair = new ArrayList<>();
        kicker = new NullCard();


        for (ArrayList<Card> group : getSorter().groupRank()) {
            if (group.size() >= 2) {
                if (highPair.isEmpty()) {
                    highPair.addAll(group.subList(0, 2));
                } else if (lowPair.isEmpty()) {
                    lowPair.addAll(group.subList(0, 2));
                }
            } else if (group.get(0).toInteger() > kicker.toInteger()) {
                kicker = group.get(0);
            }
        }
        hand.addAll(highPair);
        hand.addAll(lowPair);
        hand.add(kicker);

        if (hand.size() == 5) {
            setHand(hand);
            setValue(0x300000);
            return true;
        }
        return false;
    }
}
