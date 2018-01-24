package poker.pokerhand

import poker.cardDeck.Card

class CardSorter {
    ArrayList<Card> input

    CardSorter(ArrayList<Card> cards) {
        input = cards
    }

    ArrayList<ArrayList<Card>> sortRankGroupSuit() {
        return input.sort().reverse().groupBy {Card c -> c.getSuit()}.values().sort({li -> li.get(0)}).reverse()
    }

    ArrayList<ArrayList<Card>> groupRank() {
        return input.sort().reverse().groupBy {Card c -> c.getRank()}.values()
    }
    ArrayList<Card> sortRank() {
        return input.sort().reverse()
    }

    ArrayList<Card> sortSuit() {
        return input.sort().reverse().sort({c -> c.getSuit()})
    }

}
