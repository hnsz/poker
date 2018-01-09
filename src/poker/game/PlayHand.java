package poker.game;

import poker.dealer.Dealer;
import poker.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayHand {
    private List<Player> _players;
    private Dealer _dealer;

    public PlayHand(Dealer dealer, ArrayList<Player> players) {
        _dealer = dealer;
        _players = Collections.unmodifiableList(players);
    }

    public void start() {

    }
    public void bettingRounds() {

    }
    public void showdown() {

    }
    public void payout() {

    }
    public void end() {

    }

}
