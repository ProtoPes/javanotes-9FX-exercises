package chapter6.task9;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This program allows user to draw a polygon with mouse clicks
 * Whenever the user clicks nearby the first click the program
 * will draw complete polygon. Next click will reset the Canvas
 * and will be the starting point of a new polygon.
 */
public class DrawPolygon extends Application{

    private Canvas canvas;
    private double[] xCoords;
    private double[] yCoords;
    private int counter;
    private final int width = 400;
    private final int height = 400;
    private final int deviation = 5; // How close user need to click to count it as the same spot

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        canvas = new Canvas(width, height);
        reset();
        canvas.setOnMouseClicked(this::onClick);

        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Draw polygons for free!");
        stage.show();

    }

    /**
     * Reset the canvas and all variables to 0.
     */
    private void reset() {
        counter = 0;
        xCoords = new double[50];
        yCoords = new double[50];
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.WHITE);
        g.fillRect(0, 0, width, height);
    }

    /**
     * Draw polygon if it's completed or draw a line between the last
     * click and current click coordinates if polygon is not finished.
     * Reset the canvas and data if polygon was finished on the last
     * click.
     */
    private void onClick(MouseEvent evt) {
        double x = evt.getX();
        double y = evt.getY();
        if (counter == -1) {
            reset();
        }
        if (counter == 0 || isLastClickNotEqualsfirst(x, y)) {
            if (counter > 0) {
                connectPoints(x, y);
            }
            xCoords[counter] = x;
            yCoords[counter] = y;
            counter++;
        } else {
            drawPolygon();
            counter = -1;
        }
    }

    /**
     * Utility method to connect two points on a canvas with black line
     */
    private void connectPoints(double x, double y) {

        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setStroke(Color.BLACK);
        g.strokeLine(xCoords[counter - 1], yCoords[counter - 1], x, y);

    }

    /**
     * Draw red polygon with thin black border. Coordinates will be taken
     * from global variables xCoords, yCoords, counter.
     */
    private void drawPolygon() {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.RED);
        g.fillPolygon(xCoords, yCoords, counter);
        g.setStroke(Color.BLACK);
        g.strokePolygon(xCoords, yCoords, counter);
    }

    /**
     * Check if the coordinates are not close to the last click.
     * @param x width of target coordinate
     * @param y height of target coordinate
     */
    private boolean isLastClickNotEqualsfirst(double x, double y) {
        boolean result;

        double xOffset = Math.abs(xCoords[0] - x);
        double yOffset = Math.abs(yCoords[0] - y);

        result = xOffset < deviation && yOffset < deviation;


        return !result;
    }
}
