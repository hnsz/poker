package poker;

import poker.cardDeck.Card;

import java.util.ArrayList;

public class Board {
    private ArrayList<Card> _cards;

    public Board() {
        _cards = new ArrayList<>();
    }

    public void place(Card card) {
        assert _cards.size() <= 5 : "A maximum of 5 cards can be placed on the board.";
        _cards.add(card);
    }
    public void clear() {
        _cards.clear();
        _cards = new ArrayList<>();
    }
}
