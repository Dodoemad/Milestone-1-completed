package model.card;
import model.card.standard.*;
import model.card.wild.Burner;
import model.card.wild.Saver;
import engine.GameManager;
import engine.board.BoardManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Deck {
    private static final String CARDS_FILE = "Cards.csv";
    private static ArrayList<Card> cardsPool = new ArrayList<>();
    private static boolean skip = false; 

    public static void loadCardPool(BoardManager boardManager, GameManager gameManager) throws IOException {
    	cardsPool = new ArrayList<>();
        if (!skip) { 
            cardsPool.clear();
            BufferedReader reader = new BufferedReader(new FileReader(CARDS_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int code = Integer.parseInt(parts[0].trim());
                int frequency = Integer.parseInt(parts[1].trim());
                String name = parts[2].trim();
                String description = parts[3].trim();

                for (int i = 0; i < frequency; i++) {
                    Card card;
                    switch (code) {
                        case 1:
                            card = new Ace(name, description, Suit.valueOf(parts[5].trim()), boardManager, gameManager);
                            break;
                        case 4:
                            card = new Four(name, description, Suit.valueOf(parts[5].trim()), boardManager, gameManager);
                            break;
                        case 5:
                            card = new Five(name, description, Suit.valueOf(parts[5].trim()), boardManager, gameManager);
                            break;
                        case 7:
                            card = new Seven(name, description, Suit.valueOf(parts[5].trim()), boardManager, gameManager);
                            break;
                        case 10:
                            card = new Ten(name, description, Suit.valueOf(parts[5].trim()), boardManager, gameManager);
                            break;
                        case 11:
                            card = new Jack(name, description, Suit.valueOf(parts[5].trim()), boardManager, gameManager);
                            break;
                        case 12:
                            card = new Queen(name, description, Suit.valueOf(parts[5].trim()), boardManager, gameManager);
                            break;
                        case 13:
                            card = new King(name, description, Suit.valueOf(parts[5].trim()), boardManager, gameManager);
                            break;
                        case 14:
                            card = new Burner(name, description, boardManager, gameManager);
                            break;
                        case 15:
                            card = new Saver(name, description, boardManager, gameManager);
                            break;
                        case 0:
                        default:
                            card = new Standard(name, description, Integer.parseInt(parts[4].trim()), Suit.valueOf(parts[5].trim()), boardManager, gameManager);
                            break;
                    }
                    cardsPool.add(card);
                }
            }
            reader.close();
        }
    }

    public static ArrayList<Card> drawCards() {
        if (cardsPool.isEmpty()) {
            return new ArrayList<>();
        }
        int cardsToDraw = Math.min(4, cardsPool.size());
        ArrayList<Card> drawnCards = new ArrayList<>();
        
        Random rand = new Random();
        for (int i = 0; i < cardsToDraw; i++) {
            int index = rand.nextInt(cardsPool.size());
            drawnCards.add(cardsPool.get(index));
            cardsPool.remove(index);
        }
        
        return drawnCards;
    }

    
}