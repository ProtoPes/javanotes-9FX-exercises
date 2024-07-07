package chapter3;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *  This file can be used to create very simple animations.  Just fill in
 *  the definition of drawFrame with the code to draw one frame of the 
 *  animation, and possibly change a few of the values in the rest of
 *  the program as noted below.
 */
public class CyclicAndOscillatingAnimation extends Application {

    /**
     * Draws one frame of an animation. This subroutine should be called
     * about 60 times per second.  It is responsible for redrawing the
     * entire drawing area. The parameter g is used for drawing. The frameNumber 
     * starts at zero and increases by 1 each time this subroutine is called.  
     * The parameter elapsedSeconds gives the number of seconds since the animation
     * was started.  By using frameNumber and/or elapsedSeconds in the drawing
     * code, you can make a picture that changes over time.  That's an animation.
     * The parameters width and height give the size of the drawing area, in pixels.  
     */
    public void drawFrame(GraphicsContext g, int frameNumber, double elapsedSeconds, int width, int height) {

        g.setFill(Color.WHITE);
        g.fillRect(0, 0, width, height); // First, fill the entire image with a background color!
        final int rectSide = 40;
        final int windowSide = 600;
        int xCoord;

        for (int i = 0; i < 6; i++) {
            if (i < 3){
                xCoord = frameNumber * (i + 1) % windowSide;
            } else {
                xCoord = frameNumber * (i + 1) % (2*windowSide);
                if (xCoord > windowSide) {
                    xCoord = (2*windowSide) - xCoord;
                }
            }
            switch (i) {
                case 0 -> g.setFill(Color.RED);
                case 1 -> g.setFill(Color.GREEN);
                case 2 -> g.setFill(Color.BLUE);
                case 3 -> g.setFill(Color.CYAN);
                case 4 -> g.setFill(Color.PINK);
                case 5 -> g.setFill(Color.YELLOW);
                }
            g.fillRect(xCoord, i * rectSide, rectSide, rectSide);
            g.strokeLine(0, i * rectSide, windowSide + 40, i * rectSide);
            }
    }

    //------ Implementation details: DO NOT EXPECT TO UNDERSTAND THIS ------


    public void start(Stage stage) {
        int width = 640;   // The width of the image.  You can modify this value!
        int height = 240;  // The height of the image. You can modify this value!
        Canvas canvas = new Canvas(width,height);
        drawFrame(canvas.getGraphicsContext2D(), 0, 0, width, height);
        BorderPane root = new BorderPane(canvas);
        root.setStyle("-fx-border-width: 4px; -fx-border-color: #444");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Simple Animation"); // STRING APPEARS IN WINDOW TITLEBAR!
        stage.show();
        stage.setResizable(false);
        AnimationTimer anim = new AnimationTimer() {
            private int frameNum;
            private long startTime = -1;
            private long previousTime;
            public void handle(long now) {
                if (startTime < 0) {
                    startTime = previousTime = now;
                    drawFrame(canvas.getGraphicsContext2D(), 0, 0, width, height);
                }
                else if (now - previousTime > 0.95e9/60) {
                       // The test in the else-if is to make sure that drawFrame() is
                       // called about once every 1/60 second.  It is required since
                       // handle() can be called by the system more often than that.
                    frameNum++;
                    drawFrame(canvas.getGraphicsContext2D(), frameNum, (now-startTime)/1e9, width, height);
                    previousTime = now;
                }
            }
        };
        anim.start();
    } 

    public static void main(String[] args) {
        launch();
    }
    
} // end SimpleAnimationStarter
