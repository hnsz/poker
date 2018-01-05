package Poker.Test

import Poker.Dealer
import Poker.Game
import Poker.Player
import Poker.PlayerStatus

class GameTest extends GroovyTestCase {
    void testPlayHand() {

        Dealer dealer = new Dealer()
        Game game = new Game(dealer)

        game.admitPlayer(new Player("player 1", 101).setStatus(PlayerStatus.PLAYING))
        game.admitPlayer(new Player("player 2", 102).setStatus(PlayerStatus.PLAYING))
        game.admitPlayer(new Player("player 3", 103).setStatus(PlayerStatus.PLAYING))


    }


    void testPlayerList() {

        Player player1, player2, player3
        Dealer dealer = new Dealer()
        Game game = new Game(dealer)

        player1 = new Player("player 1", 101)
        player2 = new Player("player 2", 102)
        player3 = new Player("player 3", 103)

        game.admitPlayer(player1)
        game.admitPlayer(player2)


        assertEquals(game._players[0]._nick, new String("player 1"))
        assertEquals(game._players[1]._nick, new String("player 2"))

        game.admitPlayer(player3)
        game.removePlayer(player2)
        assertEquals(game._players[0]._nick, new String("player 1"))
        assertEquals(game._players[1]._nick, new String("player 3"))
    }
}
