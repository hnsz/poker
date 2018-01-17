package poker.betting;

import poker.Player;

import java.util.ArrayList;

public class NoAction extends BettingAction {
    NoAction(Pot pot, Player player) {
        super(pot, player);
        super.setString("No Action");
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean responseRequired() {
        return false;
    }
    public ArrayList<BettingAction> followUps(Player followingPlayer) {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<BettingAction> requeuingOptions() {
        return new ArrayList<>();
    }

    @Override
    public boolean matchesConstraints(Integer response) {
        return true;
    }
}
