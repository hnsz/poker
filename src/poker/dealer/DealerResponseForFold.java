package poker.dealer;

import poker.Player;
import poker.PlayerStatus;
import poker.betting.Pot;

public class DealerResponseForFold extends DealerBettingResponse {

    DealerResponseForFold(Player player, Pot pot) {
        super(pot, player);

    }

    @Override
    void execute() {
        Player player = super.getPlayer();
        Pot pot = super.getPot();
        player.setStatus(PlayerStatus.FOLD);
        System.out.println(player + "FOLDS");

        pot.removeShareholder(player);
    }
}
