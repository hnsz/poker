package poker.cardDeck;

import org.testng.internal.collections.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 *
 */


public class Deck {


    private Stack<Card> cards = new Stack<Card>();


    public Deck() {

        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                cards.push(new Card(rank, suit));
            }
        }
        Collections.shuffle(cards);
    }
    public Deck(ArrayList<Pair<Rank,Suit>> input) {
        for (Pair<Rank,Suit> tuple : input) {
            cards.push(new Card(tuple.first(), tuple.second()));
        }
    }

    public Card deal() {
        return cards.pop();
    }
    public Boolean notEmpty() {
        return !cards.empty();
    }
}
