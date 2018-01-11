package poker.betting;

import poker.Player;
import poker.PlayerStatus;

public class Raise extends Bet {


    Raise(Pot pot, Player player, Integer minimum) {
        super(pot, player, minimum);
    }

    @Override
    public void execute() {
        Pot pot = super.getPot();
        Player player = super.getPlayer();

        pot.transfer(getAmount(), player);
        player.setStatus(PlayerStatus.RAISE);
        System.out.println(player + "Raise: " + getAmount());

    }
}
