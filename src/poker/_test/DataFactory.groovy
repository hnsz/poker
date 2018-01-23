package poker._test

import org.testng.internal.collections.Pair
import poker.InternalPlayerClient
import poker.Player
import poker.PlayerClient
import poker.cardDeck.Rank
import poker.cardDeck.Suit


class DataFactory {
    static final ArrayList<InternalPlayerClient> makePlayerClients(List<List<Integer>> clientResponses) {
        ArrayList<InternalPlayerClient> clients = new ArrayList<>()
        for (List responses : clientResponses) {
            clients.add(new InternalPlayerClient(new ArrayList<>(responses)))
        }
        return clients
    }
    static final ArrayList<Player> makePlayers(ArrayList<PlayerClient> clients) {
        ArrayList<Player> players

        players = new ArrayList<>(Arrays.asList(
                new Player("Small Blind", 3001, clients.get(0)),
                new Player("Big Blind", 3002, clients.get(1)),
                new Player("Player 3", 3002, clients.get(2)),
                new Player("Player 4", 3002, clients.get(3)),
                new Player("Button  ", 3002, clients.get(4))
        ));
        players.get(0).receiveChips(120);
        players.get(1).receiveChips(50);
        players.get(2).receiveChips(100);
        players.get(3).receiveChips(120);
        players.get(4).receiveChips(180);

        return players
    }

    static final ArrayList<Pair> staticDeckData() {
        return [
                [Rank.QUEEN, Suit.DIAMONDS] as Pair,
                [Rank.SIX, Suit.HEARTS] as Pair,
                [Rank.SIX, Suit.CLUBS] as Pair,
                [Rank.FOUR, Suit.CLUBS] as Pair,
                [Rank.EIGHT, Suit.DIAMONDS] as Pair,
                [Rank.ACE, Suit.HEARTS] as Pair,
                [Rank.KING, Suit.DIAMONDS] as Pair,
                [Rank.SEVEN, Suit.DIAMONDS] as Pair,
                [Rank.FOUR, Suit.SPADES] as Pair,
                [Rank.KING, Suit.SPADES] as Pair,
                [Rank.SEVEN, Suit.HEARTS] as Pair,
                [Rank.ACE, Suit.DIAMONDS] as Pair,
                [Rank.FIVE, Suit.CLUBS] as Pair,
                [Rank.THREE, Suit.HEARTS] as Pair,
                [Rank.QUEEN, Suit.HEARTS] as Pair,
                [Rank.NINE, Suit.DIAMONDS] as Pair,
                [Rank.TEN, Suit.HEARTS] as Pair,
                [Rank.SIX, Suit.DIAMONDS] as Pair,
                [Rank.QUEEN, Suit.SPADES] as Pair,
                [Rank.NINE, Suit.SPADES] as Pair,
                [Rank.SEVEN, Suit.SPADES] as Pair,
                [Rank.FIVE, Suit.DIAMONDS] as Pair,
                [Rank.TWO, Suit.DIAMONDS] as Pair,
                [Rank.TWO, Suit.CLUBS] as Pair,
                [Rank.ACE, Suit.CLUBS] as Pair,
                [Rank.SIX, Suit.SPADES] as Pair,
                [Rank.SEVEN, Suit.CLUBS] as Pair,
                [Rank.JACK, Suit.DIAMONDS] as Pair,
                [Rank.NINE, Suit.CLUBS] as Pair,
                [Rank.THREE, Suit.SPADES] as Pair,
                [Rank.TEN, Suit.DIAMONDS] as Pair,
                [Rank.QUEEN, Suit.CLUBS] as Pair,
                [Rank.EIGHT, Suit.CLUBS] as Pair,
                [Rank.NINE, Suit.HEARTS] as Pair,
                [Rank.KING, Suit.HEARTS] as Pair,
                [Rank.FIVE, Suit.SPADES] as Pair,
                [Rank.TWO, Suit.HEARTS] as Pair,
                [Rank.FIVE, Suit.HEARTS] as Pair,
                [Rank.TEN, Suit.CLUBS] as Pair,
                [Rank.JACK, Suit.CLUBS] as Pair,
                [Rank.ACE, Suit.SPADES] as Pair,
                [Rank.FOUR, Suit.DIAMONDS] as Pair,
                [Rank.THREE, Suit.DIAMONDS] as Pair,
                [Rank.KING, Suit.CLUBS] as Pair,
                [Rank.JACK, Suit.HEARTS] as Pair,
                [Rank.TEN, Suit.SPADES] as Pair,
                [Rank.THREE, Suit.CLUBS] as Pair,
                [Rank.EIGHT, Suit.HEARTS] as Pair,
                [Rank.EIGHT, Suit.SPADES] as Pair,
                [Rank.TWO, Suit.SPADES] as Pair,
                [Rank.JACK, Suit.SPADES] as Pair,
                [Rank.FOUR, Suit.HEARTS] as Pair
        ]

    }
}
