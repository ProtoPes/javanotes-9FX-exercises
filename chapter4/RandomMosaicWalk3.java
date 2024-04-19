/**
 * This program opens a window full of black colored squares.  A "disturbance"
 * moves randomly around in the window, adding a little bit of green to each
 * square that it visits.  The program runs until the user closes the window.
 */
public class RandomMosaicWalk3 {

    final static int ROWS = 80;        // Number of rows in mosaic.
    final static int COLUMNS = 80;     // Number of columns in mosaic.
    final static int SQUARE_SIZE = 5; // Size of each square in mosaic.

    static int currentRow;    // Row currently containing the disturbance.
    static int currentColumn; // Column currently containing disturbance.

    /**
     * The main program creates the window, fills it with black colors,
     * and then moves the disturbances in a random walk around the window
     * as long as the window is open.
     */
    public static void main(String[] args) {
        Mosaic.setUse3DEffect(false);
        Mosaic.open( ROWS, COLUMNS, SQUARE_SIZE, SQUARE_SIZE );
        currentRow = ROWS / 2;   // start at center of window
        currentColumn = COLUMNS / 2;
        while (true) {
            addGreenColor(currentRow, currentColumn);
            randomMove();
            Mosaic.delay(1);
        }
    }  // end main

    /**
     * Add a little bit of green to square
     * Precondition:    The specified row and column are in the valid range
     * Postcondition:   The green component appended with 25 but it will
     *                  not exseed 255
     */
    static void addGreenColor(int rowNum, int colNum) {
        int currentGreen = Mosaic.getGreen(rowNum, colNum);
        if (currentGreen < 250) {
            currentGreen += 25;
        } else {
            currentGreen = 255;
        }
        Mosaic.setColor(rowNum, colNum, 0, currentGreen, 0);
    }


    /**
     * Move the disturbance.
     * Precondition:   The global variables currentRow and currentColumn
     *                 are within the legal range of row and column numbers.
     * Postcondition:  currentRow or currentColumn is changed to one of the
     *                 neighboring positions in the grid -- up, down, left, or
     *                 right from the current position.  If this moves the
     *                 position outside of the grid, then it is moved to the
     *                 opposite edge of the grid.
     */
    static void randomMove() {
        int directionNum; // Randomly set to 0, 1, 2, or 3 to choose direction.
        directionNum = (int)(4*Math.random());
        switch (directionNum) {
            case 0 -> {  // move up 
                currentRow--;
                if (currentRow < 0)
                    currentRow = ROWS - 1;
            }
            case 1 -> {  // move right
                currentColumn++;
                if (currentColumn >= COLUMNS)
                    currentColumn = 0;
            } 
            case 2 -> {  // move down
                currentRow ++;
                if (currentRow >= ROWS)
                    currentRow = 0;
            }
            case 3 -> {  // move left  
                currentColumn--;
                if (currentColumn < 0)
                    currentColumn = COLUMNS - 1;
            } 
        }
    }  // end randomMove

} // end class RandomMosaicWalk3
