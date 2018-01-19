package poker.betting;

import poker.Player;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Pot {
    private ArrayDeque<SubPot> _potList;
    private ArrayList<Player> _activeInPot;
    private Integer _highestShare;

    public Pot(ArrayList<Player> players) {
        _activeInPot = new ArrayList<>(players);
        _highestShare = 0;
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

    public ArrayList<Player> getShareHolders() {
        HashSet<Player> shareholders = new HashSet<>();
        for (SubPot pot : _potList) {
            shareholders.addAll(pot.getShareholders());
        }
        return new ArrayList<>(shareholders);
    }

    public Integer toCall(Player player) {
        if (getShare(player) < _highestShare) {
            return _highestShare - getShare(player);
        }
        else {
            return 0;
        }
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
        _highestShare = Math.max(_highestShare, getShare(player));
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
    public Integer total() {
        Integer sum = 0;
        for (SubPot sub : _potList) {
            sum = sum + sub.getTotal();
        }
        return sum;
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
        ArrayList<Player> players;
        List<Integer> byStackSizeAsc;

        _potList = new ArrayDeque<>();
        players = new ArrayList<>(_activeInPot);
        byStackSizeAsc = _activeInPot.stream().map(Player::getStack).distinct().collect(Collectors.toList());
        byStackSizeAsc.sort(Integer::compareTo);

        for (Integer stackSize : byStackSizeAsc) {
            pot = new SubPot(new ArrayList<>(players), stackSize);

            players.removeIf(player -> player.getStack() <= stackSize);
            _potList.push(pot);
        }
    }
}
