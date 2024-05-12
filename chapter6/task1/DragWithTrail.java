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
 * This program will draw a blue oval if left mouse Button
 * was pressed or a red rectangle if the shift key is held
 * down. If the user will drag the mouse when the left key
 * is pressed, the program will draw a "trail" of corresponding
 * figures. The screen is cleared after the right mouse click.
 */
public class DragWithTrail extends Application {
    public static void main(String[] args) {
        launch();
    }

    private double prevX, prevY; // The previous location of the figure
    private boolean dragging;
    private Canvas canvas;
    private GraphicsContext g;

    @Override
    public void start(Stage stage) {
        canvas = new Canvas(600, 600);
        g = canvas.getGraphicsContext2D();
        g.setStroke(Color.BLACK);
        clearAndDrawCanvas();
        canvas.setOnMousePressed(this::mousePressed);
        canvas.setOnMouseReleased(this::mouseReleased);
        canvas.setOnMouseDragged(this::mouseDragged);

        BorderPane root = new BorderPane(canvas);
        root.setStyle("-fx-border-color: black; -fx-border-width: 2px"); //using CSS
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Draw figures with trail");
        stage.show();
    }

    /**
     * Fills the canvas with white.
     * Called when the canvas is created and when the
     * user clicks right mouse button.
     */
    public void clearAndDrawCanvas() {
        int width = (int)canvas.getWidth();
        int height = (int)canvas.getHeight();

        g.setFill(Color.WHITE);
        g.fillRect(0,0,width,height);
    }

    /**
     * Called when the user press a mouse button.
     * If it is the left mouse: draw a red Rectangle
     * when shift is not held down, otherwise draw
     * a blue oval.
     * If it is the right mouse button: clear canvas.
     */
    public void mousePressed(MouseEvent evt) {
        if (dragging == true) {
            return;
        }
        if (evt.getButton() == MouseButton.PRIMARY) {
            prevX = evt.getX();
            prevY = evt.getY();
            drawFigure(evt.isShiftDown(), prevX, prevY);
            dragging = true;
        } else {
            clearAndDrawCanvas();
        }
    }

    /**
     * Called whenever the user releases the mouse button. Just sets
     * dragging to false.
     */
    public void mouseReleased(MouseEvent evt) {
        dragging = false;
    }

    /**
     * Called whenever the user dragging the mouse.
     * If the user hold a left mouse button, it will
     * draw figure depending on the `shift` pressed or not
     */
    public void mouseDragged(MouseEvent evt) {

        if (dragging == false) {
            return;
        }

        double x = evt.getX();
        double y = evt.getY();

        if (x < 3)
            x = 3;
        if (x > canvas.getWidth() - 4)
            x = (int)canvas.getWidth() - 4;

        if (y < 3)
            y = 3;
        if (y > canvas.getHeight() - 4)
            y = canvas.getHeight() - 4;

        if ( (Math.abs(prevX - x) > 5) || (Math.abs(prevY - y) > 5) ) {
            drawFigure(evt.isShiftDown(), x, y);
            prevX = x;
            prevY = y;
        }

    }

    /**
     * Utility method to draw oval or rectangle.
     * @param isOval Boolean if true: blue oval, otherwise red rectangle
     * @param x coordinate x of figure
     * @param y coordinate y of figure
     */
    private void drawFigure(boolean isOval, double x, double y) {
        if ( isOval ) {
            g.setFill( Color.BLUE );
            g.fillOval( x - 30, y - 15, 60, 30 );
            g.strokeOval( x - 30, y - 15, 60, 30 );
        } else {
            g.setFill( Color.RED );
            g.fillRect( x - 30, y - 15, 60, 30 );
            g.strokeRect( x - 30, y - 15, 60, 30 );
        }
    }

}
