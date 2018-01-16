package poker.betting;

import poker.Player;
import poker.game.TableRules;

import java.util.ArrayList;

public class CallBB extends Call {
    CallBB(Pot pot, Player player) {
        super(pot, player);
        super.setAmount(TableRules.BB);
        super.setString("BB Call(" + super.getAmount() + ")");
    }
    public boolean matchesConstraints(Integer response) {
        return (response == TableRules.BB);
    }
}
