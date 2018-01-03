package Poker;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private List<Player> _players = new ArrayList<>();
    private Dealer _dealer;

    public Game(Dealer dealer) {
        _dealer = dealer;
    }

    public PlayHand startHand(ArrayList<Player> playersInGame) {
        List<Player> playing =
            playersInGame.stream().
            filter(player -> player.status() == PlayerStatus.PLAYING).
            collect(Collectors.toList());

        return new PlayHand(playing);
    }

    public void admitPlayer(Player player) {
        _players.add(player);
    }

    public void removePlayer(Player player) {
        _players.remove(player);
    }
}
