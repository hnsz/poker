package poker.cardDeck;

import groovy.json.JsonOutput;

/**
 * Represents one of the 52 poker cards,
 * Has a rank and a suit
 * Use CardA == CardB returns true if cards are equal in rank
 * Use CardA > CardB returns true if cardA is greater in rank than cardB
 */
public class Card implements Comparable<Card> {
    private Rank _rank;
    private Suit _suit;



    /**
     * @param rank TWO - ACE
     * @param suit SPADES, HEARTS, DIAMONDS, CLUBS
     */
    public Card(Rank rank, Suit suit) {
        _rank = rank;
        _suit = suit;
    }
    @Override
    public String toString() {
        return "" + _rank + _suit;
    }

    public Integer toInteger() {
        return _rank.toInteger();
    }
    public Suit getSuit() {
        return _suit;
    }

    public boolean connected(Card rhs) {
        if (rhs._rank == null) {
            return false;
        }
        return Math.abs(_rank.toInteger() - rhs._rank.toInteger()) == 1;
    }
    public Integer distance(Card rhs) {
        if (rhs._rank == null) {
            return -1;
        }
        return Math.abs(_rank.toInteger() - rhs._rank.toInteger());
    }
    public Rank getRank() {
        return _rank;
    }

    /**
     * @param card
     * @return
     * compare rank of lhs and rhs
     */
    @Override
    public int compareTo(Card card) {
        return _rank.compareTo(card._rank);
    }

    /**
     * @param o
     * @return
     * are rank of lhs and rhs equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (_rank != card._rank) return false;

        return true;
    }

}
