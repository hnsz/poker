package poker.dealer;

import poker.Player;
import poker.PlayerStatus;
import poker.betting.Pot;

public class DealerResponseForReRaise extends DealerBettingResponse {
    DealerResponseForReRaise(Pot pot, Player player) {
        super(pot, player);
    }

    @Override
    void execute() {
        Pot pot = super.getPot();
        Player player = super.getPlayer();

        pot.transfer(getOption().getAmount(), player);

        player.setStatus(PlayerStatus.RERAISE);
        System.out.println(player + "Re-raise: " + getOption().getAmount());

    }
}
