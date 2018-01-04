package Poker

class DealerTest extends GroovyTestCase {
    void testDraftPlayersForNextHand() {
    }


    void testGetPlayerOrder() {
    }

    void testGiveBetOption() {
    }

    void testRotateButton() {

        ArrayList<Seat> seats
        ArrayList<Player> players
        Dealer dealer
        Table table

        players = new ArrayList(
                [new Player("zero", 0),
                 new Player("one", 1),
                 new Player("two", 2),
                 new Player("three", 3),
                 new Player("four", 4),
                ])

        players[0].setStatus(PlayerStatus.IN_HAND)
        players[1].setStatus(PlayerStatus.SITTING_OUT)
        players[2].setStatus(PlayerStatus.IN_HAND)
        players[3].setStatus(PlayerStatus.IN_HAND)
        players[4].setStatus(PlayerStatus.IN_HAND)

        seats = new ArrayList<Seat>([
                new Seat("seat 1").take(players.get(0) as Player),
                new Seat("seat 2").take(players[1] as Player),
                new Seat("seat 3").take(players[2] as Player),
                new Seat("seat 4"),
                new Seat("seat 5"),
                new Seat("seat 6").take(players[3] as Player),
                new Seat("seat 7"),
                new Seat("seat 8").take(players[4] as Player),
                new Seat("seat 9")
        ])

        table = new Table(new TableRules())
        table._seats = seats

        dealer = new Dealer(table)

        assertSame(players[0], dealer._rotation.peek().getPlayer())
        dealer.rotateButtonToNextInHand()
        println("First button rotation")
        assertSame(players[2], dealer._rotation.peek().getPlayer())
        assertSame(players[1], dealer._rotation.peekLast().getPlayer())
        dealer.rotateButtonToNextInHand()
        println("Second button rotation")
        dealer.rotateButtonToNextInHand()
        println("Third button rotation")
        assertSame(players[4], dealer._rotation.peek().getPlayer())
        assertSame(seats[6], dealer._rotation.peekLast())

    }

}
