package poker.betting;

import poker.Player;
import poker.PlayerStatus;

import java.util.ArrayList;

public class Raise extends Bet {

    Raise(Pot pot, Player player) {
        super(pot, player);
        super.setName("Raise");
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
        Pot pot = getPot();

        actions.add(new Fold(pot, followingPlayer));
        if (Call.possible(pot, followingPlayer)) {
            actions.add(new Call(pot, followingPlayer));
        }
        if (ReRaise.possible(pot, followingPlayer)) {
            actions.add(new ReRaise(pot, followingPlayer));
        }

        actions.add(new AllIn(pot, followingPlayer));

        return actions;
    }

    @Override
    public boolean matchesConstraints(Integer response) {
        return super.matchesConstraints(response);
    }
}
