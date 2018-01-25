package poker.pokerhand;

import poker.cardDeck.Card;

import java.util.ArrayList;

public class Calculator {
    CardSorter _sorter;
    public Calculator(ArrayList<Card> cardCombo) {
        assert cardCombo.size() <= 7 : "Internal logic only assured for sets of cards with max size 7.";
        _sorter = new CardSorter(cardCombo);
    }
    public PokerHand resolveHand() {
        ArrayList<PokerHand> hands = new ArrayList<>();
        hands.add(new RoyalFlush(_sorter));
        hands.add(new StraightFlush(_sorter));
        hands.add(new FourOfKind(_sorter));
        hands.add(new FullHouse(_sorter));
        hands.add(new Flush(_sorter));
        hands.add(new Straight(_sorter));
        hands.add(new ThreeOfKind(_sorter));
        hands.add(new TwoPair(_sorter));
        hands.add(new Pair(_sorter));
        hands.add(new HighCard(_sorter));

        for (PokerHand hand : hands) {
            if (hand.match()) {
                return hand;
            }
        }
        return null;
    }

}
