package poker.betting;

import poker.Player;
import poker.PlayerStatus;

import java.util.ArrayList;

public class Fold extends BettingAction {

    Fold(Pot pot, Player player) {
        super(pot, player);
        super.setString("Fold");
    }

    @Override
    public void execute() {
        Player player = super.getPlayer();
        Pot pot = super.getPot();
        player.setStatus(PlayerStatus.FOLD);

        pot.removeShareholder(player);
    }

    @Override
    public void setAmount(Integer amount) {
        //do nothing
    }

    @Override
    public ArrayList<BettingAction> followUps(Player player) {
        return new ArrayList<>();
    }

    @Override
    public boolean matchesConstraints(Integer response) {
        return (response == 0);
    }
}
