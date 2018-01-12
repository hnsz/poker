package poker.betting;

import poker.Player;
import poker.PlayerStatus;

import java.util.ArrayList;

public class AllIn extends BettingAction {
    Integer _amount;

    AllIn(Pot pot, Player player) {
        super(pot, player);
        _amount = player.getStack();
    }
    public Integer getAmount() {
        return _amount;
    }
    public void execute() {
        Pot pot = super.getPot();
        Player player = super.getPlayer();

        pot.transfer(getAmount(), super.getPlayer());
        player.setStatus(PlayerStatus.ALL_IN);
    }

    @Override
    public ArrayList<BettingAction> followUps(Player followingPlayer) {
        ArrayList<BettingAction> actions = new ArrayList<>();

        actions.add(new Check(getPot(),followingPlayer));

        return actions;
    }

    @Override
    public boolean matchesConstraints(Integer response) {
        return (response == getAmount());
    }
}
