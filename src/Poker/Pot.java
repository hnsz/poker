package Poker;

import java.util.ArrayList;

public class Pot {
    private Integer _total = 0;
    private ArrayList<Player> _shareHolders;

    public Pot() {
        _shareHolders = new ArrayList<Player>();
    }

    public void insert(Integer amount, Player player) {
        _total += amount;
        _shareHolders.add(player);
    }
    public void payout(Player winner) {
        winner.addChips(_total);
        _total = 0;
    }

    public void split() {
        for (Player p : _shareHolders) {
            p.addChips(_total/_shareHolders.size());
        }
        _total = 0;
    }

}
