package chapter6.task8;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LineWordCharCounter extends Application{

    private TextArea userInput;
    // Labels for outputting calculated values
    private Label linesNumber = createBoldLabel("");
    private Label wordsNumber = createBoldLabel("");
    private Label charsNumber = createBoldLabel("");

    @Override
    public void start(Stage stage) {
        userInput = new TextArea();
        userInput.setPrefColumnCount(25);

        Button process = new Button("Process the Text");
        process.setAlignment(Pos.CENTER);
        HBox box0 = new HBox(process);
        box0.setAlignment(Pos.CENTER);
        process.setOnAction( evt -> calculate() );

        Label linesLabel = createBoldLabel("Number of lines:");
        Label wordsLabel = createBoldLabel("Number of words:");
        Label charsLabel = createBoldLabel("Number of chars:");

        HBox box1 = createWhiteBox(linesLabel, linesNumber);
        HBox box2 = createWhiteBox(wordsLabel, wordsNumber);
        HBox box3 = createWhiteBox(charsLabel, charsNumber);

        VBox root = new VBox(5, userInput, box0, box1, box2, box3);
        root.setBackground(Background.fill(Color.DARKBLUE));
        root.setPadding(new Insets(3));


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Line/Word/Char Counter");
        stage.show();
    }

    /*
     * Business logic. Count words, lines, characters. Save values in
     * global variables and change labels accordingly.
     */
    private void calculate() {
        String input = userInput.getText();
        int length = input.length();
        int lines = (length == 0) ? 0 : 1;
        int words = 0;
        for (int i = 0; i < length; i++){
            char c = input.charAt(i);
            if (c == '\n') {
                lines++;
            }
            /*
             * Logic: count a word if we have a letter and it is the last in the input
             * or we have got not a letter as next character
             */
            if ( Character.isLetter(c) && ( (i == length - 1) || (!Character.isLetter(input.charAt(i + 1))) ) ) {
                words++;
            }
        }
        linesNumber.setText(String.valueOf(lines));
        wordsNumber.setText(String.valueOf(words));
        charsNumber.setText(String.valueOf(length));
    }

    /**
     * Utility method for creating Label with bolder serif font 15px size
     * @param text Text in the label
     */
    private Label createBoldLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-family: serif; -fx-font-size: 15; -fx-font-weight: bolder");
        return label;
    }
    /**
     * Utility method for creating HBox object with white background.
     * Width between child nodes are 5px.
     * Padding 5px top, bottom, 10px left.
     * @param args array of Nodes that will be added in HBox
     */
    private HBox createWhiteBox(Node... args) {
        HBox box = new HBox(5, args);
        box.setBackground(Background.fill(Color.WHITE));
        box.setPadding(new Insets(5, 0, 5, 10));
        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
