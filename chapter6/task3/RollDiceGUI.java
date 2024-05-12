package chapter6.task3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This program will show a pair of dice,
 * user can roll the dice with mouse click on
 * the screen.
 */
public class RollDiceGUI extends Application {

    private final int sizeCanvas = 100;
    private final double sizeDie = 35;
    private final double diamDot = 10;
    private GraphicsContext g;
    private Canvas canvas;
    private int firstDie;
    private int secondDie;
    private static int firstCoord = 10;
    private static int secondCoord = 55;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        canvas = new Canvas(sizeCanvas, sizeCanvas);
        g = canvas.getGraphicsContext2D();
        g.setStroke(Color.BLACK);
        g.setLineWidth(1.5);
        canvas.setOnMouseClicked(this::mouseClicked);
        draw();
        BorderPane root = new BorderPane(canvas);
        root.setStyle("-fx-border-color: blue; -fx-border-width: 2px"); //using CSS
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Draws the screen with two dice each of which will have random values
     * betweent 1 and 6.
     */
    private void draw() {
        g.setFill(Color.LIGHTBLUE);
        g.fillRect(0, 0, sizeCanvas, sizeCanvas);
        firstDie = (int) (Math.random() * 6 + 1);
        secondDie = (int) (Math.random() * 6 + 1);
        drawDie(firstDie, firstCoord, firstCoord);
        drawDie(secondDie, secondCoord, secondCoord);
    }

    /**
     * Draw a die.
     * @param val represents number on the die.
     * @param x width of right upper corner of the die.
     * @param y height of right upper corner of the die.
     */
    private void drawDie(int val, double x, double y) {
        g.setFill(Color.WHITE);
        g.fillRect(x, y, sizeDie, sizeDie);
        g.strokeRect(x, y, sizeDie, sizeDie);
        g.setFill(Color.BLACK);
        switch (val) {
            case 1 -> {
                drawMiddleDot(x, y);
            }
            case 2 -> {
                drawUpperLeftDot(x, y);
                drawLowerRightDot(x, y);
            }
            case 3 -> {
                drawUpperLeftDot(x, y);
                drawMiddleDot(x, y);
                drawLowerRightDot(x, y);
            }
            case 4 -> {
                drawUpperLeftDot(x, y);
                drawUpperRightDot(x, y);
                drawLowerLeftDot(x, y);
                drawLowerRightDot(x, y);
            }
            case 5 -> {
                drawUpperLeftDot(x, y);
                drawUpperRightDot(x, y);
                drawLowerLeftDot(x, y);
                drawLowerRightDot(x, y);
                drawMiddleDot(x, y);
            }
            default -> {
                drawUpperLeftDot(x, y);
                drawUpperRightDot(x, y);
                drawLowerLeftDot(x, y);
                drawLowerRightDot(x, y);
                drawUpperCenterDot(x, y);
                drawLowerCenterDot(x, y);
            }
        }
    }

    /**
     * Draw a middle dot on the die
     * @param x width of upper left corner of a die
     * @param y height of upper left corner of a die
     */
    private void drawMiddleDot(double x, double y) {
        drawDot( x + sizeDie / 2, y + sizeDie / 2 );
    }

    /**
     * Draw a upper left dot on the die
     * @param x width of upper left corner of a die
     * @param y height of upper left corner of a die
     */
    private void drawUpperLeftDot(double x, double y) {
        drawDot( x + sizeDie / 5, y + sizeDie / 5);
    }

    /**
     * Draw a upper center dot on the die
     * @param x width of upper left corner of a die
     * @param y height of upper left corner of a die
     */
    private void drawUpperRightDot(double x, double y) {
        drawDot( x + sizeDie / 5 * 4, y + sizeDie / 5);
    }

    /**
     * Draw a lower left dot on the die
     * @param x width of upper left corner of a die
     * @param y height of upper left corner of a die
     */
    private void drawLowerLeftDot(double x, double y) {
        drawDot( x + sizeDie / 5, y + sizeDie / 5 * 4);
    }

    /**
     * Draw a lower right dot on the die
     * @param x width of upper left corner of a die
     * @param y height of upper left corner of a die
     */
    private void drawLowerRightDot(double x, double y) {
        drawDot( x + sizeDie / 5 * 4, y + sizeDie / 5 * 4);
    }

    /**
     * Draw a upper center dot on the die
     * @param x width of upper left corner of a die
     * @param y height of upper left corner of a die
     */
    private void drawUpperCenterDot(double x, double y) {
        drawDot( x + sizeDie / 2, y + sizeDie / 5);
    }

    /**
     * Draw a lower center dot on the die
     * @param x width of upper left corner of a die
     * @param y height of upper left corner of a die
     */
    private void drawLowerCenterDot(double x, double y) {
        drawDot( x + sizeDie / 2, y + sizeDie / 5 * 4);
    }

    /**
     * Draw a dot.
     * @param x width of upper left corner of a dot.
     * @param y height of upper left corner of a dot.
     */
    private void drawDot(double x, double y) {
        g.fillOval( x - diamDot / 2, y - diamDot / 2 , diamDot, diamDot);
    }

    /**
     * React on mouse click with redrawing the screen with new values of dice.
     */
    private void mouseClicked(MouseEvent evt) {
        if (evt.getButton() == MouseButton.PRIMARY) {
            draw();
        }
    }
}
