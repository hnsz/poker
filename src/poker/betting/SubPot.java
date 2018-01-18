package poker.betting;

import poker.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class SubPot {
    private HashMap<Player,Integer> _shareholders;
    private Integer _total = 0;
    private Integer _upperBound;

    public SubPot(ArrayList<Player> players, Integer upperBound) {
        _shareholders = new HashMap<>();
        for (Player p : players) {
            _shareholders.put(p, 0);
        }
        _upperBound = upperBound;
    }

    public Integer getTotal() {
        return _total;
    }
    public Integer upperBound() {
        return _upperBound;
    }

    public Integer getShare(Player player) {
        assert _shareholders.containsKey(player);

        return _shareholders.get(player);
    }

    public void setWinnersByRemovingLosers(ArrayList<Player> players) {
        ArrayList<Player> winnersOfThisPot =  new ArrayList<>();
        for (Player p : getShareholders()) {
            if (players.contains(p)) {
                winnersOfThisPot.add(p);
            }
        }
        if (winnersOfThisPot.isEmpty()) {
            return;
        } else {
            for (Player p : getShareholders()) {
                if (!winnersOfThisPot.contains(p)) {
                    _shareholders.remove(p);
                }
            }
        }
    }
    public void removeShareholder(Player shareholder) {
        assert _shareholders.containsKey(shareholder);

        _shareholders.remove(shareholder);
    }
    public void insert(Integer amount, Player player) {
        assert (amount + _shareholders.get(player)) <= upperBound();

        _shareholders.replace(player, _shareholders.get(player) + amount);
        _total += amount;
    }
    public void payout() {
        // assert that all winners have payed equal share of the pot
        assert _shareholders.values().stream().distinct().count() <= 1: "not all shareholders have payed equal share";

        List<Player> shareholders = getShareholders();
        for (Player p : shareholders) {
            p.recieveChips(_total/shareholders.size());
        }
        _total = 0;
    }

    public ArrayList<Player> getShareholders() {
        return new ArrayList<>(_shareholders.keySet());
    }
}
