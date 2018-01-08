package poker.betting;

import poker.Player;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Pot {
    private ArrayDeque<SubPot> _potlist;
    private ArrayList<Player> _activeInPot;

    public Pot(ArrayList<Player> players) {
        _activeInPot = new ArrayList<>(players);
        initPotlist();
    }


    public void removeFromAll(Player shareholder) {
        for (SubPot subPot : _potlist) {
            if (subPot.getShareholders().contains(shareholder)) {
                subPot.remove(shareholder);
            }
        }
        _activeInPot.remove(shareholder);
    }
    public void insert(Integer amount, Player player) {
        Iterator<SubPot> subPots = _potlist.descendingIterator();
        SubPot pot;
        Integer difference;
        Integer restAmount;

        restAmount = amount;

        while (subPots.hasNext()) {
            pot = subPots.next();

            if (pot.getShareholders().contains(player)) {
                difference = pot.upperBound() - pot.share(player);

                if (difference < restAmount) {
                    pot.insert(difference, player);
                    restAmount = restAmount - difference;
                }
                else {
                    pot.insert(restAmount, player);
                    restAmount = 0;
                }
            }
        }
    }
    private void initPotlist() {
        SubPot pot;
        ArrayList<Player> players, byStackSizeAsc;

        _potlist = new ArrayDeque<>();
        players = new ArrayList<>(_activeInPot);
        byStackSizeAsc = new ArrayList<>(_activeInPot);
        byStackSizeAsc.sort(Comparator.comparing(Player::getStack));

        for (Player p : byStackSizeAsc) {
            pot = new SubPot(new ArrayList<>(players));
            players.remove(p);
            _potlist.push(pot);
        }
    }
}
