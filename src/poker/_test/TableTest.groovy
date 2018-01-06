package poker._test

import poker.table.Seat
import poker.table.Table
import poker.table.TableRules

class TableTest extends GroovyTestCase {
    void testGetAvailableSeats() {


        Table table = new Table(new TableRules())

        ArrayList<Seat> available = table.getAvailableSeats()
        assertEquals(9, available.size())
    }
}
