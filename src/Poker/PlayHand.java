package Poker;

import java.util.ArrayList;
import java.util.List;

public class PlayHand {
    private List<Player> playersInHand;
    private Dealer _dealer;

    public PlayHand(Dealer dealer, List<Player> playing) {
        playersInHand =  new ArrayList<>(playing);
        _dealer = dealer;
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
