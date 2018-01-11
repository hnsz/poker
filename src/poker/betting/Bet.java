package poker.betting;

import poker.Player;
import poker.PlayerStatus;

public class Bet extends BettingAction {
    private Integer _minimum;
    private Integer _amount;

    Bet(Pot pot, Player player, Integer minimum) {
        super(pot, player);
        _minimum = minimum;
        _amount = minimum;
    }

    public Integer getMinimum() {
        return _minimum;
    }
    public void setAmount(Integer amount) {
        assert amount >= _minimum: "Minimum bet is " + getMinimum();
        _amount = amount;
    }

    public Integer getAmount() {
        return _amount;
    }
    @Override
    public void execute() {

        Pot pot = super.getPot();
        Player player = super.getPlayer();

        pot.transfer(getAmount(), player);
        player.setStatus(PlayerStatus.BET);
        System.out.println(player + "Bet: " + getAmount());
    }
}
