package ca.bcit.comp2526.a2a;

/**
 * The World built with Cells, does not wrap around the edges.
 * It is flat but nothing falls off the edge either.
 * 
 * @author Jia Qi Lee
 * @version 1.0
 */
public class World {
    private final int rows;
    private final int cols;
    private Cell[][] map;
    private int time; //Stores the amount of time(turns) that has passed for this world
    private boolean bc; //True for year in BC, false for year in AD
    
    /**
     * Constructor for objects of type World.
     * 
     * @param rows the amount of rows of the World
     * @param cols the amount of columns of the World
     */
    public World(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException(
                    "Parameters cannot be negative or zero");
        }
        this.rows = rows;
        this.cols = cols;
        map = new Cell[rows][cols];
        time = 500;
        bc = true;
        System.out.println("Dawn of Life! This is year " 
                + time + (bc ? " BC" : " AD"));
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
        turnCount();
    }
    
    /**
     * Counts the number of time passed in the World.
     */
    private void turnCount() {
        final int increment = 100;
        
        if (bc) {
            time -= increment;
            if (time == 0) {
                bc = false;
            }
        } else if (!bc) {
            time += increment; 
        }
        System.out.println("This is This is year " 
                + time + (bc ? " BC" : " AD"));
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
        if (row < 0 || col < 0) {
            throw new IllegalArgumentException(
                    "Parameters cannot be negative");
        }
        return map[row][col];
    }
    
    /**
     * Returns all adjacent Cells in a 2D array, null = no Cell. 
     * Cell[1][1] is always null as it is its own Cell.
     * @param cell the Cell to get adjacent Cells from
     * @return the 2D Cell array of adjacent cells.
     */
    public Cell[][] getAdjacentCells(Cell cell) {
        if (cell == null) {
            throw new IllegalArgumentException(
                    "Parameter cannot be null");
        }
        final int three = 3;
        int x1 = (int)cell.getLocation().getX();
        int y1 = (int)cell.getLocation().getY();
        Cell[][] adjCell = new Cell[three][three];
        
        checkCornerCells(y1, x1, adjCell);
        checkSideCells(y1, x1, adjCell);
        checkOtherCells(y1, x1, adjCell);
        return adjCell;
    }
    
    /**
     * Support method for getAdjacentCells, checks and stores
     * the Cells adjacent to the corner Cells.
     * @param row the y position of the Cell to check
     * @param col the x position of the Cell to check
     * @param cell the 3x3 2D array cell to store with
     */
    private void checkCornerCells(int row, int col, Cell[][] cell) {
        final int two = 2;
        final int three = 3;
        
        if (row < 0 || col < 0 || cell.length != three || cell[two].length != three) {
            throw new IllegalArgumentException(
                    "Parameters cannot be negative and cell "
                    + "array passed must be size [3][3]");
        }
        
        /* Map of 2D array for reference in y,x index format
         * 00   01     02
         * 10   CELL   12
         * 20   21     22
         */
        if (row == 0 && col == 0) { //Top left
            cell[1][two] = getCellAt(row, col + 1);
            cell[two][two] = getCellAt(row + 1, col + 1);
            cell[two][1] = getCellAt(row + 1, col);
        } else if (row == 0 && col == cols - 1) { //Top right
            cell[1][0] = getCellAt(row, col - 1);
            cell[two][0] = getCellAt(row + 1, col - 1);
            cell[two][1] = getCellAt(row + 1, col);
        } else if (row == rows - 1 && col == 0) { //Bottom left
            cell[0][1] = getCellAt(row - 1, col);
            cell[0][two] = getCellAt(row - 1 , col + 1);
            cell[1][two] = getCellAt(row, col + 1);
        } else if (row == rows - 1 && col == cols - 1) { //Bottom right
            cell[0][1] = getCellAt(row - 1, col);
            cell[0][0] = getCellAt(row - 1 , col - 1);
            cell[1][0] = getCellAt(row, col - 1);
        }
    }
    
    /**
     * Support method for getAdjacentCells, checks and stores
     * the Cells adjacent to the corner Cells.
     * @param row the y position of the Cell to check
     * @param col the x position of the Cell to check
     * @param cell the 3x3 2D array cell to store with
     */
    private void checkSideCells(int row, int col, Cell[][] cell) {
        final int two = 2;
        final int three = 3;
        
        if (row < 0 || col < 0 
                || cell.length != three || cell[two].length != three) {
            
            throw new IllegalArgumentException(
                    "Parameters cannot be negative and cell "
                    + "array passed must be size [3][3]");
        }
        
        if (row == 0 && (col > 0 && col < cols - 1)) { //Top side
            cell[1][two] = getCellAt(row, col + 1);
            cell[two][two] = getCellAt(row + 1, col + 1);
            cell[two][1] = getCellAt(row + 1, col);
            cell[two][0] = getCellAt(row + 1, col - 1);
            cell[1][0] = getCellAt(row, col - 1);
        } else if ((row > 0 && row < rows - 1) && col == cols - 1) { //Right side
            cell[0][1] = getCellAt(row - 1, col);
            cell[two][1] = getCellAt(row + 1, col);
            cell[two][0] = getCellAt(row + 1, col - 1);
            cell[1][0] = getCellAt(row, col - 1);
            cell[0][0] = getCellAt(row - 1, col - 1);
        } else if (row == rows - 1 && (col > 0 && col < cols - 1)) { //Bottom side
            cell[0][1] = getCellAt(row - 1, col);
            cell[0][two] = getCellAt(row - 1, col + 1);
            cell[1][two] = getCellAt(row, col + 1);
            cell[1][0] = getCellAt(row, col - 1);
            cell[0][0] = getCellAt(row - 1, col - 1);
        } else if ((row > 0 && row < rows - 1) && col == 0) { //Left side
            cell[0][1] = getCellAt(row - 1, col);
            cell[0][two] = getCellAt(row - 1, col + 1);
            cell[1][two] = getCellAt(row, col + 1);
            cell[two][two] = getCellAt(row + 1, col + 1);
            cell[two][1] = getCellAt(row + 1, col);
        }
    }
    
    /**
     * Support method for getAdjacentCells, checks and stores
     * the Cells adjacent to the corner Cells.
     * @param row the y position of the Cell to check
     * @param col the x position of the Cell to check
     * @param cell the 3x3 2D array cell to store with
     */
    private void checkOtherCells(int row, int col, Cell[][] cell) {
        final int two = 2;
        final int three = 3;
        
        if (row < 0 || col < 0 || cell.length != three || cell[two].length != three) {
            throw new IllegalArgumentException(
                    "Parameters cannot be negative and cell "
                    + "array passed must be of size [3][3]");
        }
        
        if ((row > 0 && row < rows - 1) && (col > 0 && col < cols - 1)) {
            cell[0][1] = getCellAt(row - 1, col);
            cell[0][two] = getCellAt(row - 1, col + 1);
            cell[1][two] = getCellAt(row, col + 1);
            cell[two][two] = getCellAt(row + 1, col + 1);
            cell[two][1] = getCellAt(row + 1, col);
            cell[two][0] = getCellAt(row + 1, col - 1);
            cell[1][0] = getCellAt(row, col - 1);
            cell[0][0] = getCellAt(row - 1, col - 1);
        }
    }
}
