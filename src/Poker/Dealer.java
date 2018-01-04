package Poker;

import java.util.*;
import java.util.stream.Collectors;

public class Dealer {
    private Table _table;
    private Deque<Seat> _rotation;

    public Dealer(Table table) {
        _table = table;
        _rotation = new ArrayDeque<>(_table.getAllSeats());
    }
    public void giveBetOption(Player player) {

    }

    public PlayHand initiateHand(ArrayList<Player> players) {
        ArrayList<Player> playersInHand, ordered;
        PlayHand hand;

        playersInHand = draftPlayersForNextHand(players);
        for(Player p : playersInHand) {
            p.setStatus(PlayerStatus.IN_HAND);
        }
        rotateButtonToNextPlayerInHand();
        ordered = reorder(playersInHand);

        hand = new PlayHand(this, ordered);

        return hand;
    }
    private ArrayList<Player> draftPlayersForNextHand(ArrayList<Player> playersInGame) {
        ArrayList<Player> inHand = new ArrayList<>();

        for (Player p : playersInGame) {
            if(p.status() == PlayerStatus.PLAYING) {
                inHand.add(p);
            }
        }
        return inHand;
    }

    private void rotateButtonToNextPlayerInHand() {
        Seat next, current;
        Set<Seat> seen = new HashSet<>();
        do {
            current = _rotation.pop();
            _rotation.add(current);
            seen.add(current);

            next = _rotation.peek();
            if(next.occupied() && _rotation.peek().getPlayer().status() == PlayerStatus.IN_HAND) {
                break;
            }
        } while (!seen.contains(next));
    }
    private ArrayList<Player> reorder(ArrayList<Player> inHand) {
        ArrayList<Player> ordered;
        Player onButton;
        int idx;

        onButton = _rotation.peek().getPlayer();
        idx = inHand.indexOf(onButton);
        ordered = new ArrayList<>();

        ordered.addAll(inHand.subList(idx, inHand.size()));
        ordered.addAll(inHand.subList(0, idx));


        return ordered;
    }

}
