package poker.dealer;

import poker.Player;
import poker.betting.BettingOption;
import poker.betting.Pot;

abstract public class DealerAction {
    private Player _player;
    private Pot _pot;
    private BettingOption _option;

    DealerAction(Pot pot, Player player) {
       _pot = pot;
       _player = player;
    }

    Pot getPot() {
        return _pot;
    }

    Player getPlayer() {
        return _player;
    }

    void setOption(BettingOption option) {
        _option = option;
    }
    BettingOption getOption() {
        return _option;
    }


    abstract void execute();
}
