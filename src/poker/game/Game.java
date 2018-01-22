package poker.game;

import poker.dealer.Dealer;
import poker.Player;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> _players = new ArrayList<>();
    private Dealer _dealer;

    public Game(Dealer dealer, History history) {
        _dealer = dealer;
    }

    public void playHand() {
        PlayHand playHand;
        ArrayList<Player> players;


        players = _dealer.draftPlayersForNextHand(_players);
        playHand = _dealer.initiateHand(players);
        playHand.handEntryCallRound();

        playHand.showdown();
        playHand.payout();
    }


    public void admitPlayer(Player player) {
        _players.add(player);
    }

    public void removePlayer(Player player) {
        _players.remove(player);
    }
}
