package poker.dealer;

import poker.Player;
import poker.PlayerStatus;
import poker.betting.Pot;
import poker.betting.ReRaise;

public class DealerResponseForReRaise extends DealerBettingResponse {
    DealerResponseForReRaise(Pot pot, Player player) {
        super(pot, player);
    }

    @Override
    void execute() {
        Pot pot = super.getPot();
        Player player = super.getPlayer();
        ReRaise reRaise = (ReRaise) getAction();

        pot.transfer(reRaise.getAmount(), player);

        player.setStatus(PlayerStatus.RERAISE);
        System.out.println(player + "Re-raise: " + reRaise.getAmount());

    }
}
