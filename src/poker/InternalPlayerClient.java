package poker;

import poker.betting.BettingDecision;
import poker.betting.BettingAction;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class InternalPlayerClient implements PlayerClient {
    private ArrayDeque<Integer> _responseList;

    public InternalPlayerClient(ArrayList<Integer> responseList) {
        _responseList = new ArrayDeque<>(responseList);
    }

    @Override
    public void getResponse(BettingDecision decision) {
        assert !_responseList.isEmpty(): "No responses left in queue.";
        ArrayList<BettingAction> options = decision.getOptions();
        Integer response;

        response = _responseList.pop();

        decision.select(response);
        System.out.println(decision.getOptions());
        System.out.println(decision.getSelected());
        decision.getSelected().setAmount(response);
    }
}
