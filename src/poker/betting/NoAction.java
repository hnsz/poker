package poker.betting;

import poker.Player;

import java.util.ArrayList;

public class NoAction extends BettingAction {
    NoAction(Pot pot, Player player) {
        super(pot, player);
<<<<<<< HEAD
        super.setString("No Action");
=======
>>>>>>> dd15e612a40c38f03a328440d7ae92211e4b5b62
    }

    @Override
    public void execute() {

    }

    @Override
<<<<<<< HEAD
    public boolean responseRequired() {
        return false;
    }
=======
    public ArrayList<BettingAction> followUps(Player followingPlayer) {
        return new ArrayList<>();
>>>>>>> dd15e612a40c38f03a328440d7ae92211e4b5b62
    }

    @Override
    public ArrayList<BettingAction> requeuingOptions() {
        return new ArrayList<>();
    }

    @Override
    public boolean matchesConstraints(Integer response) {
        return true;
    }
}
