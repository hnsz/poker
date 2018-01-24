package poker.pokerhand;

import poker.cardDeck.Card;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PokerHandRankAndSuitOrdered extends PokerHand {
    public PokerHandRankAndSuitOrdered(ArrayList<Card> cards) {
        super(cards);
    }

    @Override
    boolean match() {
        return false;
    }

}
