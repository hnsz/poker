package poker;

import poker.cardDeck.Card;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private ArrayList<Card> _cards;

    public Board() {
        _cards = new ArrayList<>();
    }

    public void place(Card card) {
        assert _cards.size() <= 5 : "A maximum of 5 cards can be placed on the board.";
        _cards.add(card);
    }

    public ArrayList<Card> getBoardCards() {
        return new ArrayList<>(_cards);
    }
    public ArrayList<Card> getFlopCards() {
        return new ArrayList<>(_cards.subList(0,3));

    }
    public Card getTurnCard() {
        return _cards.get(3);
    }
    public Card getRiverCard() {
        return _cards.get(4);
    }

    public void clear() {
        _cards.clear();
        _cards = new ArrayList<>();
    }
}
