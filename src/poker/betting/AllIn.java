package poker.betting;

import poker.Player;
import poker.PlayerStatus;

import java.util.ArrayList;

public class AllIn extends BettingAction {
    Integer _amount;

    AllIn(Pot pot, Player player) {
        super(pot, player);
        _amount = player.getStack();
        super.setString("All-In(" +getAmount()+")");
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
<<<<<<< HEAD
        if (pot.toCall(followingPlayer) > 0) {
            actions.add(new Fold(pot, followingPlayer));
=======
>>>>>>> dd15e612a40c38f03a328440d7ae92211e4b5b62
            actions.add(new Call(getPot(), followingPlayer));
        }
        if (ReRaise.possible(pot, followingPlayer)) {
            actions.add(new ReRaise(getPot(), followingPlayer));
        }

        actions.add(new AllIn(getPot(), followingPlayer));

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
