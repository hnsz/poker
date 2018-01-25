package poker.pokerhand;

import poker.cardDeck.Card;
import poker.cardDeck.Rank;

import java.util.ArrayList;

public class StraightFlush extends PokerHand {
    StraightFlush(CardSorter sorter) {
        super(sorter);
        setName("Straight Flush");
    }

    @Override
    boolean match() {
        CardSorter sorter = getSorter();
        for (ArrayList<Card> group : sorter.sortRankGroupSuit()) {
            if (group.size() >= 5 &&
                    (group.get(0).getRank() != Rank.ACE ||
                    group.get(4).getRank() != Rank.TEN)) {
                for (int i = 0; i <= group.size() - 5; i++) {
                    if (group.get(i).distance(group.get(i + 4)) == 4) {
                        setHand(group.subList(0,5));
                        setValue(0x900000);
                        return true;
                    }
                }
            }
        }
        setValue(0);
        return false;
    }

}
