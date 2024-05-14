package chapter6.task5;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *  This program will draw a checker board
 */
public class CheckerboardSelector extends Application {

    final int sideLength = 400;
    final int rectSide = sideLength / 8;
    double lineWidth = 5.0;
    int selectedColumn = -1; // Represents currently selected column, -1 means no selection
    int selectedRow = -1; // Represents currently selected row, -1 means no selection
    GraphicsContext g;

    /**
     * Draws a CheckerBoard with no squares selected.
     */
    private void drawCheckerBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                drawSquare(i, j);
            }
        }
    }

    /**
     * Draws one square, it will detect the necessary color.
     * @param column number of columns square has.
     * @param row number of rows square has.
     */
    private void drawSquare(int column, int row) {
        if ( (column % 2) == (row % 2) ) {
            g.setFill(Color.RED);
        } else {
            g.setFill(Color.BLACK);
        }
        g.fillRect(column * rectSide, row * rectSide, rectSide, rectSide);
    }

    /**
     * When the user clicks on the canvas, calculate which row and Column
     * was it and change the selected square accordingly.
     */
    private void mouseClicked(MouseEvent evt) {
        int clickedColumn = (int) (evt.getX() / rectSide);
        int clickedRow = (int) (evt.getY() / rectSide);
        // If any square is selected, deselect it.
        if ( selectedColumn != -1 && selectedRow != -1 ) {
            drawSquare(selectedColumn, selectedRow);
        }
        // If clicked on a new square, select it.
        if ( selectedColumn != clickedColumn || selectedRow != clickedRow ) {
            selectSquare(clickedColumn, clickedRow);
            selectedColumn = clickedColumn;
            selectedRow = clickedRow;
        } else {
            selectedColumn = -1;
            selectedRow = -1;
        }
    }

    /**
     * Draw a border for selected square.
     * @param column number of columns square has.
     * @param row number of rows square has.
     */
    private void selectSquare(double column, int row) {
        double offset = lineWidth / 2;
        g.strokeRect(column * rectSide + offset, row * rectSide + offset, rectSide - lineWidth, rectSide - lineWidth);
    }

    public void start(Stage stage) {
        Canvas canvas = new Canvas(sideLength, sideLength);
        g = canvas.getGraphicsContext2D();
        g.setStroke(Color.CYAN);
        g.setLineWidth(lineWidth);
        drawCheckerBoard();
        canvas.setOnMouseClicked(this::mouseClicked);
        BorderPane root = new BorderPane(canvas);
        root.setStyle("-fx-border-width: 4px; -fx-border-color: #444");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Select square on the checkerboard");
        stage.show();
        stage.setResizable(false);
    } 

    public static void main(String[] args) {
        launch();
    }

}
