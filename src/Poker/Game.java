package Poker;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private ArrayList<Player> _players = new ArrayList<>();
    private Dealer _dealer;

    public Game(Dealer dealer) {
        _dealer = dealer;
    }

    public void playHand() {
        ArrayList<Player> playersInHand;
        PlayHand playHand;

        playersInHand = _dealer.draftPlayersForNextHand(_players);

        playHand = new PlayHand(_dealer, playersInHand);



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
