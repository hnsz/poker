package poker.table;

import poker.dealer.Dealer;
import poker.game.History;

import java.util.ArrayList;
import java.util.Arrays;

public class Table {
    private TableRules _rules;
    private Dealer _dealer;
    private ArrayList<Seat> _seats;
    private History _history;
    private String _tableId = "C4F3B4B3";


    public Table(TableRules rules) {
        _rules = rules;
        _history = new History(_tableId);
        _seats = new ArrayList<>(Arrays.asList(
                new Seat(1), new Seat(2), new Seat(3),
                new Seat(4), new Seat(5), new Seat(6),
                new Seat(7), new Seat(8), new Seat(9)
                                ));

    }
    public ArrayList<Seat> getAvailableSeats() {

         return new ArrayList<>(_seats);
    }

    public ArrayList<Seat> getAllSeats() {
        return _seats;
    }
}
