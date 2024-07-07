package chapter6.task7;

import chapter5.task2.StatCalc;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Simple graphic calculator, which receive a double value and
 * calculate sum, average and standard deviation.
 * It has one input field and two buttons: enter and clear.
 * To add a number one can press enter or click on the "enter" button.
 * Click on the "clear" button will reset all the values.
 * If the number is incorrect will show the message on the input
 * field text.
 * @author Nikolai Pavlov mokajin@protonmail.com
 */
public class StatCalcGUI extends Application {

    private StatCalc calc;
    private TextField field = new TextField();  // Field for user input

    // Labels specially for outputting calculated values on the screen
    private BigLabel valueEntries = new BigLabel("0");
    private BigLabel valueSum = new BigLabel("0");
    private BigLabel valueAvg = new BigLabel("0");
    private BigLabel valueDeviation = new BigLabel("0");


    @Override
    public void start(Stage stage) {

        reset();

        Label topText = new Label("Enter a number, click Enter:");
        topText.setTextFill(Color.WHITE);
        topText.setAlignment(Pos.CENTER);

        Button enter = new Button("Enter");
        enter.setOnAction( evt -> calculate(field.getText()) );
        enter.setDefaultButton(true);
        Button clear = new Button("Clear");
        clear.setOnAction( evt -> reset() );
        field.setPrefWidth(100);
        enter.setPrefWidth(100);
        clear.setPrefWidth(100);
        HBox buttons = new HBox(5.0, field, enter, clear);

        BigLabel numEntries = new BigLabel("Number of Entries:");

        BigHBox box1 = new BigHBox(numEntries, valueEntries);

        BigLabel sum = new BigLabel("Sum:");
        BigHBox box2 = new BigHBox(sum, valueSum);

        BigLabel avg = new BigLabel("Average: ");
        BigHBox box3 = new BigHBox(avg, valueAvg);

        BigLabel deviation = new BigLabel("Standard Deviation: ");
        BigHBox box4 = new BigHBox(deviation, valueDeviation);



        TilePane tilePane = new TilePane(0.0, 5.0, topText, buttons, box1, box2, box3, box4);
        tilePane.setTileAlignment(Pos.CENTER);
        tilePane.setPrefColumns(1);

        Pane root = new Pane(tilePane);
        tilePane.setPadding(new Insets(4.0));
        root.setBackground(Background.fill(Color.BLACK));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Simple Calc GUI");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Business logic: check if the number is correct, add to count
     * and show the values accordingly.
     * @param text string representation of Double value, if the value is
     * wrong shows the message in the input box.
     */
    private void calculate(String text) {
        try {
            calc.enter(Double.valueOf(text));
            valueEntries.setText(String.valueOf(calc.getCount()));
            valueAvg.setText(String.valueOf(calc.getMean()));
            valueDeviation.setText(String.valueOf(calc.getStandardDeviation()));
            valueSum.setText(String.valueOf(calc.getSum()));
        } catch (NumberFormatException e) {
            field.setText("Illegal value!");
        }
        field.selectAll();
    }

    /**
     * Called whenever a resetup needed, will create a new instance of StatCalc
     * and set text fields to 0.
     */
    private void reset() {
        calc = new StatCalc();
        field.setText("Type number");
        field.requestFocus();
        valueEntries.setText("0");
        valueAvg.setText("0");
        valueDeviation.setText("0");
        valueSum.setText("0");
    }

    /**
     * Class for easy layout, just sets white background, infinite size,
     * left padding to 8 pixels and 10 pixels distance between childs.
     */
    private class BigHBox extends HBox {
        BigHBox(Node... nodes) {
            super(10, nodes);
            this.setBackground(Background.fill(Color.WHITE));
            this.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
            this.setPadding(new Insets(0.0, 0.0, 0.0, 8.0));
        }
    }

    /**
     * Sets width to 130 pixels and alighn on center left, max size unlimited.
     */
    private class BigLabel extends Label {
        BigLabel(String text) {
            super(text);
            this.setPrefWidth(130.0);
            this.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
            this.setAlignment(Pos.CENTER_LEFT);
        }
    }
}
