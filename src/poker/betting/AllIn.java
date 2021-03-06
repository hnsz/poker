package poker.betting;

import poker.Player;
import poker.PlayerStatus;

import java.util.ArrayList;

public class AllIn extends BettingAction {
    Integer _amount;

    AllIn(Pot pot, Player player) {
        super(pot, player);
        _amount = player.getStack();
        super.setName("All-In");
    }
    public static boolean possible(Pot pot, Player player) {
        return true;
    }
    public Integer getAmount() {
        return _amount;
    }
    public void execute() {
        Pot pot = getPot();
        Player player = getPlayer();

        pot.transfer(getAmount(), getPlayer());
        player.setStatus(PlayerStatus.ALL_IN);
    }

    @Override
    public ArrayList<BettingAction> followUps(Player followingPlayer) {
        ArrayList<BettingAction> actions = new ArrayList<>();
        Player currentPlayer = getPlayer();
        Pot pot = getPot();

        if (pot.getShare(followingPlayer ) >= pot.getShare(currentPlayer)) {
            return new ArrayList<>();
        }
        if (pot.toCall(followingPlayer) > 0) {
            actions.add(new Fold(pot, followingPlayer));
        }
        if(pot.toCall(followingPlayer) < followingPlayer.getStack()) {
            actions.add(new Call(pot, followingPlayer));
        }
        if (ReRaise.possible(pot, followingPlayer)) {
            actions.add(new ReRaise(pot, followingPlayer));
        }

        actions.add(new AllIn(pot, followingPlayer));

        return actions;
    }

    @Override
    public ArrayList<BettingAction> requeuingOptions() {
        return new ArrayList<>();
    }

    @Override
    public boolean matchesConstraints(Integer response) {
        return (response == getAmount());
    }
}
