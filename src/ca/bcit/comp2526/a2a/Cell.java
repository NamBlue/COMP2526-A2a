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
    private final Cell[][] cell = new Cell[3][3];
    
    /**
     * Constructor for objects of type Cell.
     * 
     * @param row the row position of the Cell
     * @param col the column position of the Cell
     */
    public Cell(World world, int row, int col) {
        this.world = world;
        this.row = row;
        this.col = col;
        setLayout(new GridLayout(1, 1));
    }
    
    /**
     * Initializes the Cell and places the inhabitant on the Cell
     * randomly using the RandomGenerator class. Checks and stores adjacent Cells.
     */
    public void init() {
        int seed = RandomGenerator.nextNumber(100);
        int rows = world.getRowCount() - 1;
        int cols = world.getColumnCount() - 1;

        if (seed < 10) {
            inhabitant = new Herbivore(this);
            inhabitant.init();
        } else if (seed < 40) {
            inhabitant = new Plant(this);
            inhabitant.init();
        } else {
            inhabitant = null;
        }
        
        checkCornerCells(rows, cols);
        checkSideCells(rows, cols);
        checkOtherCells(rows, cols);
    }
    
    /**
     * Advances time within the Cell by one turn.
     */
    public void takeTurn() {
        if (inhabitant instanceof Herbivore) {
            ((Herbivore)inhabitant).move();
        }
    }
    
    /**
     * Draws the cell.
     * @param draw device context for the window to draw on
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
        Point location = new Point(row, col);
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
     * Support method for init, checks and stores
     * the Cells adjacent to the corner Cells.
     * @param rows the maximum rows in this world
     * @param cols the maximum columns in this world
     */
    private void checkCornerCells(int rows, int cols) {
        /* Map of 2D array for reference in y,x index format
         * 00   01     02
         * 10   CELL   12
         * 20   21     22
         */
        if (row == 0 && col == 0) { //Top left
            cell[1][2] = world.getCellAt(row, col + 1);
            cell[2][2] = world.getCellAt(row + 1, col + 1);
            cell[2][1] = world.getCellAt(row + 1, col);
        } else if (row == 0 && col == cols) { //Top right
            cell[1][0] = world.getCellAt(row, col - 1);
            cell[2][0] = world.getCellAt(row + 1, col - 1);
            cell[2][1] = world.getCellAt(row + 1, col);
        } else if (row == rows && col == 0) { //Bottom left
            cell[0][1] = world.getCellAt(row - 1, col);
            cell[0][2] = world.getCellAt(row - 1 , col + 1);
            cell[1][2] = world.getCellAt(row, col + 1);
        } else if (row == rows && col == cols) { //Bottom right
            cell[0][1] = world.getCellAt(row - 1, col);
            cell[0][0] = world.getCellAt(row - 1 , col - 1);
            cell[1][0] = world.getCellAt(row, col - 1);
        }
    }
    
    /**
     * Support method for init, checks and stores
     * the Cells adjacent to the side Cells.
     * @param rows the maximum rows in this world
     * @param cols the maximum columns in this world
     */
    private void checkSideCells(int rows, int cols) {
        if (row == 0 && (col > 0 && col < cols)) { //Top side
            cell[1][2] = world.getCellAt(row, col + 1);
            cell[2][2] = world.getCellAt(row + 1, col + 1);
            cell[2][1] = world.getCellAt(row + 1, col);
            cell[2][0] = world.getCellAt(row + 1, col - 1);
            cell[1][0] = world.getCellAt(row, col - 1);
        } else if ((row > 0 && row < rows) && col == cols) { //Right side
            cell[0][1] = world.getCellAt(row - 1, col);
            cell[2][1] = world.getCellAt(row + 1, col);
            cell[2][0] = world.getCellAt(row + 1, col - 1);
            cell[1][0] = world.getCellAt(row, col - 1);
            cell[0][0] = world.getCellAt(row - 1, col - 1);
        } else if (row == rows && (col > 0 && col < cols)) { //Bottom side
            cell[0][1] = world.getCellAt(row - 1, col);
            cell[0][2] = world.getCellAt(row - 1, col + 1);
            cell[1][2] = world.getCellAt(row, col + 1);
            cell[1][0] = world.getCellAt(row, col - 1);
            cell[0][0] = world.getCellAt(row - 1, col - 1);
        } else if ((row > 0 && row < rows) && col == 0) { //Left side
            cell[0][1] = world.getCellAt(row - 1, col);
            cell[0][2] = world.getCellAt(row - 1, col + 1);
            cell[1][2] = world.getCellAt(row, col + 1);
            cell[2][2] = world.getCellAt(row + 1, col + 1);
            cell[2][1] = world.getCellAt(row + 1, col);
        }
    }
    
    /**
     * Support method for init, checks and stores
     * the Cells adjacent to all other Cells not in the corner or sides.
     * @param rows the maximum rows in this world
     * @param cols the maximum columns in this world
     */
    private void checkOtherCells(int rows, int cols) {
        if ((row > 0 && row < rows) && (col > 0 && col < cols)) {
            cell[0][1] = world.getCellAt(row - 1, col);
            cell[0][2] = world.getCellAt(row - 1, col + 1);
            cell[1][2] = world.getCellAt(row, col + 1);
            cell[2][2] = world.getCellAt(row + 1, col + 1);
            cell[2][1] = world.getCellAt(row + 1, col);
            cell[2][0] = world.getCellAt(row + 1, col - 1);
            cell[1][0] = world.getCellAt(row, col - 1);
            cell[0][0] = world.getCellAt(row - 1, col - 1);
        }
    }
}