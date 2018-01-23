package poker.dealer;

import poker.Board;
import poker.Player;
import poker.PlayerStatus;
import poker.game.History;
import poker.game.PlayHand;
import poker.table.Seat;
import poker.table.Table;

import java.util.*;

public class Dealer {
    private Table _table;
    private Deque<Seat> _rotation;

    public Dealer(Table table) {
        _table = table;
        _rotation = new ArrayDeque<>(_table.getAllSeats());
    }
    public Player buttonPlayer() {
        return _rotation.peek().getPlayer();
    }
    public void giveBetOption(Player player) {

    }

    public PlayHand initiateHand(ArrayList<Player> players) {
        ArrayList<Player> ordered;
        PlayHand hand;

        for(Player p : players) {
            p.setStatus(PlayerStatus.IN_HAND);
        }
        rotateButton(players);
        ordered = reorder(players);

        hand = new PlayHand(ordered, new Board(), new History("BAD NOTHING"));// OMG BAD

        return hand;
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

    private boolean rotateButton(ArrayList<Player> playersInRotation) {
        Seat buttonSeat, prevButtonSeat;

        prevButtonSeat = _rotation.peek();
        buttonSeat = shiftRotation();

        while (buttonSeat != prevButtonSeat) {
            if (playersInRotation.contains(buttonSeat.getPlayer())) {
                return true;
            }

            buttonSeat = shiftRotation();
        }
        return false;
    }
    private Seat shiftRotation() {
        _rotation.add(_rotation.pop());
        return _rotation.peek();
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
