package poker.pokerhand;

import poker.cardDeck.Card;
import poker.cardDeck.NullCard;

import java.util.ArrayList;
import java.util.List;

public class FourOfKind extends PokerHand {
    FourOfKind(CardSorter sorter) {
        super(sorter);
        setName("Four of a Kind");
    }

    @Override
    boolean match() {
        CardSorter sorter = getSorter();
        ArrayList<Card> hand = new ArrayList<>();
        Card kicker = new NullCard();
        for (ArrayList<Card> group : sorter.groupRank()) {
            if (group.size() == 4) {
                hand.addAll(group);
            }
            else {
                if (kicker.toInteger() < group.get(0).toInteger()) {
                    kicker = group.get(0);
                }
            }


        }
        if (hand.size() == 4) {
            hand.add(kicker);
            setHand(hand);
            setValue(0x800000);
        }
        return false;
    }
}
