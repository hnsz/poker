package poker.betting;

import poker.Player;
import poker.PlayerStatus;
import poker.dealer.DealerBettingResponse;

public class Call extends BettingAction {
    Integer _amount;

    Call(Pot pot, Player player, Integer amount) {
        super(pot, player);
        _amount = amount;
    }


    public Integer getAmount() {
        return _amount;
    }

    @Override
    public void execute() {
        Pot pot = super.getPot();
        Player player = super.getPlayer();


        pot.transfer(getAmount(), player);
        player.setStatus(PlayerStatus.CALL);
        System.out.println(player + "Call: " + getAmount());
    }
}
