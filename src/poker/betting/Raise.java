package poker.betting;

import poker.Player;
import poker.PlayerStatus;

import java.util.ArrayList;

public class Raise extends Bet {

    Raise(Pot pot, Player player) {
        super(pot, player);
    }

    @Override
    public void execute() {
        Pot pot = super.getPot();
        Player player = super.getPlayer();

        pot.transfer(getAmount(), player);
        player.setStatus(PlayerStatus.RAISE);
    }
    @Override
    public ArrayList<BettingAction> followUps(Player followingPlayer) {
        ArrayList<BettingAction> actions = new ArrayList<>();

        actions.add(new Fold(getPot(), followingPlayer));
        actions.add(new Call(getPot(), followingPlayer));
        actions.add(new Raise(getPot(), followingPlayer));
        actions.add(new AllIn(getPot(), followingPlayer));

        return actions;
    }

    @Override
    public boolean matchesConstraints(Integer response) {
        return super.matchesConstraints(response);
    }
}
