package poker.betting;


import poker.Player;
import java.util.ArrayList;

public class BettingDecision {
    private ArrayList<BettingAction> _options = new ArrayList<>();
    private BettingAction _selected;
    private Player _player;

    public BettingDecision(Player player) {
        _player = player;
    }

    public void setOptions(ArrayList<BettingAction> options) {
        _options = options;
    }

    public ArrayList<BettingAction> getOptions() {
        return _options;
    }

    public void select(Integer response) {
        for (BettingAction option : _options) {
            if (option.matchesConstraints(response)) {
                _selected = option;
                break;
            }
        }
    }
}
