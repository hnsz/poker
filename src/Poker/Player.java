package Poker;

import com.thoughtworks.xstream.mapper.Mapper;

import java.util.List;

public class Player {
    private String _nick;
    private Integer _id;
    private Table _table;
    private PlayerStatus _status;

    public Player(String nick, Integer id) {
        _nick = nick;
        _id = id;
    }
    public void JoinTable(Table table) {
        assert(_table == null);
    }
    public void pickSeat()
    {
        List<Seat>available = _table.getAvailableSeats();
        int seatnumber = 0;

        if (available.size() > 0) {
            Seat first = available.get(seatnumber);

            first.take(this);

        }
    }

    public Player setStatus(PlayerStatus status) {
        _status = status;
        return this;
    }

    public PlayerStatus status() {
        return _status;
    }

}
