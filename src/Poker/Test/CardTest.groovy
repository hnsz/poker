package Poker.Test

import Poker.Card
import Poker.Rank
import Poker.Suit

class CardTest extends GroovyTestCase {

    void testToString() {

        Card card1 = new Card(Rank.ACE, Suit.SPADES);
        Card card2 = new Card(Rank.TWO, Suit.HEARTS);
        Card card3 = new Card(Rank.SEVEN, Suit.DIAMONDS);
        Card card4 = new Card(Rank.JACK, Suit.CLUBS);

        assertToString(card1,"ACE of SPADES");
        assertToString(card2,"TWO of HEARTS");
        assertToString(card3,"SEVEN of DIAMONDS");
        assertToString(card4,"JACK of CLUBS");
    }

    void testCompareble() {
        Card aceOfSpades,aceOfClubs,jackOfSpades;
        aceOfSpades = new Card(Rank.ACE, Suit.SPADES);
        aceOfClubs = new Card(Rank.ACE,Suit.CLUBS);
        jackOfSpades = new Card(Rank.JACK, Suit.SPADES)


        assertTrue(jackOfSpades < aceOfSpades)
        assertTrue(aceOfSpades > jackOfSpades)
        assertTrue(aceOfSpades == aceOfSpades)
        assertTrue(aceOfSpades == new Card(Rank.ACE,Suit.SPADES))
        assertTrue(aceOfClubs == aceOfSpades)
        assertTrue(jackOfSpades != aceOfSpades)

        compareToAndEqualsLogicTable(jackOfSpades,aceOfSpades)
        compareToAndEqualsLogicTable(aceOfSpades,jackOfSpades)
        compareToAndEqualsLogicTable(aceOfClubs,aceOfSpades)
        compareToAndEqualsLogicTable(aceOfSpades,aceOfClubs)
        compareToAndEqualsLogicTable(jackOfSpades,jackOfSpades)
    }

    static void compareToAndEqualsLogicTable(Card lhs, Card rhs) {
        assertEquals(lhs.compareTo(rhs) == 0, lhs.equals(rhs))
    }
}
