package poker.dealer;

import poker.Player;
import poker.PlayerStatus;
import poker.betting.Bet;
import poker.betting.Pot;

public class DealerResponseForBet extends DealerBettingResponse {
    DealerResponseForBet(Pot pot, Player player) {
        super(pot, player);
    }

    @Override
    void execute() {

        Pot pot = super.getPot();
        Player player = super.getPlayer();
        Bet bet = (Bet) getAction();

        pot.transfer(bet.getAmount(), player);
        player.setStatus(PlayerStatus.BET);
        System.out.println(player + "Bet: " + bet.getAmount());
    }
}
