package poker;

import poker.betting.BettingDecision;

public interface PlayerClient {
    void getResponse(BettingDecision decision);
}
