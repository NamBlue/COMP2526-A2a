package ca.bcit.comp2526.a2a;

/**
 * The World built with Cells, no wrap around on the world,
 * flat but nothing falls off the edge
 * 
 * @author Jia Qi Lee
 * @version 1.0
 */
public class World {
    private final int rows;
    private final int cols;
    private Cell[][] map;
    private int time;
    
    /**
     * Constructor for objects of type World.
     * 
     * @param rows the amount of rows of the World
     * @param cols the amount of columns of the World
     */
    public World(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        map = new Cell[rows][cols];
        time = 0;
        System.out.println("Dawn of Life! This is year " + time + " AD");
    }
    
    /**
     * Places the Cells on the World then initializes them.
     */
    public void init() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                map[row][col] = new Cell(this, row, col);
            }
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                map[row][col].init();
            }
        }        
    }
    
    /**
     * Advances time within the World by one turn.
     */
    public void takeTurn() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                map[row][col].takeTurn();
            }
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                map[row][col].resetTurn();
            }
        }
        time += 100;
        System.out.println("This is year " + time + " AD");
    }
    
    /**
     * Returns the number of rows in this world.
     * @return number of rows in this world.
     */
    public int getRowCount() {
        return rows;
    }
    
    /**
     * Returns the number of columns in this world.
     * @return number of columns in this world.
     */
    public int getColumnCount() {
        return cols;
    }
    
    /**
     * Returns the Cell with the location specified.
     * @param row the row of the Cell
     * @param col the column of the Cell
     * @return the Cell at that location.
     */
    public Cell getCellAt(int row, int col) {
        return map[row][col];
    }
}
