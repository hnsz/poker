package poker;

import poker.betting.BettingDecision;
import poker.table.Seat;
import poker.table.Table;

import java.util.List;

public class Player {
    private String _nick;
    private Integer _id;
    private Table _table;
    private PlayerStatus _status;
    private PlayerClient _client;
    private Integer _stack = 0;

    public Player(String nick, Integer id, PlayerClient client) {
        _client = client;
        _nick = nick;
        _id = id;
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
    public void recieveChips(Integer amount) {
        _stack += amount;
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
        return "Player{" +
                "_nick='" + _nick + '\'' +
                '}';
    }
}