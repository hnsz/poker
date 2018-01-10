package poker.dealer;

import poker.Player;
import poker.PlayerStatus;
import poker.betting.Call;
import poker.betting.Pot;

public class DealerResponseForCall extends DealerBettingResponse {
    private Call _call;

    DealerResponseForCall(Pot pot, Player player) {
        super(pot, player);
    }

    @Override
    void execute() {
        Pot pot = super.getPot();
        Player player = super.getPlayer();

        pot.transfer(getOption().getAmount(), player);
        player.setStatus(PlayerStatus.CALL);
        System.out.println(player + "Call: " + getOption().getAmount());
    }
}
