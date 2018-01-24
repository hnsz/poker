package poker.pokerhand;

import poker.cardDeck.Card;

import java.util.ArrayList;

public class Flush extends PokerHand {
    Flush(ArrayList<Card> cards) {
        super(cards);
        setName("Flush");
    }

    @Override
    boolean match() {

        for (ArrayList<Card> group : getSorter().sortRankGroupSuit()) {
            if (group.size() >= 5) {
                setHand(group.subList(0,5));
                setValue(0x600000);
                return true;
            }
        }
        return false;
    }
}
