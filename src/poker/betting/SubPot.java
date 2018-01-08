package poker.betting;

import poker.Player;

import java.util.ArrayList;
import java.util.HashMap;
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

    public Integer upperBound() {
        return _upperBound;
    }

    public Integer getShare(Player player) {
        assert _shareholders.containsKey(player);

        return _shareholders.get(player);
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
        assert _shareholders.values().stream().distinct().count() == 1;

        Set<Player> shareholders = getShareholders();
        for (Player p : shareholders) {
            p.recieveChips(_total/shareholders.size());
        }
        _total = 0;
    }

    public Set<Player> getShareholders() {
        return _shareholders.keySet();
    }
}
