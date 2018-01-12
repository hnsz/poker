package poker.betting;

import poker.Player;
import poker.PlayerStatus;

import java.util.ArrayList;


public class Bet extends BettingAction {
    private Integer _minimum;
    private Integer _maximum;
    private Integer _amount;

    Bet(Pot pot, Player player) {
        super(pot, player);
        _minimum = pot.toCall(player);
        _maximum = player.getStack();
        _amount = _minimum;
        assert _minimum <= _maximum: "Bad values for raise. This player cannot raise unless all-in.";
        super.setString("Bet(" + _minimum +"-"+ _maximum+")");
    }

    public Integer getMinimum() {
        return _minimum;
    }
    public Integer getMaximum() { return _maximum; }
    public Integer getAmount() {
        return _amount;
    }

    public void setAmount(Integer amount) {
        assert amount >= _minimum: "Minimum bet is " + getMinimum();
        _amount = amount;
    }

    @Override
    public void execute() {

        Pot pot = super.getPot();
        Player player = super.getPlayer();

        pot.transfer(getAmount(), player);
        player.setStatus(PlayerStatus.BET);
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
        return (response >= _minimum && response < _maximum);
    }
}
