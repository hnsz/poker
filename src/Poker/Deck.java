package Poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

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

    public Card deal() {
        return cards.pop();
    }
    public Boolean notEmpty() {
        return !cards.empty();
    }
}
