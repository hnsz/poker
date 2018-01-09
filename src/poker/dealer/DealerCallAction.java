package poker.dealer;

import poker.Player;
import poker.PlayerStatus;
import poker.betting.Call;
import poker.betting.Pot;

public class DealerCallAction extends DealerAction{
    private Call _call;

    DealerCallAction(Pot pot, Player player, Call call) {
        super(pot, player);
        _call = call;
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
