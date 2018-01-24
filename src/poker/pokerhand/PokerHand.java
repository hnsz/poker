package poker.pokerhand;

import poker.cardDeck.Card;

import java.util.ArrayList;
import java.util.List;

abstract class PokerHand {

    private CardSorter sorter;
    private ArrayList<Card> _hand;
    private String _name = "Hand";
    private Integer _value;

    PokerHand(ArrayList<Card> cards) {
        assert cards.size() <= 7 : "Internal logic only assured for sets of cards with max size 7.";
        sorter = new CardSorter(cards);
        match();
    }

    public CardSorter getSorter() {
        return sorter;
    }

    public void setName(String name) {
        _name = name;

    }

    public void setHand(List<Card> cards) {
        _hand = new ArrayList<>(cards);
    }

    public void setValue(Integer value) {
        _value = value;
    }

    public Integer getValue() {
        return _value;
    }

    public ArrayList<Card> getCards() {
        return _hand;
    }

    abstract boolean match();

    @Override
    public String toString() {
        return _name + " " + getCards();
    }
}
