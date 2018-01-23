package poker.betting;

import poker.Player;
import poker.table.TableRules;

public class CallBB extends Call {
    CallBB(Pot pot, Player player) {
        super(pot, player);
        super.setAmount(TableRules.BB);
        super.setName("Call bb");
    }
    public boolean matchesConstraints(Integer response) {
        return (response == TableRules.BB);
    }
}
