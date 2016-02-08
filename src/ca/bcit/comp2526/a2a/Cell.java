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
    private Inhabitants object;
    
    /**
     * Constructor for objects of type Cell.
     * 
     * @param row the row position of the Cell
     * @param col the column position of the Cell
     */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        setLayout(new GridLayout(1, 1));
    }
    
    public void init() {
        int seed = RandomGenerator.nextNumber(100);
        if (seed < 10) {
            object = new Herbivore(this);
            object.init();
        } else if (seed < 40) {
            object = new Plant(this);
            object.init();
        } else {
            object = null;
        }
    }
    
    public void paintComponent(Graphics draw) {
        draw.setColor(Color.lightGray);
        draw.fillRect(0, 0, getWidth() -1, getHeight() -1);
    }
    
    public Point getLocation() {
        Point location = new Point(row, col);
        return (location);
    }
    
    public Cell getAdjacentCells() {
        return new Cell(0,0);
    }
}