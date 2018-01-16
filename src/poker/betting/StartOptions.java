package poker.betting;

import poker.Player;

import java.util.ArrayList;

public class StartOptions {
    public static final ArrayList<BettingAction> getPreflop(Pot pot, Player player) {
        ArrayList<BettingAction> actions = new ArrayList<>();

        actions.add(new Fold(pot, player));
        actions.add(new Call(pot, player));
        actions.add(new Raise(pot, player));
        actions.add(new AllIn(pot, player));

        return actions;
    }

    public static final ArrayList<BettingAction> getBB(Pot pot, Player player) {
        ArrayList<BettingAction> actions = new ArrayList<>();
        actions.add(new Check(pot, player));
        actions.add(new Raise(pot, player));
        actions.add(new AllIn(pot, player));
        return actions;
    }
    public static final ArrayList<BettingAction> get(Pot pot, Player followingPlayer) {
        ArrayList<BettingAction> actions = new ArrayList<>();

        actions.add(new Check(pot, followingPlayer));
        actions.add(new Bet(pot, followingPlayer));
        actions.add(new AllIn(pot, followingPlayer));

        return actions;
    }
}
