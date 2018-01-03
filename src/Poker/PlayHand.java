package Poker;

import java.util.ArrayList;
import java.util.List;

class PlayHand {
    private List<Player> playersInHand;
    PlayHand(List<Player> playing) {
        playersInHand =  new ArrayList<>(playing);
    }
}
