package test;

import model.card.Deck;
import engine.board.BoardManager;
import engine.GameManager;
import java.io.IOException;

public class DeckTest {
    public static void main(String[] args) {
        try {
            System.out.println("Testing Deck CSV Loading...");

            Deck.loadCardPool(new DummyBoardManager(), new DummyGameManager());

            System.out.println("Deck loaded successfully!");
        } catch (IOException e) {
            System.err.println("Error loading deck: " + e.getMessage());
        }
    }
}

class DummyBoardManager implements BoardManager {
    public int getSplitDistance() {
        return 3; 
    }
}

class DummyGameManager implements GameManager {
}
