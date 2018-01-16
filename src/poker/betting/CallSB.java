package poker.betting;

import poker.Player;
import poker.game.TableRules;

import java.util.ArrayList;

public class CallSB extends Call {
    CallSB(Pot pot, Player player) {
        super(pot, player);
        super.setAmount(TableRules.SB);
        super.setString("SB Call(" + super.getAmount() + ")");
    }
    public boolean matchesConstraints(Integer response) {
        return (response == TableRules.SB);
    }
}
