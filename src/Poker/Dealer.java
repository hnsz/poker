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

    public ArrayList<Player> draftPlayersForNextHand(ArrayList<Player> playersInGame) {
        ArrayList<Player> inHand = new ArrayList<>();

        for (Player p : playersInGame) {
            if(p.status() == PlayerStatus.PLAYING) {
                inHand.add(p);
            }
        }
        return inHand;
    }

    public void rotateButtonToNextInHand() {
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
    public ArrayList<Player> getPlayerOrder(ArrayList<Player> inHand) {
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
