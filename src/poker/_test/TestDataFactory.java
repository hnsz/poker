package poker._test;

import poker.InternalPlayerClient;
import poker.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class TestDataFactory {
    public static final ArrayList<Player> makePlayers() {
        ArrayList<Player> players;

        players = new ArrayList<>(Arrays.asList(
                new Player("sb", 3001, new InternalPlayerClient(new ArrayList<>(Arrays.asList(10, 30, 0)))),
                new Player("bb", 3002, new InternalPlayerClient(new ArrayList<>(Arrays.asList(20, 20, 10)))),
                new Player("3", 3002, new InternalPlayerClient(new ArrayList<>(Arrays.asList(20, 80)))),
                new Player("4", 3002, new InternalPlayerClient(new ArrayList<>(Arrays.asList(20, 80, 0)))),
                new Player("button", 3002, new InternalPlayerClient(new ArrayList<>(Arrays.asList(20, 0))))
        ));
        players.get(0).recieveChips(120);
        players.get(1).recieveChips(50);
        players.get(2).recieveChips(100);
        players.get(3).recieveChips(120);
        players.get(4).recieveChips(180);

        return players;
    }

}
