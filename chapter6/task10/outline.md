# Black Jack rules

1. Deal 2 cards to each player
    - If dealer has 21: he won
    - If user has 21: he won
    - User can see only one of dealer's cards.
2. Loop until user say "stand", then if user didn't won, same loop for dealer:
    - If player go over 21: he lost.
    - If player took 5 cards and total is less than 22: he won
    - When dealer's move: all his cards are open.
    - Dealer goes hit while his total less than 17
3. If game still on:
    - Winner is who has more points.
    - Dealer wins on ties

# Layout

Total size of canvas:
5*99 + 20 (to show 5 cards) width
123*2 + 20*3 height

1. Green canvas for cards and messages
    1.1 "Dealer's Cards" message ~ 20px height
    1.2 Space to show dealer's cards ~ 123px height
    1.3 "Your Cards" message ~ 20px height
    1.4 Space to show user's cards 123px height
    1.5 Message for explain current state of a game 20px height
2. Bottom HBox button bar with creamy colour ~ 20px height
    2.1 "Hit!" button
    2.2 "Stand!" button
    2.3 "New Game" button

# States of the program:

0. From new game until user hit "Stand!"
    - `New Game` button is disabled.
    - Enable `Hit!` and `Stand!` buttons.
    - Dealer has 2 cards, we see one only
    - Player sees all his cards
    - Message: "You have {score}. Hit or Stand?"
1. Player hit "stand", dealers move.
    - Disable "Hit!" and "Stand!" buttons.
    - Show all dealer's cards.
2. Player lost, dealer has Blackjack or just more or equal points
    - Enable `New Game` button.
    - Show all dealer's cards
    - "You lost, dealer has the Black Jack!"
    - "You lost, dealer has {score} , you have {score}"
    - "You lost, you and dealer have {score}, dealer wins on ties!"
3. Player won, he has Blackjack or more points than dealer
    - Enable `New Game` button.
    - "You won! You have the Black Jack!"
    - "You won! Dealer overcome the limit!"
    - "You won! You have: {score}, it's more than dealer's: {score}"

