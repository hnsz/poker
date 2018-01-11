package poker.betting;

import poker.Player;
import poker.PlayerStatus;
import poker.dealer.DealerBettingResponse;

public class Fold extends BettingAction {

    Fold(Pot pot, Player player) {
        super(pot, player);
    }

    @Override
    public void execute() {
        Player player = super.getPlayer();
        Pot pot = super.getPot();
        player.setStatus(PlayerStatus.FOLD);
        System.out.println(player + "Call");

        pot.removeShareholder(player);
    }
}
