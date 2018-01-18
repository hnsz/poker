package poker._test;

import poker.InternalPlayerClient;
import poker.Player;
import poker.PlayerClient;

import java.util.ArrayList;
import java.util.Arrays;

public class TestDataFactory {
    public static final ArrayList<InternalPlayerClient> makePlayerClients(Integer[][] clientResponses) {
        ArrayList<InternalPlayerClient> clients = new ArrayList<>();
        for (Integer[] responses : clientResponses) {
            clients.add(new InternalPlayerClient(new ArrayList<>(Arrays.asList(responses))));
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
        players.get(0).recieveChips(120);
        players.get(1).recieveChips(50);
        players.get(2).recieveChips(100);
        players.get(3).recieveChips(120);
        players.get(4).recieveChips(180);

        return players;
    }

}
