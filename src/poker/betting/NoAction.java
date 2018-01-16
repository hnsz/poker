package poker.betting;

import poker.Player;

import java.util.ArrayList;

public class NoAction extends BettingAction {
    NoAction(Pot pot, Player player) {
        super(pot, player);
        super.setString("NoAction");
    }

    @Override
    public void execute() {

    }

    @Override
    public ArrayList<BettingAction> followUps(Player followingPlayer) {
        return new ArrayList<>();
    }

    @Override
    public void setAmount(Integer amount) {
        //do nothing
    }

    @Override
    public boolean matchesConstraints(Integer response) {
        return true;
    }
}
