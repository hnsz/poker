package poker.table;

import poker.Player;

public class Seat {
    private Player _player;
    private Integer _number;


    Seat(Integer number) {
        _number = number;
    }

    public Integer getNumber() {
        return _number;
    }
    public void take(Player player) {
        _player = player;
    }
    public Player getPlayer() {
        return _player;
    }

    public Player remove() {
         Player p = _player;

         _player = null;

        return p;
    }

    public boolean occupied()
    {
        return (_player != null);
    }
    @Override
    public String toString() {
        return "Seat{" +
                "player=" + _player +
                '}';
    }
}
