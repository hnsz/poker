package poker.dealer;

import poker.Player;
import poker.PlayerStatus;
import poker.betting.AllIn;
import poker.betting.Pot;
import poker.betting.Raise;

public class DealerResponseForAllIn extends DealerBettingResponse {
    DealerResponseForAllIn(Pot pot, Player player) {
        super(pot, player);
    }

    @Override
    void execute() {
        Pot pot = super.getPot();
        Player player = super.getPlayer();
        AllIn allIn = (AllIn) getAction();

        pot.transfer(allIn.getAmount(), player);
        player.setStatus(PlayerStatus.ALL_IN);
        System.out.println(player + "All-in: " + allIn.getAmount());

    }
}
