package poker.game;

import org.testng.internal.collections.Pair;
import poker.Board;
import poker.PlayerStatus;
import poker.betting.BettingDecision;
import poker.betting.BettingQueueFactory;
import poker.betting.Pot;
import poker.cardDeck.Card;
import poker.cardDeck.Deck;
import poker.Player;
import poker.dealer.DealingRound;
import poker.pokerhand.Calculator;
import poker.pokerhand.PokerHand;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;


public class PlayHand {
    private ArrayList<Player> _players;
    private Pot _pot;
    private Deck _deck;
    private Board _board;
    private History _history;
    private String _id = "22";


    public PlayHand(ArrayList<Player> players, Board board, History history) {
        _players = new ArrayList<>(Collections.unmodifiableList(players));
        _history = history;
        for (Player p : _players) {
            _history.handPlayerEnter(_id, p);
        }
        _history.handStart(_id);
        _pot = new Pot(_players);
        _deck = new Deck();
        _board = board;
        _board.clear();
        for (Player player : players) {
            player.removeCards();
        }
    }

    public void handEntryCallRound() {
        bettingRound(BettingQueueFactory.handEntryCallQueue(_pot, new ArrayDeque<Player>(_players), _history));
    }
    public void preFlop() {
        _history.dealHoleCards();
        DealingRound.holecards(_deck, new ArrayList<Player>(_players));
        bettingRound(BettingQueueFactory.preFlop(_pot, new ArrayDeque<Player>(_players), _history));
    }

    public void flop() {
        ArrayList<Player> playersInPot, bettingPlayers;

        playersInPot = _pot.getShareHolders();
        if(playersInPot.size() > 1) {
            DealingRound.flop(_deck, _board);
        }
        _history.dealingRound("Flop, the board is ", _board.getBoardCards());
        bettingPlayers = new ArrayList<>(playersInPot);
        bettingPlayers.removeIf(player -> (player.status() == PlayerStatus.ALL_IN));

        if (bettingPlayers.size() > 1) {
            bettingRound(BettingQueueFactory.postFlop(_pot, new ArrayDeque<>(_players), _history));
        }
    }
    public void turn() {
        ArrayList<Player> playersInPot, bettingPlayers;

        playersInPot = _pot.getShareHolders();
        if(playersInPot.size() > 1) {
            DealingRound.turn(_deck, _board);
        }
        _history.dealingRound("Turn, the board is ", _board.getBoardCards());

        bettingPlayers = new ArrayList<>(playersInPot);
        bettingPlayers.removeIf(player -> (player.status() == PlayerStatus.ALL_IN));

        if (bettingPlayers.size() > 1) {
            bettingRound(BettingQueueFactory.postFlop(_pot, new ArrayDeque<Player>(_players), _history));
        }
    }
    public void river() {
        ArrayList<Player> playersInPot, bettingPlayers;

        playersInPot = _pot.getShareHolders();
        if(playersInPot.size() > 1) {
            DealingRound.river(_deck, _board);
        }
        _history.dealingRound("River, the board is ", _board.getBoardCards());

        bettingPlayers = new ArrayList<>(playersInPot);
        bettingPlayers.removeIf(player -> (player.status() == PlayerStatus.ALL_IN));

        if (bettingPlayers.size() > 1) {
            bettingRound(BettingQueueFactory.postFlop(_pot, new ArrayDeque<Player>(_players), _history));
        }
    }

    public void showdown() {
        _history.showdown();
        ArrayList<Player> players = _pot.getShareHolders();
        PokerHand pokerHand;
        Calculator calculator;
        ArrayList<Pair<Player, PokerHand>> pokerHands = new ArrayList<>();
        for (Player player : players) {
            ArrayList<Card> cards = player.getHolecards();
            cards.addAll(_board.getBoardCards());
            calculator= new Calculator(cards);
            pokerHand = calculator.resolveHand();
            _history.playerShowdown(player, pokerHand);
            pokerHands.add(new Pair<>(player, pokerHand));
        }
        setWinnerOrder(pokerHands);
    }

    private void setWinnerOrder(ArrayList<Pair<Player,PokerHand>> pokerHands) {
        pokerHands.sort(Comparator.comparing((p -> p.second().getValue() *-1)));
        Player player;
        PokerHand pokerHand;
        ArrayList<ArrayList<Player>> winnerOrder;
        ArrayList<Player> nthPlacePlayers;
        winnerOrder = new ArrayList<>();
        nthPlacePlayers = new ArrayList<>();
        Integer handValue = 0;

        for(Pair<Player,PokerHand> tuple: pokerHands) {

            player = tuple.first();
            pokerHand = tuple.second();
            if (nthPlacePlayers.isEmpty() || pokerHand.getValue() == handValue) {
                nthPlacePlayers.add(player);
            } else {
                winnerOrder.add(nthPlacePlayers);
            }
            handValue = pokerHand.getValue();
        }
        _pot.setWinners(winnerOrder);
    }
    public void payout() {
        _pot.payout();
    }
    private void bettingRound(ArrayDeque<BettingDecision> decisionQueue) {
        BettingDecision bettingDecision;

        while (!decisionQueue.isEmpty()) {

            bettingDecision = decisionQueue.pop();
            bettingDecision.execute(decisionQueue);

        }
    }

}
