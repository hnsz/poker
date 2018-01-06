package poker._test

import poker.Player
import poker.Seat

class SeatTest extends GroovyTestCase {
    void testTakeOccupiedRemove() {
        Seat seat =  new Seat()
        Player player = new Player("Hans", 15)
        Player removedPlayer



        assertFalse(seat.occupied())

        seat.take(player)
        assertTrue(seat.occupied())

        removedPlayer = seat.remove()
        assertFalse(seat.occupied())

        assertSame(player, removedPlayer)


    }
}
