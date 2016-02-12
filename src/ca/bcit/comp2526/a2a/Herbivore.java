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
    
    /**
     * Constructor for objects of type Herbivore.
     * 
     * @param location the cell to initialize this Herbivore on
     */
    public Herbivore(Cell location) {
        cell = location;
        hunger = 0;
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
        move();
        eat();
    }
    
    /**
     * Herbivore moves if space is unoccupied.
     */
    private void move() {
        /* Map of 2D array for reference in y,x index format
         * 00   01     02
         * 10   CELL   12
         * 20   21     22
         */
        Point point = direction();
        int y = (int)point.getY();
        int x = (int)point.getX();
        Cell[][] cells = cell.getAdjacentCells();
        
        if (cells[y][x] != null && (cells[y][x].getInhabitant() == null)) {
            cell.removeInhabitant(this);
            cells[y][x].removeInhabitant(cells[y][x].getInhabitant());
            cells[y][x].setInhabitant(this);
            cell = cells[y][x];
        }
    }
    
    /**
     * Herbivore decides which direction to go before moving.
     */
    private Point direction() {
        int direction;
        int y = 1;
        int x = 1;
        
        direction = RandomGenerator.nextNumber(79);
        System.out.println(direction);
        if (direction < 10) { //moves north
            y = 0;
            x = 1;
        } else if (direction < 20) { //moves north east
            y = 0;
            x = 2;
        } else if (direction < 30) { //moves east
            y = 1;
            x = 2;
        } else if (direction < 40) { //moves south east
            y = 2;
            x = 2;
        } else if (direction < 50) { //moves south
            y = 2;
            x = 1;
        } else if (direction < 60) { //moves south west
            y = 2;
            x = 0;
        } else if (direction < 70) { //moves west
            y = 1;
            x = 0;
        } else if (direction < 80) { //moves north west
            y = 0;
            x = 0;
        }
        Point point = new Point(x, y);
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
     * Returns the hunger of the Herbivore.
     * @return the hunger level of the Herbivore
     */
    public int hungry() {
        return hunger;
    }
    
    /**
     * Draws the Herbivore.
     * @param draw device context for the window to draw on
     */
    public void paintComponent(Graphics draw) {
        draw.setColor(Color.yellow);
        draw.fillRect(0, 0, getWidth() - 1 ,getHeight() - 1);
    }
}
