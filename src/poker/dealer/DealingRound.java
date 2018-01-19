package poker.dealer;

import poker.Board;
import poker.Player;
import poker.cardDeck.Deck;

import java.util.ArrayList;

public class DealingRound {
    public static void holecards(Deck deck, ArrayList<Player> players) {
        for (Player player : players) {
            player.receiveCard(deck.deal());
        }
        for (Player player : players) {
            player.receiveCard(deck.deal());
        }
    }

    public static void flop(Deck deck, Board board) {
        board.place(deck.deal());
        board.place(deck.deal());
        board.place(deck.deal());
    }
    public static void river(Deck deck, Board board) {
        board.place(deck.deal());
    }

    public static void turn(Deck deck, Board board) {
        board.place(deck.deal());
    }
}

