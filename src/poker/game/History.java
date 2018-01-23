package poker.game;

import poker.Player;
import poker.betting.BettingAction;
import poker.betting.Pot;
import poker.betting.SubPot;
import poker.cardDeck.Card;
import poker.dealer.Dealer;
import poker.table.Seat;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class History {
    private ArrayDeque<HistoryEntry> _events;
    private String _tableId;

    public History(String tableId) {
        _tableId = tableId;
        _events = new ArrayDeque<>();
    }

    @Override
    public String toString() {
        String out =  "History for table #" + _tableId + '\n';
        while (!_events.isEmpty()) {
            HistoryEntry entry = _events.pop();
            out += entry + "\n";
        }
        return out;
    }

    public void tableJoin(Player player) {
        _events.add(new HistoryStringEntry(player.nick() + " joins table."));
    }
    public void tableLeave(Player player) {
        _events.add(new HistoryStringEntry(player.nick() + " leaves table."));
    }
    public void dealerRotate(Player buttonPlayer) {
        _events.add(new HistoryStringEntry("DealerButton rotates to " + buttonPlayer.nick()));
    }

    public void seatTake(Seat seat, Player player) {
        _events.add(new HistoryStringEntry(player.nick() + " takes seat " + seat.getNumber()));
    }

    public void seatLeave(Seat seat, Player player) {
        _events.add(new HistoryStringEntry(player.nick() + " stands up from seat " + seat.getNumber()));
    }

    public void gameStart(Integer id) {
        _events.add(new HistoryStringEntry("Game " + id + " starts."));
    }

    public void gameEnd(Integer id) {
        _events.add(new HistoryStringEntry("Game " + id + " ends."));

    }

    public void gamePlayerJoin(Integer id, Player player) {
        _events.add(new HistoryStringEntry(player.nick() + " joins game #" + id) );
    }

    public void gamePlayerLeave(Integer id, Player player) {
        _events.add(new HistoryStringEntry(player.nick() + " leaves game #" + id));
    }

    public void handStart(String id) {
        _events.add(new HistoryStringEntry("Hand #" + id + " starts.\n\n"));
    }

    public void handEnd(String id) {
        _events.add(new HistoryStringEntry("Hand #" + id + " ends."));
    }

    public void handPlayerEnter(String id, Player player) {
        _events.add(new HistoryStringEntry(player.nick() + " enters Hand #" + id) );
    }

    public void bettingDecision(Player player, BettingAction action) {
        Integer amount = action.getAmount();
        _events.add(new HistoryStringEntry(player.nick() + " " + action + (amount == 0 ? "" : " $" +amount)));

    }
    public void dealingRound(String round, ArrayList<Card> cards) {
        _events.add(new HistoryStringEntry(round + cards));
    }
    public void dealingRound(String round, Card card) {
        _events.add(new HistoryStringEntry(round + " card dealt on the board " + card));
    }

    public void playerShowdown(Player player, ArrayList<Card> cards) {
        _events.add(new HistoryStringEntry(player + " shows " + cards));
    }

    public void playerHand(Player player) {
        _events.add(new HistoryStringEntry(player + " had hand x with y kicker"));
    }

    public void announceWinner(Player player, SubPot sub) {
        _events.add(new HistoryStringEntry(player + " wins pot "+ sub.getTotal() +", with hand y"));
    }
    public void playerWinner(Player player, Pot pot) {
        _events.add(new HistoryStringEntry(player + "wins x pot"));
    }


}
