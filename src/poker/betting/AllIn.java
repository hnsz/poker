package poker.betting;

import poker.Player;
import poker.PlayerStatus;

import java.util.ArrayList;

public class AllIn extends BettingAction {
    Integer _amount;

    AllIn(Pot pot, Player player, Integer amount) {

        super(pot, player);
        _amount = amount;
    }
    public Integer getAmount() {
        return _amount;
    }
    public void execute() {
        Pot pot = super.getPot();
        Player player = super.getPlayer();

        pot.transfer(getAmount(), super.getPlayer());
        player.setStatus(PlayerStatus.ALL_IN);
        System.out.println(player + "All-in: " + getAmount());
    }

    @Override
    public BettingAction followUp(Player player) {
        return new Call(super.getPot(), player, getAmount());
    }
}
