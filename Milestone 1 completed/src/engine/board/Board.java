package engine.board;

import model.Colour;
import engine.GameManager;

import java.util.ArrayList;
import java.util.Random;

public class Board implements BoardManager {
    private final GameManager gameManager;
    private final ArrayList<Cell> track;
    private final ArrayList<SafeZone> safeZones;
    private int splitDistance;

    public Board(ArrayList<Colour> colourOrder, GameManager gameManager) {
        this.gameManager = gameManager;
        this.track = new ArrayList<>();
        this.safeZones = new ArrayList<>();
        this.splitDistance = 3;

        for (int i = 0; i < 100; i++) {
            track.add(new Cell(CellType.NORMAL));
        }

        track.get(0).setCellType(CellType.BASE);  
        track.get(23).setCellType(CellType.ENTRY); 
        track.get(25).setCellType(CellType.BASE);  
        track.get(48).setCellType(CellType.ENTRY); 
        track.get(50).setCellType(CellType.BASE);  
        track.get(73).setCellType(CellType.ENTRY); 
        track.get(75).setCellType(CellType.BASE);  
        track.get(98).setCellType(CellType.ENTRY); 

        for (int i = 0; i < 8; i++) {
            assignTrapCell();
        }

        for (Colour colour : colourOrder) {
            safeZones.add(new SafeZone(colour));
        }
    }

    private void assignTrapCell() {
        Random rand = new Random();
        boolean trapSet = false;
        while (!trapSet) {
            int pos = rand.nextInt(track.size());
            Cell cell = track.get(pos);
            if (cell.getCellType() == CellType.NORMAL && !cell.isTrap()) {
                cell.setTrap(true);
                trapSet = true;
            }
        }
    }

   
    public int getSplitDistance() {
        return splitDistance;
    }

    public void setSplitDistance(int splitDistance) {
        this.splitDistance = splitDistance;
    }

    public ArrayList<Cell> getTrack() {
        return track;
    }

    public ArrayList<SafeZone> getSafeZones() {
        return safeZones;
    }
}