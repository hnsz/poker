package poker._test

import groovy.json.JsonSlurper
import poker.Board
import poker.PlayerStatus
import poker.betting.BettingDecision
import poker.betting.BettingQueueFactory
import poker.InternalPlayerClient
import poker.Player
import poker.betting.SubPot
import poker.cardDeck.Deck
import poker.dealer.DealingRound
import poker.game.History
import poker.game.PlayHand


class PlayHandTest extends GroovyTestCase {
    private ArrayList<Player> _players
    private Board _board
    private History _history


    void testStart() {
    }

    void testBettingRounds() {
        _players.each {p -> println("${p.nick()}\t\tchips:${p.getStack()}")}

        PlayHand hand = [_players, _board, _history]
        hand._deck = new Deck(DataFactory.staticDeckData())
        _history.dealerRotate(_players[-1])
        ArrayDeque<BettingDecision> handEntryCallQ, preFlopQ, flopQ, turnQ
        ArrayList<Integer> stackValues = []
        ArrayList<PlayerStatus> statusList = []
        handEntryCallQ = BettingQueueFactory.handEntryCallQueue(hand._pot, new ArrayDeque<Player>(hand._players), _history)
        hand.bettingRound(handEntryCallQ)

//        DealingRound.holecards(hand._deck, new ArrayList<Player>(hand._players))
//
//        preFlopQ = BettingQueueFactory.preFlop(hand._pot, new ArrayDeque<Player>(hand._players), _history)
//        hand.bettingRound(preFlopQ)
        hand.preFlop()

        hand.flop()
        hand.turn()
        hand.river()


        hand.showdown()

//                _history.announceWinner(p, sub)
        hand.payout()
        println(_history)
        _players.each {p -> println("${p.nick()}\t\tchips:${p.getStack()} \t${p.getHolecards()}")}

    }


    void testShowdown() {
    }

    void testPayout() {
    }

    void testEnd() {
    }
    void setUp() {
        super.setUp()

        File inputFile = new File("/home/hans-rudolf/IdeaProjects/Inception/src/poker/_test/betting_responses.json" )

        JsonSlurper json = new JsonSlurper()
        def input = json.parseFile(inputFile, "UTF-8")
        assert input instanceof List
        ArrayList<InternalPlayerClient> clients

        clients = DataFactory.makePlayerClients(input)
        _board = new Board()
        _players = DataFactory.makePlayers(clients)
        _history = new History("Poker Game")
    }
}
