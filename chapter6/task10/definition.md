Write a GUI Blackjack program that lets the user play a game of Blackjack, with the
computer as the dealer. The program should draw the user’s cards and the dealer’s cards,
just as was done for the graphical HighLow card game in Subsection 6.6.1. You can use
the source code for that game, HighLowGUI.java, for some ideas about how to write your
Blackjack game. The structures of the HighLow program and the Blackjack program are
very similar. You will certainly want to use the drawCard() method from the HighLow
program.
You can find a description of the game of Blackjack in Exercise 5.5. Add the following
rule to that description: If a player takes five cards without going over 21, that player
wins immediately. This rule is used in some casinos. For your program, it means that you
only have to allow room for five cards. You should make the canvas just wide enough to
show five cards, and tall enough to show both the user’s hand and the dealer’s hand.
Note that the design of a GUI Blackjack game is very different from the design of the
text-oriented program that you wrote for Exercise 5.5. The user should play the game by
clicking on “Hit” and “Stand” buttons. There should be a “New Game” button that can
be used to start another game after one game ends. You have to decide what happens
when each of these buttons is pressed. You don’t have much chance of getting this right
unless you think in terms of the states that the game can be in and how the state can
change.
Your program will need the classes defined in Card.java, Hand.java, Deck.java, and
BlackjackHand.java. It will also need the images file cards.png, which contains pictures
of the cards.
The next exercise has a picture of a Blackjack game that you can use a guide, except
that the version for this exercise does not allow betting. (Some aesthetic changes to the
GUI were made in that Blackjack program, compared to the HighLow program.)
