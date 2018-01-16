package poker.betting;

import poker.Player;
import poker.PlayerStatus;
import poker.game.TableRules;

import java.util.ArrayList;


public class Bet extends BettingAction {
    private Integer _minimum;
    private Integer _maximum;
    private Integer _amount;

    Bet(Pot pot, Player player) {
        super(pot, player);
        _minimum = pot.toCall(player) + TableRules.BB;
        _maximum = player.getStack();
        assert possible(pot, player): "Check possible() method before instantiating this class.";
        super.setString("Bet(" + _minimum +"-"+ _maximum+")");
    }

    public static boolean possible(Pot pot, Player player) {
        Integer minimum, maximum;
        minimum = pot.toCall(player) + TableRules.BB;
        maximum = player.getStack();
        return (minimum < maximum);
    }

    public Integer getMinimum() {
        return _minimum;
    }
    public Integer getMaximum() { return _maximum; }
    public Integer getAmount() {
        return _amount;
    }

    public void setAmount(Integer amount) {
        assert amount >= _minimum: "Minimum amount is " + getMinimum();
        assert amount < _maximum: "Maximum amount exceeded";
        _amount = amount;
    }

    @Override
    public void execute() {
        assert _amount != null: "No amount is set.";
        assert _amount >= _minimum && _amount < _maximum: "Amount is not between minimum and maximum.";
        Pot pot = super.getPot();
        Player player = super.getPlayer();

        pot.transfer(getAmount(), player);
        player.setStatus(PlayerStatus.BET);
    }

    @Override
    public ArrayList<BettingAction> followUps(Player followingPlayer) {
        ArrayList<BettingAction> actions = new ArrayList<>();
        Pot pot = getPot();


        if (pot.toCall(followingPlayer) > 0) {
            actions.add(new Fold(pot, followingPlayer));
            actions.add(new Call(pot, followingPlayer));
        }
        if (Raise.possible(pot, followingPlayer)) {
            actions.add(new Raise(pot, followingPlayer));
        }
        actions.add(new AllIn(pot, followingPlayer));

        return actions;
    }

    @Override
    public boolean matchesConstraints(Integer response) {
        return (response >= _minimum && response < _maximum);
    }
}
