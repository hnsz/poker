package poker.game;

import poker.Board;
import poker.betting.BettingDecision;
import poker.betting.BettingQueueFactory;
import poker.betting.Pot;
import poker.cardDeck.Deck;
import poker.Player;
import poker.dealer.DealingRound;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;


public class PlayHand {
    private ArrayList<Player> _players;
    private Pot _pot;
    private Deck _deck;
    private Board _board;


    public PlayHand(ArrayList<Player> players, Board board) {
        _players = new ArrayList<>(Collections.unmodifiableList(players));
        _pot = new Pot(_players);
        _deck = new Deck();
        _board = board;
        _board.clear();
        for (Player player : players) {
            player.removeCards();
        }
    }

    public void handEntryCallRound() {
        bettingRound(BettingQueueFactory.handEntryCallQueue(_pot, new ArrayDeque<Player>(_players)));
    }
    public void preFlop() {
        DealingRound.holecards(_deck, new ArrayList<Player>(_players));
        bettingRound(BettingQueueFactory.preFlop(_pot, new ArrayDeque<Player>(_players)));
    }

    public void flop() {
        DealingRound.flop(_deck, _board);
        bettingRound(BettingQueueFactory.postFlop(_pot, new ArrayDeque<Player>(_players)));
    }
    public void turn() {
        DealingRound.turn(_deck, _board);
        bettingRound(BettingQueueFactory.postFlop(_pot, new ArrayDeque<Player>(_players)));
    }
    public void river() {
        DealingRound.river(_deck, _board);
        bettingRound(BettingQueueFactory.postFlop(_pot, new ArrayDeque<Player>(_players)));
    }

    public void showdown() {

    }
    public void payout() {

    }
    private void bettingRound(ArrayDeque<BettingDecision> decisionQueue) {
        BettingDecision bettingDecision;

        while (!decisionQueue.isEmpty()) {

            bettingDecision = decisionQueue.pop();
            bettingDecision.execute(decisionQueue);

        }
    }

}
