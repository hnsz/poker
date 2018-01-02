package Poker.Test

import Poker.Card
import Poker.Deck


class DeckTest extends GroovyTestCase {
    void testDeal() {
        HashSet<Card> cards = new HashSet<Card>();
        Deck deck = new Deck();
        while (deck.notEmpty()) {
            cards.add(deck.deal());
        }

        assertEquals(52, cards.size());
    }
}
