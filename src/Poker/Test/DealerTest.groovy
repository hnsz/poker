package Poker.Test

import Poker.Dealer
import Poker.PlayHand
import Poker.Player
import Poker.PlayerStatus
import Poker.Seat
import Poker.Table
import Poker.TableRules


class DealerTest extends GroovyTestCase {
    ArrayList<Player> _playersInGame
    Table _table
    Dealer _dealer



    void testDraftPlayersForNextHand() {

    }
    void testInitiateHand() {
        PlayHand hand

        hand = _dealer.initiateHand(_playersInGame)


    }
    void testReorder() {
        ArrayList<Player> inHand, expected


        inHand = new ArrayList<>(_playersInGame.subList(2,5))
        inHand.add(0,_playersInGame[0])
        expected = new ArrayList<>(inHand.subList(1,4))
        expected.add(inHand[0])
        assertEquals(inHand, _dealer.reorder(inHand))

        _dealer.rotateButtonToNextPlayerInHand()

        assertEquals(expected, _dealer.reorder(inHand))
    }

    void testRotateButton() {

        assertSame(_playersInGame[0], _dealer._rotation.peek().getPlayer())
        _dealer.rotateButtonToNextPlayerInHand()
        assertSame(_playersInGame[2], _dealer._rotation.peek().getPlayer())
        assertSame(_playersInGame[1], _dealer._rotation.peekLast().getPlayer())
        _dealer.rotateButtonToNextPlayerInHand()
        _dealer.rotateButtonToNextPlayerInHand()
        assertSame(_playersInGame[4], _dealer._rotation.peek().getPlayer())
        assertSame(_table._seats[6], _dealer._rotation.peekLast())
    }

    private static void playersSetStatus(ArrayList<Player> players) {
    }

    private static ArrayList<Player> getPlayers() {
    }


    void testGiveBetOption() {
    }

    @Override
    void setUp() {
        super.setUp()

        ArrayList<Seat> tableSeats

        _table = new Table(new TableRules())

        _playersInGame = new ArrayList(
                [new Player("zero", 0),
                 new Player("one", 1),
                 new Player("two", 2),
                 new Player("three", 3),
                 new Player("four", 4),
                ])

        _playersInGame[0].setStatus(PlayerStatus.IN_HAND)
        _playersInGame[1].setStatus(PlayerStatus.SITTING_OUT)
        _playersInGame[2].setStatus(PlayerStatus.IN_HAND)
        _playersInGame[3].setStatus(PlayerStatus.IN_HAND)
        _playersInGame[4].setStatus(PlayerStatus.IN_HAND)

        tableSeats = _table.getAllSeats()

        tableSeats[0].take(_playersInGame[0])
        tableSeats[1].take(_playersInGame[1])
        tableSeats[2].take(_playersInGame[2])
        tableSeats[3]
        tableSeats[4]
        tableSeats[5].take(_playersInGame[3])
        tableSeats[6]
        tableSeats[7].take(_playersInGame[4])
        tableSeats[8]

        _dealer = new Dealer(_table)
    }

    @Override
    void tearDown() {
        super.tearDown()
        _table = null
        _playersInGame = null
        _dealer = null
    }
}
