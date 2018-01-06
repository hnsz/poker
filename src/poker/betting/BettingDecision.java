package poker.betting;

import java.util.ArrayList;

public class BettingDecision {
    ArrayList<BettingOption> _options = new ArrayList<>();
    BettingOption _choice;

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
