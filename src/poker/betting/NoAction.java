package poker.betting;

import poker.Player;

import java.util.ArrayList;

public class NoAction extends BettingAction {
    NoAction(Pot pot, Player player) {
        super(pot, player);
    }

    @Override
    public void execute() {

    }

    @Override
    public ArrayList<BettingAction> followUps(Player followingPlayer) {
        ArrayList<BettingAction> actions = new ArrayList<>();

        actions.add(new Check(getPot(), followingPlayer));
        actions.add(new Bet(getPot(), followingPlayer));
        actions.add(new AllIn(getPot(), followingPlayer));

        return actions;
    }

    @Override
    public void setAmount(Integer amount) {
        //do nothing
    }

    @Override
    public boolean matchesConstraints(Integer response) {
        return false;
    }
}
