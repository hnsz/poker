package Poker;

import java.util.List;
import java.util.stream.Collectors;

public class Dealer {
    private Table _table;

    public Dealer(Table table) {
        _table = table;
    }
    public void giveBetOption(Player player) {

    }

    public List<Player> draftPlayersForNextHand(List<Player> playersInGame) {
        return playersInGame.stream().
                   filter(player -> player.status() == PlayerStatus.PLAYING).
                   collect(Collectors.toList());
    }

}
