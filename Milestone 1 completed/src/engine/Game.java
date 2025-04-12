package engine;

import engine.board.Board;
import model.Colour;
import model.player.CPU;
import model.player.Player;
import model.card.Card;
import model.card.Deck;

import java.io.IOException;
import java.util.ArrayList;

public class Game implements GameManager {
    private final Board board;
    private final ArrayList<Player> players;
    private final ArrayList<Card> firePit;
    private int currentPlayerIndex;
    private int turn;

    public Game(String playerName) throws IOException {
        this.players = new ArrayList<>();
        this.firePit = new ArrayList<>();
        this.turn = 0;
        this.currentPlayerIndex = 0;

        ArrayList<Colour> colours = new ArrayList<>();
        colours.add(Colour.RED);
        colours.add(Colour.BLUE);
        colours.add(Colour.YELLOW);
        colours.add(Colour.GREEN);

        this.board = new Board(colours, this);

        players.add(new Player(playerName, colours.get(0)));
        for (int i = 1; i < colours.size(); i++) {
            players.add(new CPU("CPU " + i, colours.get(i), board));
        }

        Deck.loadCardPool(board, this);
        dealInitialHands();
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Card> getFirePit() {
        return firePit;
    }

    private void dealInitialHands() {
        for (Player player : players) {
            player.getHand().clear();
            player.getHand().addAll(Deck.drawCards());
        }
    }
}
