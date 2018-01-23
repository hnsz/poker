package poker.betting;


import poker.Player;
import poker.game.History;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class BettingDecision {
    private ArrayList<BettingAction> _options = new ArrayList<>();
    private BettingAction _selected;
    private Player _player;
    private History _history;

    public BettingDecision(Player player, History history) {
        _player = player;
        _history = history;
    }

    public void setOptions(ArrayList<BettingAction> options) {

        _options = options;
    }

    public ArrayList<BettingAction> getOptions() {
        return _options;
    }

    public void select(Integer response) {
        for (BettingAction option : _options) {
            if (option.matchesConstraints(response)) {
                _selected = option;
                _selected.setAmount(response);
                break;
            }
        }
    }
    public BettingAction getSelected() {
        return _selected;
    }
    private boolean responseRequired() {
        for (BettingAction bettingAction: _options) {
            if (bettingAction.responseRequired()) {
                return true;
            }
        }
        return false;
    }
    public boolean getPlayerResponse() {
        _player.prompt(this);
        assert _selected != null: " Player response wasn't resolved to a selected bettingaction from options";
        return (_selected != null);
    }
    public void execute(ArrayDeque<BettingDecision> bettingQueue) {
        ArrayList<BettingAction> requeuingOptions;
        BettingDecision nextDecision;
        if (responseRequired()) {
            getPlayerResponse();
        }
        else {
            select(0);
        }
        _selected.execute();
        _history.bettingDecision(_player, _selected);

        for (BettingDecision dec : bettingQueue) {
            ArrayList<BettingAction> options = _selected.followUps(dec._player);
            if (!options.isEmpty()) {
                dec.setOptions(options);
            }
        }
        requeuingOptions = _selected.requeuingOptions();
        if (!requeuingOptions.isEmpty()) {
            nextDecision =  new BettingDecision(_player, _history);
            nextDecision.setOptions(requeuingOptions);
            bettingQueue.add(nextDecision);
        }

    }
    @Override
    public String toString() {
        return  _player +
                " Chose: " + _selected +
                " from options" + _options;
    }
}
