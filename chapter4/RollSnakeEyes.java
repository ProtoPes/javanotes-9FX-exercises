package exercises.chapter4;

/**
 * RollSnakeEyes
 * This is a simulation of rolling a pair of dice until snake eyes
 * is received. It will print the number of tries to get it.
 */
public class RollSnakeEyes {

    public static void main(String[] args) {
        System.out.println("Number of tries to get snake eyes is " + rollDiceToNumber(2));
    }

    /**
     * Simulates rolling a pair of dice until a given total show up
     * Precondition: target number must be in range 2 to 12
     * @param target the total that we want to get
     * @return the number of tries to get the wanted total
     * @throws IllegalArgumentException if the parameter is not in
     * valid range
     */
    private static int rollDiceToNumber(int target) {
        if ( target < 2 || target > 12 ) {
            throw new IllegalArgumentException();
        }
        int die1 = 0;
        int die2 = 0;
        int counter = 0;
        while( (die1 + die2) != target ) {
            counter++;
            die1 = (int)(Math.random()*6 + 1);
            die2 = (int)(Math.random()*6 + 1);
        };
        return counter;
    }
}
