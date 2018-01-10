package poker.betting;


import poker.Player;
import java.util.ArrayList;

public class BettingDecision {
    private ArrayList<BettingAction> _options = new ArrayList<>();
    private BettingAction _choice;
    private Player _player;

    public BettingDecision(Player player) {
        _player = player;
    }

    public void addOption(BettingAction option) {
        _options.add(option);
    }

    public ArrayList<BettingAction> getOptions() {
        return _options;
    }

    public void choice(BettingAction select) {
        for (BettingAction option : _options) {
            if (option.getClass() == select.getClass()) {

            }
        }
    }
}
