package exercises.chapter5.task5;

import exercises.chapter5.task5.BlackjackHand;
import exercises.chapter5.task5.Deck;
import exercises.chapter5.task5.Card;
import textio.TextIO;

/**
 * This program will simulate a Black Jack game with interaction
 * from a player. Initially the player has 100 Dollars to bet.
 * The rules are as in a casino: dealer and player got 2 cards each,
 * dealer show only one card, if dealer has a BlackJack(21) he won
 * if player has BlackJack - he won. Dealer always wins on a ties.
 * First the player has a chance to hit(take a card) and then dealer
 * has the same chance. If someone will will reach above 21 points,
 * automatically lose the game.
 */
public class PlayBlackJack {
    private static BlackjackHand playerHand = new BlackjackHand();
    private static BlackjackHand dealerHand = new BlackjackHand();
    private static Deck deck = new Deck();

    public static void main(String[] args) {
        int userMoney = 100;
        boolean userAnswer;

        System.out.println("Black Jack Game!");
        do {
            System.out.println("You have: " + userMoney + " US Dollars");
            System.out.println("Your bet?");
            int playerBet;
            do {
                playerBet = TextIO.getlnInt();
                if (playerBet > userMoney || playerBet < 0) {
                    System.out.println("You don't have so much money! Bet less.");
                }
            } while (playerBet > userMoney || playerBet < 0);

            if (playGame()) {
                userMoney += playerBet;
                showHands();
                System.out.println("\nYou won " + playerBet);
            } else {
                userMoney -= playerBet;
                showHands();
                System.out.println("\nYou lose " + playerBet);
            }
            if (userMoney == 0) {
                System.out.println("Game over, you lose all your money");
                System.out.println("Sorry, we cannot accept clothes!");
                System.exit(0);
            }
            System.out.println("Play again?");
            userAnswer = TextIO.getlnBoolean();


        } while (userAnswer && userMoney > 0);
            System.out.println("You quit! Pussy?");
    }

    /**
     * Play one round.
     * @return true is player has won, false in case of opposite.
     */
    private static boolean playGame() {
        playerHand.clear();
        dealerHand.clear();
        deck.shuffle();

        for (int i = 0; i < 2; i++ ) {
            dealerHand.addCard(deck.dealCard());
            playerHand.addCard(deck.dealCard());
        }

        if ( dealerHand.getBlackjackValue() == 21 ) {
            return false;
        } else if ( playerHand.getBlackjackValue() == 21 ) {
            return true;
        }


        boolean playerAnswer;
        showHands(false);
        do {
            System.out.print("Hit? [y/n]  ");
            playerAnswer = TextIO.getlnBoolean();
            if (playerAnswer) {
                Card card = deck.dealCard();
                playerHand.addCard(card);
                System.out.println("You got " + card);
                System.out.println("Total: " + playerHand.getBlackjackValue());
                if ( playerHand.getBlackjackValue() > 21 ) {
                    return false;
                }
            }
        } while (playerAnswer);

        showHands();

        while (dealerHand.getBlackjackValue() <= 16) {
            Card card = deck.dealCard();
            System.out.println("Dealer got " + card);
            System.out.println("Total: " + dealerHand.getBlackjackValue());
            dealerHand.addCard(card);
            if (dealerHand.getBlackjackValue() > 21) {
                return true;
            }
        }

        if (dealerHand.getBlackjackValue() > 21) {
            return true;
        } else if (dealerHand.getBlackjackValue() >= playerHand.getBlackjackValue()) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * Show all cards of dealer and player.
     */
    private static void showHands() {
        showHands(true);
    }

    /**
     * Show cards of dealer and player.
     * @param full flags to show all the dealer's cards
     * or to hide one of them.
     */
    private static void showHands(boolean full) {
        System.out.println();
        System.out.println("<><><><><><><><><><><>");
        System.out.println("Your's hand:");
        showHands(playerHand, false);
        System.out.println("<><><><><><><><><><><>");
        System.out.println("<><><><><><><><><><><>");
        System.out.println("Dealer's hand:");
        showHands(dealerHand, !full);
        System.out.println("<><><><><><><><><><><>");
    }

    /**
     * Show a hand.
     * @param who what hand need to show.
     * @param hidden flags if the last card should be hidden.
     */
    private static void showHands(BlackjackHand who, boolean hidden) {
        int totalValue = who.getBlackjackValue();
        System.out.println();
        for (int i = 1; i <= who.getCardCount(); i++) {
            if ( hidden && (i == who.getCardCount()) ) {
                System.out.println(i + ": This card is face down!");
                totalValue -= who.getCard(i - 1).getValue();
            } else {
                System.out.println(i + ": " + who.getCard(i - 1));
            }
        }
        System.out.println("Total: " + totalValue);
    }







    
}
