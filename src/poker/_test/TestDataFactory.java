package poker._test;

import poker.InternalPlayerClient;
import poker.Player;
import poker.PlayerClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestDataFactory {
    public static final ArrayList<InternalPlayerClient> makePlayerClients(List<List<Integer>> clientResponses) {
        ArrayList<InternalPlayerClient> clients = new ArrayList<>();
        for (List responses : clientResponses) {
            clients.add(new InternalPlayerClient(new ArrayList<>(responses)));
        }
        return clients;
    }
    public static final ArrayList<Player> makePlayers(ArrayList<PlayerClient> clients) {
        ArrayList<Player> players;

        players = new ArrayList<>(Arrays.asList(
                new Player("sb", 3001, clients.get(0)),
                new Player("bb", 3002, clients.get(1)),
                new Player("3", 3002, clients.get(2)),
                new Player("4", 3002, clients.get(3)),
                new Player("button", 3002, clients.get(4))
        ));
        players.get(0).receiveChips(120);
        players.get(1).receiveChips(50);
        players.get(2).receiveChips(100);
        players.get(3).receiveChips(120);
        players.get(4).receiveChips(180);

        return players;
    }

}
