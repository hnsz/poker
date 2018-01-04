package Poker;

import java.util.ArrayList;
import java.util.List;

public class PlayHand {
    private List<Player> _playersInHand;
    private Dealer _dealer;

    PlayHand(Dealer dealer, ArrayList<Player> playing) {
        _dealer = dealer;

        for(Player p : playing) {
            p.setStatus(PlayerStatus.IN_HAND);
        }

        _dealer.rotateButtonToNextInHand();
        _playersInHand = _dealer.getPlayerOrder(playing);
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
