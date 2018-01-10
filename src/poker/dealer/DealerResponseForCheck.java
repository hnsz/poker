package poker.dealer;

import poker.Player;
import poker.PlayerStatus;
import poker.betting.Pot;

public class DealerResponseForCheck extends DealerBettingResponse {
    DealerResponseForCheck(Pot pot, Player player) {
        super(pot, player);
    }

    @Override
    void execute() {
        Pot pot = super.getPot();
        Player player = super.getPlayer();
        player.setStatus(PlayerStatus.CHECK);
        System.out.println(player + "Check");
    }
}
