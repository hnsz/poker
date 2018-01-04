package Poker;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> _players = new ArrayList<>();
    private Dealer _dealer;

    public Game(Dealer dealer) {
        _dealer = dealer;
    }

    public void playHand(ArrayList<Player> playersInGame) {
        List<Player> playersInHand;
        PlayHand playHand;

        playersInHand = _dealer.draftPlayersForNextHand(playersInGame);

        /*
        Set correct player order
        Dealer does it.
        NextSeatWithPlayer
        First players enter hand then ordering is set
        Dealer has player assigned to dealerbutton field
        But player or seat? What is someone leaves? If someone else
         */
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
