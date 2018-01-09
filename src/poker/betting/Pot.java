package poker.betting;

import poker.Player;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Pot {
    private ArrayDeque<SubPot> _potList;
    private ArrayList<Player> _activeInPot;

    public Pot(ArrayList<Player> players) {
        _activeInPot = new ArrayList<>(players);
        initPotList();
    }

    public Integer getShare(Player player) {
        Integer sum = 0;

        for (SubPot pot : _potList) {
            if (pot.getShareholders().contains(player)) {
                sum = sum + pot.getShare(player);
            }
        }
        return sum;
    }
    public void setWinners(ArrayList<ArrayList<Player>> winnerOrder) {
        for (ArrayList<Player> winnerRank : winnerOrder) {
            for (SubPot subPot : _potList) {
                subPot.setWinnersByRemovingLosers(winnerRank);
            }
        }
    }
    public void payout() {
        Iterator<SubPot> potIterator = _potList.descendingIterator();
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
        for (SubPot subPot : _potList) {
            if (subPot.getShareholders().contains(shareholder)) {
                subPot.removeShareholder(shareholder);
                if (subPot.getShareholders().size() == 0) {
                    _potList.remove(subPot);
                }
            }
        }
        _activeInPot.remove(shareholder);
    }

    public void insert(Integer amount, Player player) {
        Iterator<SubPot> subPots = _potList.descendingIterator();
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
    private void initPotList() {
        SubPot pot;
        ArrayList<Player> players, byStackSizeAsc;

        _potList = new ArrayDeque<>();
        players = new ArrayList<>(_activeInPot);
        byStackSizeAsc = new ArrayList<>(_activeInPot);
        byStackSizeAsc.sort(Comparator.comparing(Player::getStack));

        for (Player p : byStackSizeAsc) {
            pot = new SubPot(new ArrayList<>(players), p.getStack());
            players.remove(p);
            _potList.push(pot);
        }
    }
}
