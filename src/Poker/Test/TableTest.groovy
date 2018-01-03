package Poker.Test

import Poker.Dealer
import Poker.Seat
import Poker.Table
import Poker.TableRules

class TableTest extends GroovyTestCase {
    void testGetAvailableSeats() {


        Table table = new Table(new TableRules(), new Dealer())

        ArrayList<Seat> available = table.getAvailableSeats()
        assertEquals(9, available.size())
    }
}
