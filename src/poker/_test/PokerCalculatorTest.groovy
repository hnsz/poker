package poker._test

import poker.cardDeck.Card
import poker.cardDeck.Deck
import poker.cardDeck.Rank
import poker.cardDeck.Suit
import poker.pokerhand.CardSorter
import poker.pokerhand.Flush
import poker.pokerhand.FourOfKind
import poker.pokerhand.FullHouse
import poker.pokerhand.RoyalFlush
import poker.pokerhand.StraightFlush
import poker.pokerhand.ThreeOfKind


class PokerCalculatorTest extends GroovyTestCase {
    def hands = [
            "RF" :[ new Card(Rank.KING, Suit.CLUBS),
                    new Card(Rank.EIGHT, Suit.DIAMONDS),
                    new Card(Rank.JACK, Suit.CLUBS),
                    new Card(Rank.QUEEN, Suit.CLUBS),
                    new Card(Rank.TEN, Suit.CLUBS),
                    new Card(Rank.KING, Suit.HEARTS),
                    new Card(Rank.ACE, Suit.CLUBS)
                    ],
            "SF" :[ new Card(Rank.FIVE, Suit.CLUBS),
                    new Card(Rank.QUEEN, Suit.DIAMONDS),
                    new Card(Rank.SIX, Suit.CLUBS),
                    new Card(Rank.FOUR, Suit.CLUBS),
                    new Card(Rank.SEVEN, Suit.CLUBS),
                    new Card(Rank.SEVEN, Suit.HEARTS),
                    new Card(Rank.EIGHT, Suit.CLUBS)
                    ],
            "FoK" :[
                    new Card(Rank.QUEEN, Suit.DIAMONDS),
                    new Card(Rank.SIX, Suit.DIAMONDS),
                    new Card(Rank.SIX, Suit.CLUBS),
                    new Card(Rank.QUEEN, Suit.CLUBS),
                    new Card(Rank.SEVEN, Suit.DIAMONDS),
                    new Card(Rank.QUEEN, Suit.HEARTS),
                    new Card(Rank.QUEEN, Suit.SPADES)
                    ],
            "Full" :[
                    new Card(Rank.QUEEN, Suit.DIAMONDS),
                    new Card(Rank.SIX, Suit.DIAMONDS),
                    new Card(Rank.QUEEN, Suit.CLUBS),
                    new Card(Rank.SEVEN, Suit.DIAMONDS),
                    new Card(Rank.SEVEN, Suit.HEARTS),
                    new Card(Rank.QUEEN, Suit.SPADES),
                    new Card(Rank.SEVEN, Suit.SPADES)
                    ],
            "Flush" :[
                    new Card(Rank.QUEEN, Suit.DIAMONDS),
                    new Card(Rank.SIX, Suit.DIAMONDS),
                    new Card(Rank.TWO, Suit.CLUBS),
                    new Card(Rank.THREE, Suit.DIAMONDS),
                    new Card(Rank.SEVEN, Suit.DIAMONDS),
                    new Card(Rank.TWO, Suit.DIAMONDS),
                    new Card(Rank.SIX, Suit.SPADES)
                    ],
            "Set" :[
                    new Card(Rank.QUEEN, Suit.DIAMONDS),
                    new Card(Rank.SIX, Suit.DIAMONDS),
                    new Card(Rank.TWO, Suit.CLUBS),
                    new Card(Rank.THREE, Suit.DIAMONDS),
                    new Card(Rank.SEVEN, Suit.DIAMONDS),
                    new Card(Rank.TWO, Suit.HEARTS),
                    new Card(Rank.TWO, Suit.SPADES)
                    ]
            ]

       void testSort() {

        def combos = hands.values()
        for (combo in combos) {
            println("\n\nInput: " + combo + "\n")
            def rf = new RoyalFlush(combo)
            println(rf)
            def sf = new StraightFlush(combo)
            println(sf)
            def fok = new FourOfKind(combo)
            println(fok)
            def full = new FullHouse(combo)
            println(full)
            def flush = new Flush(combo)
            println(flush)
            def set = new ThreeOfKind(combo)
            println(set)

        }
    }


    void testCardSorter() {
        for (combo in hands.values()) {
            def sorter = new CardSorter(combo)
            println("sortRankGroupSuit " + sorter.sortRankGroupSuit())
            println("groupRank" + sorter.groupRank())
            println("sortSuit " + sorter.sortSuit())
            println("sortRank " + sorter.sortRank())
        }
    }
    private generateRandom7Combos(Integer n) {
        def combos = []
        ArrayList<Card> cards7
        for (def i=0; i<n ; i++) {
            def deck = new Deck()
            while (deck.cards.size() >= 7) {
                cards7 = []
                for (j in [1, 2, 3, 4, 5, 6, 7]) {
                    cards7.add(deck.deal())
                }
                combos << cards7

            }
        }
        return combos
    }

    @Override
    void setUp() {
        super.setUp()
    }
}
