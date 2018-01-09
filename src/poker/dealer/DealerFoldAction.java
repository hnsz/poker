package poker.dealer;

import poker.Player;
import poker.PlayerStatus;
import poker.betting.Fold;
import poker.betting.Pot;

public class DealerFoldAction extends DealerAction {

    DealerFoldAction(Player player, Pot pot) {
        super(pot, player);

    }

    @Override
    void execute() {
        Player player = super.getPlayer();
        Pot pot = super.getPot();
        player.setStatus(PlayerStatus.FOLD);
        System.out.println(player + " Folds");

        pot.removeShareholder(player);
    }
}
