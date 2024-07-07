package chapter6.task11;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import chapter6.util.BlackjackHand;
import chapter6.util.Card;
import chapter6.util.Deck;

/**
 * This class implements a BlackJack game. The rules as in casino:
 * First 2 cards dealt to each hand. If someone has 21 - he won immediately.
 * Then each player can deal more cards to score more points.
 * If total score will be more 21 points - that player loses automatically.
 * If a player could take 5 cards without going over 21 - he won.
 * Dealer always wins on ties.
 * User have 100$ and can place bets.
 *
 * This class depends on several additional source code files:
 * Card.java, BlackjackHand.java, Deck.java, and cards.png.
 */
public class BlackJackGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    //---------------------------------------------------------------------

    private Canvas board;     // The canvas on which cards and message are drawn.
    private Image cardImages; // A single image contains the images of every card.

    private Deck deck;      // A deck of cards to be used in the game.
    private BlackjackHand userHand;
    private BlackjackHand dealerHand;
    private String message; // A message drawn on the canvas, which changes
                            //    to reflect the state of the game.
    private int userBank = 100;
    private Button hit;
    private Button stand;
    private Button newGame;
    private TextField betInput;
    private int currentBet;

    private boolean gameInProgress;

    
    /**
     * The start() method sets up the GUI and event handling. The root pane is
     * is a BorderPane. A canvas where cards are displayed occupies the center
     * position of the BorderPane.  On the bottom is a HBox that holds three buttons.
     * ActionEvent handlers are set up to call methods defined elsewhere
     * in this class when the user clicks a button.
     */
    public void start(Stage stage) {

        cardImages = new Image("cards.png");

        board = new Canvas(5*99 + 20, 150*2 + 30*4); 
                         // space for 5 cards, with 20-pixel border
                         // and space on the bottom for a message
        BorderPane center = new BorderPane(board);
        center.setStyle("-fx-border-color: darkred; -fx-border-width: 3 3 2 3");
        hit = new Button("Hit!");
        hit.setOnAction( e -> doHit() );
        stand = new Button("Stand!");
        stand.setOnAction( e -> doStand() );
        newGame = new Button("New Game");
        newGame.setOnAction( e -> doNewGame() );
        Label yourBet = new Label("Your bet:");
        betInput = new TextField("10");
        betInput.setPrefColumnCount(5);

        setGameInProgress(false);
        message = "Place your bet!";

        drawBoard();

        HBox buttonBar = new HBox(10, hit, stand, newGame, yourBet, betInput);
        buttonBar.setStyle("-fx-background-color: navajowhite; -fx-border-color: darkred; -fx-border-width: 2 3 3 3");
        buttonBar.setAlignment(Pos.CENTER);
        buttonBar.setPrefHeight(50);


        BorderPane root = new BorderPane();
        root.setCenter(center);
        root.setBottom(buttonBar);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Play Black Jack");
        stage.setResizable(false);
        stage.show();
        
    }

    /**
     * Respond to state of the game
     * Disables and enables buttons and text field
     */
    private void setGameInProgress(boolean status) {
        if (status) {
            hit.setDisable(false);
            stand.setDisable(false);
            newGame.setDisable(true);
            betInput.setEditable(false);
            hit.requestFocus();
        } else {
            hit.setDisable(true);
            stand.setDisable(true);
            newGame.setDisable(false);
            betInput.setEditable(true);
            betInput.requestFocus();
            betInput.selectAll();
        }
        gameInProgress = status;
    }

    /**
     * Called by the start() method, and called by an event handler if
     * the user clicks the "New Game" button.  Start a new game.
     */
    private void doNewGame() {


        // Check if the bet is legal
        try {
        // Check if the user still has money

            if (userBank == 0) {
                throw new IllegalStateException("You're broke! Casino " +
                    "doesn't accept clothes today!");
            }
            currentBet = Integer.parseInt(betInput.getText());
            if (currentBet < 1) {
                throw new IllegalStateException("You think you are smart???" +
                " Bet at least 1 Dollar!");
            }
            if (currentBet > userBank) {
                throw new IllegalStateException("You don't have " + currentBet +
                " , bet less.");
            }

            setGameInProgress(true);
            deck = new Deck();   // Create the deck and hand to use for this game.
            userHand = new BlackjackHand();
            dealerHand = new BlackjackHand();
            deck.shuffle();

            dealerHand.addCard( deck.dealCard() );
            dealerHand.addCard( deck.dealCard() );
            userHand.addCard( deck.dealCard() );
            userHand.addCard( deck.dealCard() );

            message = "You have " + userHand.getBlackjackValue() + ". Hit or Stand?";
            hit.requestFocus();

            int dealerScore = dealerHand.getBlackjackValue();
            int userScore = userHand.getBlackjackValue();
            if (dealerScore == 21) {
                gameLost("You lost, dealer has the Black Jack!");
            } else if (userScore == 21) {
                gameWon("You won! You have the Black Jack!");
            }

        } catch (NumberFormatException e) {
            message = "Your bet is not integer!";
        } catch (IllegalStateException ex) {
            message = ex.getMessage();
        }
        drawBoard();
    }

    /**
     * Called when user clicks `Hit!` button
     */
    private void doHit() {
        userHand.addCard( deck.dealCard() );
        int userScore = userHand.getBlackjackValue();
        if (userScore > 21) {
            gameLost("You lost! Your score is " + userScore);
        }
        if (userHand.getCardCount() == 5) {
            gameWon("You won! You took 5 cards without going over 21!");
        }
        drawBoard();
    }

    /**
     * Called whenever user clicks `Stand!` button
     */
    private void doStand() {
        while (dealerHand.getBlackjackValue() < 17) {
            dealerHand.addCard( deck.dealCard() );
        }

        int dealerScore = dealerHand.getBlackjackValue();
        int userScore = userHand.getBlackjackValue();
        if (dealerScore > 21) {
            gameWon("You won! Dealer overcome the limit: " + dealerScore);
        } else if (dealerHand.getCardCount() == 5) {
            gameLost("You lost! Dealer took 5 cards without going over 21!");
        } else if (userScore > dealerScore) {
            gameWon(String.format("You won! You have: %d, it's more than dealer's: %d",
            userScore, dealerScore));
        } else if (userScore == dealerScore) {
            gameLost(String.format("You lost! You and dealer have %d, dealer wins on ties!",
                userScore));
        } else {
            gameLost(String.format("You lost! Dealer has %d, you have %d",
                dealerScore, userScore));
        }
        drawBoard();
    }

    private void gameLost(String msg) {
        message = msg;
        userBank -= currentBet;
        setGameInProgress(false);
    }

    private void gameWon(String msg) {
        message = msg;
        userBank += currentBet;
        setGameInProgress(false);
    }

    /**
     * This method draws the message at the bottom of the
     * canvas, and it draws all of the dealt cards spread out
     * across the canvas.
     */
    private void drawBoard() {
        // This is always drawn:
        GraphicsContext g = board.getGraphicsContext2D();
        g.setFill( Color.DARKGREEN);
        g.fillRect(0,0,board.getWidth(),board.getHeight());
        g.setFill( Color.rgb(220,255,220) );
        g.setFont( Font.font(16) );

        // Messages
        if (userHand != null) {
            g.fillText("Dealer's Cards:", 20, 40);
            g.fillText("Your Cards:", 20, 210);
            // Draw dealer's cards
            int cardCt = dealerHand.getCardCount();
            for (int i = 0; i < cardCt; i++) {
                if (gameInProgress && i == 0) {
                    drawCard(g, null, 20, 60);
                } else {
                    drawCard(g, dealerHand.getCard(i), 20 + i * 99, 60);
                }
            }
            // Draw player's cards
            cardCt = userHand.getCardCount();
            for (int i = 0; i < cardCt; i++) {
                drawCard(g, userHand.getCard(i), 20 + i * 99, 230);
            }
        } else {
            g.fillText("Welcome to Black Jack!", 20, 40);
        }

        if (gameInProgress) {
            message = "You have " + userHand.getBlackjackValue() + ". Hit or Stand?";
        }

        g.fillText(message, 20, 400);
        g.setFill( Color.YELLOW);
        g.fillText("You have $" + userBank, 20, 380);

    } 


    /**
     * Draws a card with top-left corner at (x,y).  If card is null,
     * then a face-down card is drawn.  The card images are from 
     * the file cards.png; this program will fail without it.
     */
    private void drawCard(GraphicsContext g, Card card, int x, int y) {
        int cardRow, cardCol;
        if (card == null) {  
            cardRow = 4;   // row and column of a face down card
            cardCol = 2;
        }
        else {
            cardRow = 3 - card.getSuit();
            cardCol = card.getValue() - 1;
        }
        double sx,sy;  // top left corner of source rect for card in cardImages
        sx = 79 * cardCol;
        sy = 123 * cardRow;
        g.drawImage( cardImages, sx,sy,79,123, x,y,79,123 );
    }

}
