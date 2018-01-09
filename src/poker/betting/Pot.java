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

    public void setWinners(ArrayList<ArrayList<Player>> winnerOrder) {
        for (ArrayList<Player> players: winnerOrder) {
            for (SubPot pot : _potlist) {
                pot.setWinners(players);
            }
        }
    }
    public void payout() {
        Iterator<SubPot> potIterator = _potlist.descendingIterator();
        SubPot pot;
        while (potIterator.hasNext()) {
            pot = potIterator.next();
            pot.payout();
        }
    }

    public void transfer(Integer amount, Player player) {
        assert player.getStack() >= amount: "Transfer amount is higher than stack" ;

        player.subtractChips(amount);
        insert(amount, player);
    }
    public void removeShareholder(Player shareholder) {
        for (SubPot subPot : _potlist) {
            if (subPot.getShareholders().contains(shareholder)) {
                subPot.removeShareholder(shareholder);
                if (subPot.getShareholders().size() == 0) {
                    _potlist.remove(subPot);
                }
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
                difference = pot.upperBound() - pot.getShare(player);

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
            pot = new SubPot(new ArrayList<>(players), p.getStack());
            players.remove(p);
            _potlist.push(pot);
        }
    }
}
