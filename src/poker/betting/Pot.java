package poker.betting;

import poker.Player;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Pot {
    private ArrayDeque<SubPot> _potlist;

    public Pot(ArrayList<Player> players) {
        _potlist = new ArrayDeque<>();
        _potlist.push(new SubPot(players));
    }

    public void removeShareholder(Player shareholder) {
        for (SubPot subPot : _potlist) {
            subPot.remove(shareholder);
        }
    }
    public void insert(Integer amount, Player player) {
//        for (SubPot pot : _potlist) {
//            pot.
//        }
    }
}
