package poker.dealer;

import poker.Player;
import poker.PlayerStatus;
import poker.betting.Pot;

public class DealerResponseForBet extends DealerBettingResponse {
    DealerResponseForBet(Pot pot, Player player) {
        super(pot, player);
    }

    @Override
    void execute() {

        Pot pot = super.getPot();
        Player player = super.getPlayer();

        pot.transfer(getOption().getAmount(), player);
        player.setStatus(PlayerStatus.BET);
        System.out.println(player + "BET: " + getOption().getAmount());
    }
}
