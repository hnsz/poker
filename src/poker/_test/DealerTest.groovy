package poker._test

import poker.Dealer
import poker.PlayHand
import poker.Player
import poker.PlayerStatus
import poker.Seat
import poker.Table
import poker.TableRules


class DealerTest extends GroovyTestCase {
    ArrayList<Player> _playersInGame
    Table _table
    Dealer _dealer



    void testInitiateHand() {
        PlayHand hand
        ArrayList<Player> playersInHand

        playersInHand = _dealer.draftPlayersForNextHand(_playersInGame)
        hand = _dealer.initiateHand(playersInHand)

        for (Player p : hand._players) {
            assertEquals(PlayerStatus.IN_HAND, p.status())
        }
        for (Player p : _playersInGame) {
            if (p.status() == PlayerStatus.IN_HAND) {
                assertTrue(hand._players.contains(p))
            }
        }
        for (Seat seat : _table._seats) {
            if(seat.occupied() && seat.getPlayer().status() != PlayerStatus.IN_HAND) {
                assertFalse(hand._players.contains(seat.getPlayer()))
            }
        }
        assertEquals(_dealer.reorder(hand._players), hand._players)
    }
    void testReorder() {
        ArrayList<Player> inHand, expected


        inHand = new ArrayList<>(_playersInGame.subList(2,5))
        inHand.add(0,_playersInGame[0])
        for (Player p : inHand) {
            p.setStatus(PlayerStatus.IN_HAND)
        }

        expected = new ArrayList<>(inHand.subList(1,4))
        expected.add(inHand[0])
        assertEquals(inHand, _dealer.reorder(inHand))

        _dealer.rotateButton(inHand)

        assertEquals(expected, _dealer.reorder(inHand))
    }

    void testRotateButton() {
        ArrayList<Player> playersInHand = _dealer.draftPlayersForNextHand(_playersInGame)
        assertSame(_playersInGame[0], _dealer._rotation.peek().getPlayer())
        _dealer.rotateButton(playersInHand)
        assertSame(_playersInGame[2], _dealer._rotation.peek().getPlayer())
        assertSame(_playersInGame[1], _dealer._rotation.peekLast().getPlayer())
        _dealer.rotateButton(playersInHand)
        _dealer.rotateButton(playersInHand)
        assertSame(_playersInGame[4], _dealer._rotation.peek().getPlayer())
        assertSame(_table._seats[6], _dealer._rotation.peekLast())
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

        _playersInGame[0].setStatus(PlayerStatus.PLAYING)
        _playersInGame[1].setStatus(PlayerStatus.SITTING_OUT)
        _playersInGame[2].setStatus(PlayerStatus.PLAYING)
        _playersInGame[3].setStatus(PlayerStatus.PLAYING)
        _playersInGame[4].setStatus(PlayerStatus.PLAYING)

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
