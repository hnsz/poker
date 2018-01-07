package poker.betting;


import poker.Player;
import java.util.ArrayList;

public class BettingDecision {
    private ArrayList<BettingOption> _options = new ArrayList<>();
    private BettingOption _choice;
    private Player _player;

    public BettingDecision(Player player) {
        _player = player;
    }

    public void addOption(BettingOption option) {
        _options.add(option);
    }

    public ArrayList<BettingOption> getOptions() {
        return _options;
    }

    public void choice(BettingOption option) {
        _choice = option;
    }
}
