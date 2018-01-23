package poker._test

import org.testng.internal.collections.Pair
import poker.cardDeck.Card
import poker.cardDeck.Deck
import poker.cardDeck.Rank
import poker.cardDeck.Suit


class DeckTest extends GroovyTestCase {
    void testDeal() {
        HashSet<Card> cards = new HashSet<Card>()
        Deck deck = new Deck()
        while (deck.notEmpty()) {
            cards.add(deck.deal())
        }

        assertEquals(52, cards.size())
    }

    void testAltConstruct() {
        ArrayList<Pair<Rank,Suit>> input


        input = [[Rank.TWO, Suit.CLUBS ]as Pair, [Rank.THREE, Suit.CLUBS] as Pair]

        Deck deck = new Deck(input)

        println(deck.cards)
    }

    void testCreateDeckOutput() {
        def deck = new Deck(staticDeckData())
        println(deck.cards)

    }

}
