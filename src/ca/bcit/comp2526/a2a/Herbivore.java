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
        if (location == null) {
            throw new IllegalArgumentException(
                    "Parameter cannot be null");
        }
        cell = location;
        hunger = 1;
        turnTaken = false;
    }
    
    /**
     * Initializes the Herbivore.
     */
    public void init() {
        setCell(cell);
    }
    
    /**
     * Sets the Herbivore on the specified cell.
     * @param cell the cell to set this Herbivore on
     */
    public void setCell(Cell cell) {
        if (cell == null) {
            throw new IllegalArgumentException(
                    "Parameter cannot be null");
        }
        this.cell = cell;
        cell.setInhabitant(this);
        cell.add(this);
    }
    
    /**
     * Removes the Herbivore on the specified cell.
     * @param cell the cell to remove this Herbivore from
     */
    public void removeCell(Cell cell) {
        if (cell == null) {
            throw new IllegalArgumentException(
                    "Parameter cannot be null");
        }
        cell.removeInhabitant(this);
        cell.remove(this);
    }
    
    /**
     * Herbivore takes its turn.
     */
    public void takeTurn() {
        final int five = 5;
        
        if (!turnTaken) {
            if (hunger == five) {
                die();
            } else {
                hunger++; 
                move();
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
        Cell[][] cells = cell.getAdjacentCells();
        boolean moved = false;
        int stuck = 0;
        final int ten = 10;
        
        while (!moved) {
            Point point = direction();
            int y1 = (int)point.getY();
            int x1 = (int)point.getX();
                       
            if (cells[y1][x1] != null //cell exists 
                    && (cells[y1][x1].getInhabitant() == null //cell is empty
                            || cells[y1][x1].getInhabitant() instanceof Plant)) { //cell has a plant
                
                if (cells[y1][x1].getInhabitant() instanceof Plant) { 
                    eat(cells[y1][x1]);
                }
                removeCell(cell);
                setCell(cells[y1][x1]);
                moved = true;
              //if it is surrounded by impassable objects or cannot 
              //find a valid path after ten tries, give up
            } else if (stuck == ten) {  
                moved = true;
            }
            stuck++;
        }
    }
    
    /**
     * Herbivore decides which direction to go before moving.
     * @return the Point direction decided
     */
    private Point direction() {
        int direction;
        int y1 = 1;
        int x1 = 1;
        final int two = 2;
        final int ten = 10;
        final int twenty = 20;
        final int thirty = 30;
        final int forty = 40;
        final int fifty = 50;
        final int sixty = 60;
        final int seventy = 70;
        final int eighty = 80;
        
        /* Map of 2D array for reference in y,x index format
         * 00   01     02
         * 10   CELL   12
         * 20   21     22
         */
        direction = RandomGenerator.nextNumber(80);
        if (direction < ten) { //moves north
            y1 = 0;
            x1 = 1;
        } else if (direction < twenty) { //moves north east
            y1 = 0;
            x1 = two;
        } else if (direction < thirty) { //moves east
            y1 = 1;
            x1 = two;
        } else if (direction < forty) { //moves south east
            y1 = two;
            x1 = two;
        } else if (direction < fifty) { //moves south
            y1 = two;
            x1 = 1;
        } else if (direction < sixty) { //moves south west
            y1 = two;
            x1 = 0;
        } else if (direction < seventy) { //moves west
            y1 = 1;
            x1 = 0;
        } else if (direction < eighty) { //moves north west
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
    private void eat(Cell cell) {
        cell.getInhabitant().removeCell(cell);
        hunger = 0;
    }
    
    /**
     * Herbivore dies.
     */
    private void die() {
        removeCell(cell);
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
