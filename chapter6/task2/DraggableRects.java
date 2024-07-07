package chapter6.task2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This program will show two rectangles on the
 * white background. User can drag them with a mouse.
 * ESC will return the rectangles on their default
 * positions.
 */
public class DraggableRects extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private int selectedFigure = 0;
    private double redX, redY, blueX, blueY;
    private double prevX, prevY;
    private final int figureWidth = 30;
    private final int figureHeight = 30;
    private final int height = 600;
    private final int width = 600;
    private Canvas canvas;
    private GraphicsContext g;

    @Override
    public void start(Stage stage) throws Exception {
        canvas = new Canvas(width, height);
        g = canvas.getGraphicsContext2D();

        drawInitialScreen();
        BorderPane root = new BorderPane(canvas);
        root.setStyle("-fx-border-color: black; -fx-border-width: 2px"); //using CSS
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Drag figures");
        scene.setOnMousePressed(this::mousePressed);
        scene.setOnMouseDragged(this::mouseDragged);
        scene.setOnMouseReleased(this::mouseReleased);
        scene.setOnKeyPressed(this::keyPressed);
        stage.show();
    }


    /**
     * Draws the white background and one red and one blue
     * rectangle. Positions of the rectangles will be taken
     * from instance variables.
     */
    public void drawScreen() {
        g.setFill(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setFill(Color.RED);
        g.fillRect(redX, redY, figureWidth, figureHeight); 
        g.setFill(Color.BLUE);
        g.fillRect(blueX, blueY, figureWidth, figureHeight); 
    }

    /**
     * Draw the starting screen: rectangles on the middle
     */
    private void drawInitialScreen() {
        redX = width / 4;
        redY = height / 2;
        blueX = redX * 3;
        blueY = redY;
        drawScreen();
    }

    /**
     * Determine if the user pressed on the figure
     */
    private void mousePressed(MouseEvent evt) {
        if (selectedFigure != 0) {
            return;
        }
        double curX = evt.getX();
        double curY = evt.getY();

        if ( curX >= redX && curX <= redX + figureWidth && curY >= redY && curY <= redY + figureHeight ) {
            selectedFigure = 1;
        }
        if ( curX >= blueX && curX <= blueX + figureWidth && curY >= blueY && curY <= blueY + figureHeight ) {
            selectedFigure = 2;
        }
        prevX = curX;
        prevY = curY;
    }

    /**
     * Draw new rectangles positions on the screen while
     * dragging them.
     */
    private void mouseDragged(MouseEvent evt) {
        double curX = evt.getX();
        double curY = evt.getY();
        if (selectedFigure == 1) {
            redX = redX - (prevX - curX);
            redY = redY - (prevY - curY);
        }
        if (selectedFigure == 2) {
            blueX = blueX - (prevX - curX);
            blueY = blueY - (prevY - curY);
        }
        prevX = curX;
        prevY = curY;
        drawScreen();
    }

    /**
     * Just set the selectedFigure variable to 0 to
     * detect that no figure is dragged at the moment.
     */
    private void mouseReleased(MouseEvent evt) {
        selectedFigure = 0;
    }

    /**
     * Redraw the initial screen if ESC key is pressed.
     */
    private void keyPressed(KeyEvent evt) {
        if (evt.getCode() == KeyCode.ESCAPE) {
            drawInitialScreen();
        }
    }
}
