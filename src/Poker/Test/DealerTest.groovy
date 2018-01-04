package Poker.Test

import Poker.Dealer
import Poker.Player
import Poker.PlayerStatus
import Poker.Seat
import Poker.Table
import Poker.TableRules


class DealerTest extends GroovyTestCase {


    void testReorder() {
        ArrayList<Player> atTable, inHand, expected
        Table table = new Table(new TableRules())
        Dealer dealer

        atTable = getPlayers()
        playersSetStatus(atTable)
        table._seats = getSeats(atTable)

        inHand = new ArrayList<>(atTable.subList(2,5))
        inHand.add(0,atTable[0])
        dealer = new Dealer(table)
        expected = new ArrayList<>(inHand.subList(1,4))
        expected.add(inHand[0])

        assertEquals(inHand, dealer.reorder(inHand))

        dealer.rotateButtonToNextPlayerInHand()

        assertEquals(expected, dealer.reorder(inHand))
    }

    void testGiveBetOption() {
    }

    void testRotateButton() {

        ArrayList<Seat> seats
        ArrayList<Player> players
        Dealer dealer
        Table table

        players = getPlayers()

        playersSetStatus(players)

        seats = getSeats(players)

        table = new Table(new TableRules())
        table._seats = seats

        dealer = new Dealer(table)

        assertSame(players[0], dealer._rotation.peek().getPlayer())
        dealer.rotateButtonToNextPlayerInHand()
        println("First button rotation")
        assertSame(players[2], dealer._rotation.peek().getPlayer())
        assertSame(players[1], dealer._rotation.peekLast().getPlayer())
        dealer.rotateButtonToNextPlayerInHand()
        println("Second button rotation")
        dealer.rotateButtonToNextPlayerInHand()
        println("Third button rotation")
        assertSame(players[4], dealer._rotation.peek().getPlayer())
        assertSame(seats[6], dealer._rotation.peekLast())
    }

    private static void playersSetStatus(ArrayList<Player> players) {
        players[0].setStatus(PlayerStatus.IN_HAND)
        players[1].setStatus(PlayerStatus.SITTING_OUT)
        players[2].setStatus(PlayerStatus.IN_HAND)
        players[3].setStatus(PlayerStatus.IN_HAND)
        players[4].setStatus(PlayerStatus.IN_HAND)
    }

    private static ArrayList<Player> getPlayers() {
       return  new ArrayList(
                [new Player("zero", 0),
                 new Player("one", 1),
                 new Player("two", 2),
                 new Player("three", 3),
                 new Player("four", 4),
                ])
    }

    private static ArrayList<Seat> getSeats(ArrayList<Player> players) {
        return new ArrayList<Seat>([
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
    }

}
