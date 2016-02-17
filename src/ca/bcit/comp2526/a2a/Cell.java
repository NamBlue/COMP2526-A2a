package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import javax.swing.JPanel;

/**
 * The Cell. Represents a square space within a World. 
 * Can hold objects thats exists in the World.
 * 
 * @author Jia Qi Lee
 * @version 1.0
 */
public class Cell extends JPanel {
    private final int row;
    private final int col;
    private final World world;
    private Inhabitant inhabitant;
    private Cell[][] cell;
    
    /**
     * Constructor for objects of type Cell.
     * 
     * @param row the row position of the Cell
     * @param col the column position of the Cell
     */
    public Cell(World world, int row, int col) {
        if (row < 0 || col < 0 || world == null) {
            throw new IllegalArgumentException(
                    "Parameters cannot be negative");
        }
        final int three = 3;
        
        this.world = world;
        this.row = row;
        this.col = col;
        cell = new Cell[three][three];
        setLayout(new GridLayout(1, 1));
    }
    
    /**
     * Initializes the Cell and places the inhabitant on the Cell randomly 
     * using the RandomGenerator class. Stores adjacent Cells from World.
     */
    public void init() {
        int seed = RandomGenerator.nextNumber(100);
        final int ten = 10;
        final int forty = 40;

        if (seed < ten) {
            inhabitant = new Herbivore(this);
            inhabitant.init();
        } else if (seed < forty) {
            inhabitant = new Plant(this);
            inhabitant.init();
        } else {
            inhabitant = null;
        }
        cell = world.getAdjacentCells(this);
    }
    
    /**
     * Advances time within the Cell by one turn.
     */
    public void takeTurn() {
        if (inhabitant instanceof Herbivore) {
            ((Herbivore)inhabitant).takeTurn();
        }
    }
    
    /**
     * Draws the cell.
     * @param draw device context for the Panel to draw on
     */
    public void paintComponent(Graphics draw) {
        draw.setColor(Color.lightGray);
        draw.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
    
    /**
     * Sets the Inhabitant to this Cell.
     * @param object the Inhabitant to set
     */
    public void setInhabitant(Inhabitant object) {
        inhabitant = object;
        add((JPanel)object);
        repaint();
    }
    
    /**
     * Removes the Inhabitant of this Cell.
     * @param object the Inhabitant to remove
     */
    public void removeInhabitant(Inhabitant object) {
        if (inhabitant != null) {
            inhabitant = null;
            remove((JPanel)object);
        }
    }
    
    /**
     * Returns the current Inhabitant of the Cell.
     * @return the Inhabitant of the Cell
     */
    public Inhabitant getInhabitant() {
        return inhabitant;
    }
    
    /**
     * Returns the location of the cell as a Pointer object.
     * @return the Point object of the location of the cell.
     */
    public Point getLocation() {
        Point location = new Point(col, row);
        return (location);
    }
    
    /**
     * Returns all adjacent cells in a 2D array, null = no Cell. 
     * Cell[1][1] is always null as it is its own Cell.
     * @return the 2D Cell array of adjacent cells.
     */
    public Cell[][] getAdjacentCells() {
        return cell;
    }
    
    /**
     * Resets the Inhabitants' turn so that they 
     * are ready to take the next turn.
     */
    public void resetTurn() {
        if (inhabitant instanceof Herbivore) {
            ((Herbivore)inhabitant).resetTurn();
        }
    }
}