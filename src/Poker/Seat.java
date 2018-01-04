package Poker;

public class Seat {
    private Player _player;


    private String _id;

    public Seat(String id) {
        _id = id;
    }
    public Seat take(Player player) {
        _player = player;
        return this;
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
                "_player=" + _player +
                ", _id='" + _id + '\'' +
                '}';
    }
}
