package exercises.chapter3;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *  This program will draw a checker board
 */
public class DrawCheckerBoard extends Application {

    /**
     * Draws a picture.  The parameters width and height give the size 
     * of the drawing area, in pixels.  
     *
     * Outline:
     *
     * 
     * constant rectSide = 50
     * for (i = 0;i < 8;i++)
     *      for(j = 0;j < 8;j++)
     *          if ( ((i % 2) == 0) == ((j % 2) == 0) )
     *              set colour to red
     *          else
     *              set colour to black
     *          fillRect(i * 50, j * 50, 50, 50)
     *
     *
     * Results:
     *      The same as the solution except comparison is
     *      simpler: if ( (i % 2) == (j % 2) )
     *      because the lefrover will be either 0 or 1
     *      dummy me
     */
    public void drawPicture(GraphicsContext g, int width, int height) {

        final int rectSide = 50;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ( ((i % 2) == 0) == ((j % 2) == 0) ) {
                    g.setFill(Color.RED);
                } else {
                    g.setFill(Color.BLACK);
                }
                g.fillRect(i * rectSide, j * rectSide, rectSide, rectSide);
            }
        }

    } // end drawPicture()

    //------ Implementation details: DO NOT EXPECT TO UNDERSTAND THIS ------


    public void start(Stage stage) {
        int width = 400;   // The width of the image.  You can modify this value!
        int height = 400;  // The height of the image. You can modify this value!
        Canvas canvas = new Canvas(width,height);
        drawPicture(canvas.getGraphicsContext2D(), width, height);
        BorderPane root = new BorderPane(canvas);
        root.setStyle("-fx-border-width: 4px; -fx-border-color: #444");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Simple Graphics"); // STRING APPEARS IN WINDOW TITLEBAR!
        stage.show();
        stage.setResizable(false);
    } 

    public static void main(String[] args) {
        launch();
    }

} // end SimpleGraphicsStarter
