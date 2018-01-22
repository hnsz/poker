package poker.betting;

import poker.Player;
import poker.table.TableRules;

public class CallSB extends Call {
    CallSB(Pot pot, Player player) {
        super(pot, player);
        super.setAmount(TableRules.SB);
        super.setName("SB Call");
    }
    public boolean matchesConstraints(Integer response) {
        return (response == TableRules.SB);
    }
}
