package poker.betting;

import poker.Player;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class BettingQueueFactory {
    public static final ArrayDeque<BettingDecision> handEntryCalls(Pot pot, ArrayDeque<Player> players) {
        ArrayDeque<BettingDecision> queue = new ArrayDeque<>();
        Player sb = players.pop();
        Player bb = players.pop();
        BettingDecision sbCall = new BettingDecision(sb);
        BettingDecision bbCall = new BettingDecision(bb);
        ArrayList<BettingAction> optionsSB = new ArrayList<>();
        ArrayList<BettingAction> optionsBB = new ArrayList<>();
        optionsSB.add(new CallSB(pot,sb));
        optionsBB.add(new CallBB(pot,bb));
        sbCall.setOptions(optionsSB);
        bbCall.setOptions(optionsBB);
        queue.add(sbCall);
        queue.add(bbCall);

        return queue;
    }
    public static final ArrayDeque<BettingDecision> preFlop(Pot pot, ArrayDeque<Player> players) {
        ArrayDeque<BettingDecision> queue = new ArrayDeque<>();

        Player sb = players.pop();
        Player bb = players.pop();
        players.add(sb);

        BettingDecision bbDecision =  new BettingDecision(bb);
        ArrayList<BettingAction> bbOptions = new ArrayList<>();
        bbDecision.setOptions(bbOptions);

        while (!players.isEmpty()) {
            Player p = players.pop();
            BettingDecision decision = new BettingDecision(p);
            ArrayList<BettingAction> options = new ArrayList<>();
            options.add(new Fold(pot, p));
            options.add(new Call(pot, p));
            options.add(new Raise(pot, p));
            options.add(new AllIn(pot, p));

            decision.setOptions(options);
            queue.add(decision);
        }

        queue.add(bbDecision);

        return queue;
    }

    public static final ArrayDeque<BettingDecision> postFlop(Pot pot, ArrayDeque<Player> players) {
        ArrayDeque<BettingDecision> queue = new ArrayDeque<>();

        while (!players.isEmpty()) {
            Player p = players.pop();
            BettingDecision decision = new BettingDecision(p);
            ArrayList<BettingAction> options = new ArrayList<>();
            options.add(new Check(pot, p));
            options.add(new Bet(pot, p));
            options.add(new AllIn(pot, p));

            decision.setOptions(options);
            queue.add(decision);
        }
        return queue;
    }
}
