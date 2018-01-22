package poker;

import poker.betting.BettingDecision;
import poker.cardDeck.Card;
import poker.table.Seat;
import poker.table.Table;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String _nick;
    private Integer _id;
    private Table _table;
    private PlayerStatus _status;
    private PlayerClient _client;
    private Integer _stack = 0;
    private ArrayList<Card> _holecards;

    public Player(String nick, Integer id, PlayerClient client) {
        _client = client;
        _nick = nick;
        _id = id;
        _holecards = new ArrayList<>();
    }

    public String nick() {
        return _nick;
    }

    public ArrayList<Card> getHolecards() {
        return _holecards;
    }

    public void JoinTable(Table table) {
        _table = table;
    }
    public void pickSeat() {
        List<Seat>available = _table.getAvailableSeats();
        int seatNumber = 0;

        if (available.size() > 0) {
            Seat first = available.get(seatNumber);

            first.take(this);

        }
    }
    public void receiveChips(Integer amount) {
        _stack += amount;
    }

    public void receiveCard(Card card) {
        assert _holecards.size() <= 2 : "Player can hold 2 cards max.";
        _holecards.add(card);
    }

    public void removeCards() {
        _holecards.clear();
        _holecards = new ArrayList<>();
    }

    public void subtractChips(Integer amount) {
        _stack -= amount;
    }

    public Player setStatus(PlayerStatus status) {
        _status = status;
        return this;
    }

    public Integer getStack() {
        return _stack;
    }

    public void prompt(BettingDecision decision) {
        _client.getResponse(decision);
    }
    public PlayerStatus status() {
        return _status;
    }

    @Override
    public String toString() {
        return _nick;
    }
}
