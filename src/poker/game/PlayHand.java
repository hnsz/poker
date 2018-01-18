package poker.game;

import poker.betting.BettingDecision;
import poker.betting.BettingQueueFactory;
import poker.betting.Pot;
import poker.cardDeck.Deck;
import poker.Player;
import poker.dealer.Dealer;
import poker.dealer.DealingRound;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PlayHand {
    private List<Player> _players;
    private Pot _pot;
    private Deck _deck;


    public PlayHand(ArrayList<Player> players) {
        _players = Collections.unmodifiableList(players);
    }

    public void start() {
        _pot = Dea
        _deck = new Deck();

    }

    public void rounds() {
        bettingRound(BettingQueueFactory.handEntryCallQueue(_pot, new ArrayDeque<Player>(_players)));
        DealingRound.holecards(_deck, new ArrayList<Player>(_players));
    }

    private void bettingRound(ArrayDeque<BettingDecision> decisionQueue) {
        BettingDecision bettingDecision;

        while (!decisionQueue.isEmpty()) {

            bettingDecision = decisionQueue.pop();
            bettingDecision.execute(decisionQueue);

        }
    }

}
