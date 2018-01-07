package poker.betting;

import poker.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class SubPot {
    private HashMap<Player,Integer> _shares;
    private Integer _maxShare = 0;
    private Integer _total = 0;

    public SubPot(ArrayList<Player> players) {
        _shares = new HashMap<>();
        for (Player p : players) {
            _shares.put(p, 0);
        }
    }

    public void remove(Player shareholder) {
        _shares.remove(shareholder);
    }
    public void insert(Integer amount, Player player) {
        _shares.replace(player, _shares.get(player) + amount);
        _total += amount;
        updateMax(_shares.get(player));
    }
    public void payout() {
        Set<Player> shareholder = getShareholders();
        for (Player p : shareholder) {
            p.recieveChips(_total/shareholder.size());
        }
        _total = 0;
    }

    public void matchAmount(Player player, Integer amount) {
        Integer toMatch = _maxShare - _shares.get(player);
//
//        delegate or handle outside
// if (toMatch > 0) {
//            insert(Math.min(toMatch, amount), player);
//        }
//        if ()
    }
    public Set<Player> getShareholders() {
        return _shares.keySet();
    }
    private void updateMax(Integer amount) {
        _maxShare = Math.max(_maxShare, amount);
    }
}
