package poker.dealer;

import poker.Player;
import poker.betting.BettingAction;
import poker.betting.Pot;

abstract public class DealerBettingResponse {
    private Player _player;
    private Pot _pot;
    private BettingAction _action;

    DealerBettingResponse(Pot pot, Player player) {
       _pot = pot;
       _player = player;
    }

    Pot getPot() {
        return _pot;
    }

    Player getPlayer() {
        return _player;
    }

    public void setAction(BettingAction action) {
        _action = action;
    }
    BettingAction getAction() {
        return _action;
    }


    abstract void execute();
}
