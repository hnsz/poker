package poker.game;

import poker.Dealer;
import poker.Player;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> _players = new ArrayList<>();
    private Dealer _dealer;

    public Game(Dealer dealer) {
        _dealer = dealer;
    }

    public void playHand() {
        PlayHand playHand;
        ArrayList<Player> players;


        players = _dealer.draftPlayersForNextHand(_players);
        playHand = _dealer.initiateHand(players);
        playHand.start();
        playHand.bettingRounds();
        playHand.showdown();
        playHand.payout();
        playHand.end();
    }


    public void admitPlayer(Player player) {
        _players.add(player);
    }

    public void removePlayer(Player player) {
        _players.remove(player);
    }
}