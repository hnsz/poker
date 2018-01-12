package poker.betting;

import poker.Player;
import poker.PlayerStatus;

public class ReRaise extends Raise {
    ReRaise(Pot pot, Player player) {
        super(pot, player);
        super.setString("Re-raise(" + super.getMinimum() +"-" + super.getMaximum()+")");
    }
    @Override
    public void execute() {
        Pot pot = super.getPot();
        Player player = super.getPlayer();

        pot.transfer(getAmount(), player);

        player.setStatus(PlayerStatus.RERAISE);
    }

    @Override
    public boolean matchesConstraints(Integer response) {
        return super.matchesConstraints(response);
    }
}
