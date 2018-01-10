package poker.dealer;

import poker.Player;
import poker.PlayerStatus;
import poker.betting.Call;
import poker.betting.Pot;

public class DealerResponseForCall extends DealerBettingResponse {

    DealerResponseForCall(Pot pot, Player player) {
        super(pot, player);
    }

    @Override
    void execute() {
        Pot pot = super.getPot();
        Player player = super.getPlayer();
        Call call = (Call) getAction();


        pot.transfer(call.getAmount(), player);
        player.setStatus(PlayerStatus.CALL);
        System.out.println(player + "Call: " + call.getAmount());
    }
}
