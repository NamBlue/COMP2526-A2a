package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Graphics;

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
    public void setCell(Cell cell) {
        cell.add(this);
    }
    
    /**
     * Herbivore eats the Plant when on the same cell as the plant.
     * Resets its hunger back to 0(full stomach).
     */
    public void move() {
        System.out.println("Moo!");
    }
    
    /**
     * Herbivore eats the Plant when on the same cell as the plant.
     * Resets its hunger back to 0(full stomach).
     */
    public void eat() {
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
