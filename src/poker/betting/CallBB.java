package poker.betting;

import poker.Player;
import poker.game.TableRules;

import java.util.ArrayList;

public class CallBB extends Call {
    CallBB(Pot pot, Player player) {
        super(pot, player);
        super.setAmount(TableRules.BB);
        super.setString("BB Call(" + super.getAmount() + ")");
    }
    public boolean matchesConstraints(Integer response) {
        return (response == TableRules.BB);
    }
    @Override
    public ArrayList<BettingAction> followUps(Player followingPlayer) {
        ArrayList<BettingAction> actions = new ArrayList<>();
        actions.add(new Fold(getPot(), followingPlayer));
        actions.add(new Call(getPot(), followingPlayer));
        actions.add(new Raise(getPot(), followingPlayer));
        actions.add(new AllIn(getPot(), followingPlayer));

        return actions;
    }
}
