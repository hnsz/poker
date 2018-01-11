package poker.betting;

import poker.Player;
import poker.PlayerStatus;

public class Check extends BettingAction {

    Check(Pot pot, Player player) {
        super(pot, player);
    }

    @Override
    public void execute() {
        Player player = super.getPlayer();
        player.setStatus(PlayerStatus.CHECK);
        System.out.println(player + "Check");
    }
}
