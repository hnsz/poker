package poker.pokerhand;

import poker.cardDeck.Card;
import poker.cardDeck.Rank;

import java.util.ArrayList;

public class RoyalFlush extends PokerHand {

    RoyalFlush(ArrayList<Card> cards) {
        super(cards);
        setName("Royal Flush");
    }

    public boolean match() {
        CardSorter sorter = getSorter();
        for (ArrayList<Card> combo : sorter.sortRankGroupSuit()) {
            if (combo.size() >= 5 &&
                    combo.get(0).getRank() == Rank.ACE &&
                    combo.get(4).getRank() == Rank.TEN) {
                setHand(combo.subList(0,5));
                setValue(0xA00000);
                return true;
            }
        }
        setValue(0);
        return false;
    }
}
