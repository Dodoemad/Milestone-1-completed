package model.card.wild;

import model.card.Card;
import engine.GameManager;
import engine.board.BoardManager;

public abstract class Wild extends Card {
    public Wild(String name, String description, BoardManager boardManager, GameManager gameManager) {
        super(name, description, boardManager, gameManager);
    }
}