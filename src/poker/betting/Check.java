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
    public boolean matchesConstraints(Integer response) {
        return (response == 0);
    }
}
