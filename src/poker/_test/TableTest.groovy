package poker._test

import poker.Seat
import poker.Table
import poker.TableRules

class TableTest extends GroovyTestCase {
    void testGetAvailableSeats() {


        Table table = new Table(new TableRules())

        ArrayList<Seat> available = table.getAvailableSeats()
        assertEquals(9, available.size())
    }
}
