package chapter6.task4;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
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
    private final double diamDot = 9;
    private GraphicsContext g;
    private Canvas canvas;
    private Button reRoll;
    private int firstDie;
    private int secondDie;
    private static int firstCoord = 10;
    private static int secondCoord = 55;
    private int frameNumber; // Count how many frames have been drawn
    private AnimationTimer animator;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        canvas = new Canvas(sizeCanvas, sizeCanvas);
        g = canvas.getGraphicsContext2D();
        g.setStroke(Color.BLACK);
        g.setLineWidth(1.5);
        g.setFill(Color.LIGHTBLUE);
        g.fillRect(0, 0, sizeCanvas, sizeCanvas);
        draw();
        reRoll = new Button("Reroll!");
        animator = new AnimationTimer() {
            long previousFrameTime;
            public void handle( long time ) {
                if (time - previousFrameTime > 0.99e9/30) {
                    draw();
                    previousFrameTime = time;
                    frameNumber++;
                }
            }
        };
        reRoll.setOnAction( e -> {
            reRoll.setDisable(true);
            animator.start();
            frameNumber = 0;
        });

        StackPane bottom = new StackPane(reRoll);
        bottom.setStyle("-fx-background-color: gray; -fx-padding:5px;" + 
                            " -fx-border-color:black; -fx-border-width: 2px 0 0 0");
        BorderPane root = new BorderPane(canvas);
        root.setStyle("-fx-border-color: blue; -fx-border-width: 2px"); //using CSS
        root.setBottom(bottom);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Roll the dice!");
        stage.show();
    }

    /**
     * Draws the screen with two dice each of which will have random values
     * betweent 1 and 6.
     */
    private void draw() {
        firstDie = (int) (Math.random() * 6 + 1);
        secondDie = (int) (Math.random() * 6 + 1);
        drawDie(firstDie, firstCoord, firstCoord);
        drawDie(secondDie, secondCoord, secondCoord);
        if (frameNumber > 15) {
            reRoll.setDisable(false);
            animator.stop();
        }
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
        if (val % 2 == 1) {
            drawMiddleDot(x, y);
        }
        if (val > 1) {
            drawUpperLeftDot(x, y);
            drawLowerRightDot(x, y);
        }
        if (val > 3) {
            drawUpperRightDot(x, y);
            drawLowerLeftDot(x, y);
        }
        if (val == 6) {
            drawUpperCenterDot(x, y);
            drawLowerCenterDot(x, y);
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
}
