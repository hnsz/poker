package poker.betting;

import poker.Player;
import poker.PlayerStatus;

import java.util.ArrayList;

public class Check extends BettingAction {

    Check(Pot pot, Player player) {
        super(pot, player);
        super.setString("Check");
    }

    @Override
    public void execute() {
        Player player = super.getPlayer();
        player.setStatus(PlayerStatus.CHECK);
    }

    @Override
    public ArrayList<BettingAction> followUps(Player player) {
        ArrayList<BettingAction> actions = new ArrayList<>();

        actions.add(new Check(getPot(), player));
        actions.add(new Bet(getPot(), player));
        actions.add(new AllIn(getPot(), player));

        return actions;

    }

    @Override
    public boolean matchesConstraints(Integer response) {
        return (response == 0);
    }
}
