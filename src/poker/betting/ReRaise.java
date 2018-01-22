package poker.betting;

import poker.Player;
import poker.PlayerStatus;

import java.util.ArrayList;

public class ReRaise extends Raise {
    ReRaise(Pot pot, Player player) {
        super(pot, player);
        super.setName("Re-raise");
    }
    @Override
    public void execute() {
        Pot pot = super.getPot();
        Player player = super.getPlayer();

        pot.transfer(getAmount(), player);

        player.setStatus(PlayerStatus.RERAISE);
    }
    @Override
    public ArrayList<BettingAction> followUps(Player followingPlayer) {

        return super.followUps(followingPlayer);
    }

    @Override
    public boolean matchesConstraints(Integer response) {
        return super.matchesConstraints(response);
    }
}
