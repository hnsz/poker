package poker.betting;

import poker.Player;

import java.util.ArrayList;


public abstract class BettingAction {
    private Pot _pot;
    private Player _player;

    BettingAction(Pot pot, Player player) {
        _pot = pot;
        _player = player;

    }
    public Pot getPot() {
        return _pot;
    }

    public Player getPlayer() {
        return _player;
    }
    public abstract void execute();
    public abstract BettingAction followUp(Player player);
}