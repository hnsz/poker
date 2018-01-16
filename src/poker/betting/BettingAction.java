package poker.betting;

import poker.Player;

import java.util.ArrayList;


public abstract class BettingAction {
    private Pot _pot;
    private Player _player;
    private String _string;

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
    public abstract ArrayList<BettingAction> followUps(Player followingPlayer);
    public abstract boolean matchesConstraints( Integer response);
    protected void setString(String s) {_string = s;}
    public abstract void setAmount(Integer amount);

    @Override
    public String toString() {
        return _string;
    }
}