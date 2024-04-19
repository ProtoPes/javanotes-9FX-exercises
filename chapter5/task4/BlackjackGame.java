package exercises.chapter5.task4;

import exercises.chapter5.task4.BlackjackHand;
import textio.TextIO;
/**
 * BlackjackGame
 * This program simulates an automatic Black Jack card game but
 * with hand size randomly choosen between 2 and 6.
 * User can only choose to play one more time or not.
 */
public class BlackjackGame {

    private static Deck deck = new Deck();
    private static BlackjackHand bjHand = new BlackjackHand();

    public static void main(String[] args) {
        System.out.println("Let's play Black Jack!");
        boolean userAnswer;
        do {
            playBJ();
            System.out.println("One more time? [y/n]");
            userAnswer = TextIO.getlnBoolean();
        } while (userAnswer);
    }

    /**
     * Play one round in BJ
     * It shuffles the deck and clears the hand
     * before playing.
     * It will deal random number of cards from
     * 2 to 6 and show them and compute a value
     * according Black Jack rules
     */
    private static void playBJ() {
        bjHand.clear();
        deck.shuffle();
        int handSize = (int)(Math.random() * 5 + 2);
        for (int i = 0; i < handSize; i++) {
            bjHand.addCard(deck.dealCard());
            System.out.println(bjHand.getCard(i));
        }
        System.out.println(bjHand.getBlackjackValue());
    }
}
