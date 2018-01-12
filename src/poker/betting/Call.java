package poker.betting;

import poker.Player;
import poker.PlayerStatus;

import java.util.ArrayList;

public class Call extends BettingAction {
    Integer _amount;

    Call(Pot pot, Player player) {
        super(pot, player);
        _amount = pot.toCall(player);
        super.setString("Call(" + getAmount() +")");
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
    }

    @Override
    public ArrayList<BettingAction> followUps(Player followingPlayer) {
         ArrayList<BettingAction> actions = new ArrayList<>();

         return actions;
    }

    @Override
    public boolean matchesConstraints(Integer response) {
        return (response == getPot().toCall(getPlayer()));
    }
}
