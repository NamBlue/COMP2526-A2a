package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

/**
 * The Herbivore, represented as a yellow Cell.
 * 
 * @author Jia Qi Lee
 * @version 1.0
 */
public class Herbivore extends JPanel implements Inhabitant {
    private Cell cell;
    private int hunger;
    private boolean turnTaken;
    
    /**
     * Constructor for objects of type Herbivore.
     * 
     * @param location the cell to initialize this Herbivore on
     */
    public Herbivore(Cell location) {
        cell = location;
        hunger = 0;
        turnTaken = false;
    }
    
    /**
     * Initializes the Herbivore.
     */
    public void init() {
        setCell(cell);
    }
    
    /**
     * Puts the Herbivore on the specified cell.
     * @param cell the specified cell
     */
    private void setCell(Cell cell) {
        cell.add(this);
    }
    
    /**
     * Herbivore takes its turn.
     */
    public void takeTurn() {
        if (!turnTaken) {
            if (hunger == 6) {
                die();
            } else {
                move();
                hunger++; 
            }
            turnTaken = true;
        }
    }
    
    /**
     * Herbivore is ready to take the next turn.
     */
    public void resetTurn() {
        turnTaken = false;
    }
    
    /**
     * Herbivore moves if space is unoccupied or moves to a Plant and eats.
     */
    private void move() {
        /* Map of 2D array for reference in y,x index format
         * 00   01     02
         * 10   CELL   12
         * 20   21     22
         */       
        Cell[][] cells = cell.getAdjacentCells();
        boolean moved = false;
        while (!moved) {
            Point point = direction();
            int y1 = (int)point.getY();
            int x1 = (int)point.getX();
            if (cells[y1][x1] != null 
                    && (cells[y1][x1].getInhabitant() instanceof Plant 
                            || (cells[y1][x1].getInhabitant() == null))) {
                if (cells[y1][x1].getInhabitant() instanceof Plant) {
                    eat();
                }
                cell.removeInhabitant(this);
                cells[y1][x1].removeInhabitant(cells[y1][x1].getInhabitant());
                cells[y1][x1].setInhabitant(this);
                cell = cells[y1][x1];  
                moved = true;
            }
        }
    }
    
    /**
     * Herbivore decides which direction to go before moving.
     */
    private Point direction() {
        int direction;
        int y1 = 1;
        int x1 = 1;
        
        direction = RandomGenerator.nextNumber(79);
        if (direction < 10) { //moves north
            y1 = 0;
            x1 = 1;
        } else if (direction < 20) { //moves north east
            y1 = 0;
            x1 = 2;
        } else if (direction < 30) { //moves east
            y1 = 1;
            x1 = 2;
        } else if (direction < 40) { //moves south east
            y1 = 2;
            x1 = 2;
        } else if (direction < 50) { //moves south
            y1 = 2;
            x1 = 1;
        } else if (direction < 60) { //moves south west
            y1 = 2;
            x1 = 0;
        } else if (direction < 70) { //moves west
            y1 = 1;
            x1 = 0;
        } else if (direction < 80) { //moves north west
            y1 = 0;
            x1 = 0;
        }
        Point point = new Point(x1, y1);
        return point;
    }
    
    /**
     * Herbivore eats the Plant when on the same cell as the plant.
     * Resets its hunger back to 0(full stomach).
     */
    private void eat() {
        hunger = 0;
    }
    
    /**
     * Herbivore dies.
     */
    private void die() {
        cell.removeInhabitant(this);
    }
    
    
    /**
     * Draws the Herbivore.
     * @param draw device context for the Panel to draw on
     */
    public void paintComponent(Graphics draw) {
        draw.setColor(Color.yellow);
        draw.fillRect(0, 0, getWidth() - 1 ,getHeight() - 1);
    }
}
