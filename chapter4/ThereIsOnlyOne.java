/**
 * This program opens a window full of randomly colored squares.
 * Infinitely random chosen square is "consuming" one of the neighbors
 * which means they will be the same color as the chosen one.
 * The program runs until the user closes the window.
 */
public class ThereIsOnlyOne {

    final static int ROWS = 20;        // Number of rows in mosaic.
    final static int COLUMNS = 30;     // Number of columns in mosaic.
    final static int SQUARE_SIZE = 20; // Size of each square in mosaic.

    static int currentRow;
    static int currentColumn;

    /**
     * The main program creates the window, fills it with random colors,
     * and then choose a random square which will "consume" one of the neighbors
     * as long as the window is open.
     */
    public static void main(String[] args) {
        Mosaic.open( ROWS, COLUMNS, SQUARE_SIZE, SQUARE_SIZE );
        fillWithRandomColors();
        currentRow = ROWS / 2;   // start at center of window
        currentColumn = COLUMNS / 2;
        while (true) {
            chooseNmoveRandom();
            consumeNeighbor();
            Mosaic.delay(1);
        }
    }  // end main

    /**
     * Fills the window with randomly colored squares.
     * Precondition:   The mosaic window is open.
     * Postcondition:  Each square has been set to a random color. 
     */
    static void fillWithRandomColors() {
        for (int row=0; row < ROWS; row++) {
            for (int column=0; column < COLUMNS; column++) {
                changeToRandomColor(row, column);  
            }
        }
    }  // end fillWithRandomColors

    /**
     * Changes one square to a new randomly selected color.
     * Precondition:   The specified rowNum and colNum are in the valid range
     *                 of row and column numbers.
     * Postcondition:  The square in the specified row and column has
     *                 been set to a random color.
     * @param rowNum the row number of the square, counting rows down
     *      from 0 at the top
     * @param colNum the column number of the square, counting columns over
     *      from 0 at the left
     */
    static void changeToRandomColor(int rowNum, int colNum) {
        int red = (int)(256*Math.random());    // Choose random levels in range
        int green = (int)(256*Math.random());  //     0 to 255 for red, green, 
        int blue = (int)(256*Math.random());   //     and blue color components.
        Mosaic.setColor(rowNum,colNum,red,green,blue);  
    }  // end changeToRandomColor

    static void chooseNmoveRandom() {
        currentRow = (int)(ROWS*Math.random());
        currentColumn = (int)(COLUMNS*Math.random());
    }

    /**
     * Select the neighbour and fill it with the same color as the current chosen
     * square.
     * Postcondition:   One of the squares up, down, left, or right will be
     *                  filled with the same color as the chosen square.
     *                  If there is no adjacent square(there is a border)
     *                  it will consume the square on the opposite side 
     *                  of the mosaic
     */
    static void consumeNeighbor() {
        int directionNum; // Randomly set to 0, 1, 2, or 3 to choose direction.
        int targetRow = currentRow;
        int targetColumn = currentColumn;
        directionNum = (int)(4*Math.random());
        switch (directionNum) {
            case 0 -> {  // move up 
                targetRow--;
                if (targetRow < 0)
                    targetRow = ROWS - 1;
            }
            case 1 -> {  // move right
                targetColumn++;
                if (targetColumn >= COLUMNS)
                    targetColumn = 0;
            } 
            case 2 -> {  // move down
                targetRow ++;
                if (targetRow >= ROWS)
                    targetRow = 0;
            }
            case 3 -> {  // move left  
                targetColumn--;
                if (targetColumn < 0)
                    targetColumn = COLUMNS - 1;
            } 
        }
        Mosaic.setColor(targetRow, targetColumn, Mosaic.getColor(currentRow, currentColumn));  
    }  // end consumeNeighbor

} // end class ThereIsOnlyOne
