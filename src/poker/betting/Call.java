package poker.betting;

import poker.Player;
import poker.PlayerStatus;

import java.util.ArrayList;

public class Call extends BettingAction {
    Integer _amount;

    Call(Pot pot, Player player) {
        super(pot, player);
        assert possible(pot,player): "Call is not a possible action, use possible() method before instantiating.";
        _amount = pot.toCall(player);
        super.setName("Call");
    }

    public static boolean possible(Pot pot, Player player) {
        return (pot.toCall(player) < player.getStack());
    }

    public Integer getAmount() {
        return _amount;
    }
    public void setAmount(Integer amount) {
        _amount = amount;
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
