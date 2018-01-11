package poker.betting;

import poker.Player;
import poker.PlayerStatus;
import poker.dealer.DealerBettingResponse;

public class ReRaise extends Raise {
    ReRaise(Pot pot, Player player, Integer minimum) {
        super(pot, player, minimum);
    }
    @Override
    public void execute() {
        Pot pot = super.getPot();
        Player player = super.getPlayer();

        pot.transfer(getAmount(), player);

        player.setStatus(PlayerStatus.RERAISE);
        System.out.println(player + "Re-raise: " + getAmount());

    }
}
