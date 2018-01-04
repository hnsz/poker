package Poker;

import java.util.ArrayList;
import java.util.Arrays;

public class Table {
    private TableRules _rules;
    private Dealer _dealer;
    private ArrayList<Seat> _seats;

    public Table(TableRules rules) {
        _rules = rules;

        _seats = new ArrayList<>(Arrays.asList(new Seat[9]));

    }
    public ArrayList<Seat> getAvailableSeats() {

         return new ArrayList<>(_seats);
    }

    public ArrayList<Seat> getAllSeats() {
        return _seats;
    }
}
