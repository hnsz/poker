package poker._test

import poker.cardDeck.Card
import poker.cardDeck.Deck
import poker.cardDeck.Rank
import poker.cardDeck.Suit
import poker.pokerhand.Calculator
import poker.pokerhand.CardSorter
import poker.pokerhand.Flush
import poker.pokerhand.FourOfKind
import poker.pokerhand.FullHouse
import poker.pokerhand.HighCard
import poker.pokerhand.Pair
import poker.pokerhand.RoyalFlush
import poker.pokerhand.Straight
import poker.pokerhand.StraightFlush
import poker.pokerhand.ThreeOfKind
import poker.pokerhand.TwoPair


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
            "Straight" :[
                    new Card(Rank.QUEEN, Suit.DIAMONDS),
                    new Card(Rank.TEN, Suit.SPADES),
                    new Card(Rank.SEVEN, Suit.CLUBS),
                    new Card(Rank.ACE, Suit.DIAMONDS),
                    new Card(Rank.TWO, Suit.DIAMONDS),
                    new Card(Rank.KING, Suit.HEARTS),
                    new Card(Rank.JACK, Suit.SPADES)
            ],
            "Set" :[
                    new Card(Rank.QUEEN, Suit.DIAMONDS),
                    new Card(Rank.SIX, Suit.DIAMONDS),
                    new Card(Rank.TWO, Suit.CLUBS),
                    new Card(Rank.THREE, Suit.DIAMONDS),
                    new Card(Rank.SEVEN, Suit.DIAMONDS),
                    new Card(Rank.TWO, Suit.HEARTS),
                    new Card(Rank.TWO, Suit.SPADES)
                    ],
            "tpair" :[
                    new Card(Rank.QUEEN, Suit.DIAMONDS),
                    new Card(Rank.SIX, Suit.DIAMONDS),
                    new Card(Rank.QUEEN, Suit.CLUBS),
                    new Card(Rank.THREE, Suit.DIAMONDS),
                    new Card(Rank.SIX, Suit.HEARTS),
                    new Card(Rank.TWO, Suit.HEARTS),
                    ],
            "pair" :[
                    new Card(Rank.QUEEN, Suit.DIAMONDS),
                    new Card(Rank.SIX, Suit.DIAMONDS),
                    new Card(Rank.QUEEN, Suit.CLUBS),
                    new Card(Rank.THREE, Suit.DIAMONDS),
                    new Card(Rank.SEVEN, Suit.HEARTS),
                    new Card(Rank.TWO, Suit.HEARTS),
                    ],
            "high" :[
                    new Card(Rank.QUEEN, Suit.DIAMONDS),
                    new Card(Rank.SIX, Suit.DIAMONDS),
                    new Card(Rank.JACK, Suit.CLUBS),
                    new Card(Rank.THREE, Suit.DIAMONDS),
                    new Card(Rank.FOUR, Suit.HEARTS),
                    new Card(Rank.TWO, Suit.HEARTS),
                ]
            ]

        void testHandResolve() {
            for (combo in hands.values()) {
                Calculator calc = new Calculator(combo)
                println(combo)
                println(calc.resolveHand())
            }

        }

       void testHandMatch() {
       CardSorter sorter
        def combos = hands.values()
        for (combo in combos) {
            sorter = new CardSorter(combo)

            println("\n\nInput: " + combo + "\n")

            def rf = new RoyalFlush(sorter)
            rf.match()
            println("" + rf + "\t\t" + rf.getValue())

            def sf = new StraightFlush(sorter)
            sf.match()
            println("" + sf + "\t\t" + sf.getValue())


            def fok = new FourOfKind(sorter)
            fok.match()
            println("" + fok + "\t\t" + fok.getValue())

            def full = new FullHouse(sorter)
            full.match()
            println("" + full + "\t\t" + full.getValue())

            def flush = new Flush(sorter)
            flush.match()
            println("" + flush + "\t\t" + flush.getValue())

            def straight = new Straight(sorter)
            straight.match()
            println("" + straight + "\t\t" + straight.getValue())

            def set = new ThreeOfKind(sorter)
            set.match()
            println("" + set + "\t\t" + set.getValue())

            def tpair = new TwoPair(sorter)
            tpair.match()
            println("" + tpair + "\t\t" + tpair.getValue())

            def pair = new Pair(sorter)
            pair.match()
            println("" + pair + "\t\t" + pair.getValue())

            def high = new HighCard(sorter)
            high.match()
            println("" + high + "\t\t" + high.getValue())

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
