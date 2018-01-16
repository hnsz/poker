package poker.betting;

import poker.Player;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BettingQueueFactory {
    public static final ArrayDeque<BettingDecision> preDeal(Pot pot, ArrayDeque<Player> players) {
        ArrayDeque<BettingDecision> queue = new ArrayDeque<>();
        Player sb = players.pop();
        Player bb = players.pop();
        BettingDecision sbCall = new BettingDecision(sb);
        BettingDecision bbCall = new BettingDecision(bb);
        ArrayList<BettingAction> optionsSB = new ArrayList<>();
        ArrayList<BettingAction> optionBB = new ArrayList<>();
        option
        sbCall.setOptions(new CallSB(pot,sb));
    }
    public static final ArrayList<BettingAction> preFlop(Pot pot, Player player) {
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
