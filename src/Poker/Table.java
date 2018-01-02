package Poker;

import java.util.ArrayList;
import java.util.Arrays;

public class Table {
    private TableRules _rules;
    private Dealer _dealer;
    private ArrayList<Seat> _seats;

    public Table(TableRules rules, Dealer dealer) {
        _rules = rules;
        _dealer = dealer;

        _seats = new ArrayList<Seat>(Arrays.asList(new Seat[9]));

    }
    public ArrayList<Seat> getAvailableSeats() {

         return new ArrayList<Seat>(_seats);
    }
}
