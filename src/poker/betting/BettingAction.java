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

    public ArrayList<BettingAction> followUps(Player followingPlayer){
        return new ArrayList<>();
    }
    public abstract boolean matchesConstraints( Integer response);
    protected void setString(String s) {_string = s;}

    public void setAmount(Integer amount) {
        // do nothing
    }
    public ArrayList<BettingAction> requeuingOptions() {
        ArrayList<BettingAction> actions = new ArrayList<>();
        actions.add(new NoAction(getPot(), getPlayer()));
        return actions;
    }
    public boolean responseRequired() {
        return true;
    }
    @Override
    public String toString() {
        return _string;
    }
}