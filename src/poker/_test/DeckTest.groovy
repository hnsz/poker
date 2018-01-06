package poker._test

import poker.cardDeck.Card
import poker.cardDeck.Deck


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
