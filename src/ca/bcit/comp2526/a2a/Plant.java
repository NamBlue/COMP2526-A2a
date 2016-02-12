package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * The Plant, represented as a green Cell.
 * 
 * @author Jia Qi Lee
 * @version 1.0
 */
public class Plant extends JPanel implements Inhabitant {
    private Cell cell;
        
    /**
     * Constructor for objects of type Plant.
     * 
     * @param location the cell to initialize this Plant on
     */
    public Plant(Cell location) {
        cell = location;
    }
    
    /**
     * Initializes the Plant.
     */
    public void init() {
        setCell(cell);
    }
    
    /**
     * Puts the plant on the specified cell.
     * @param cell the specified cell
     */
    public void setCell(Cell cell) {
        cell.add(this);
    }
    
    /**
     * Draws the Plant.
     * @param draw device context for the Panel to draw on
     */
    public void paintComponent(Graphics draw) {
        draw.setColor(Color.green);
        draw.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
}
