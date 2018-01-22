package poker.betting;

import poker.Player;
import poker.PlayerStatus;

public class Check extends BettingAction {

    Check(Pot pot, Player player) {
        super(pot, player);
        super.setName("Check");
    }

    @Override
    public void execute() {
        Player player = super.getPlayer();
        player.setStatus(PlayerStatus.CHECK);
    }

    @Override
    public boolean matchesConstraints(Integer response) {
        return (response == 0);
    }
}
