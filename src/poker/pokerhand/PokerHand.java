package poker.pokerhand;

import poker.cardDeck.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class PokerHand implements Comparable<PokerHand> {

    private CardSorter  _sorter;
    private ArrayList<Card> _hand;
    private String _name = "Hand";
    private Integer _value;


    PokerHand(CardSorter sorter) {
        _sorter = sorter;
    }

    public CardSorter getSorter() {
        return _sorter;
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

    @Override
    public int compareTo(PokerHand pokerHand) {
        return _value.compareTo(pokerHand._value);
    }
}
