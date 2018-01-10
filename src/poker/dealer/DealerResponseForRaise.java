package poker.dealer;

import poker.Player;
import poker.PlayerStatus;
import poker.betting.Pot;
import poker.betting.Raise;

public class DealerResponseForRaise extends DealerBettingResponse {

    DealerResponseForRaise(Pot pot, Player player) {
        super(pot, player);
    }

    @Override
    void execute() {
        Pot pot = super.getPot();
        Player player = super.getPlayer();
        Raise raise = (Raise) getAction();

        pot.transfer(raise.getAmount(), player);
        player.setStatus(PlayerStatus.RAISE);
        System.out.println(player + "Raise: " + raise.getAmount());

    }
}
