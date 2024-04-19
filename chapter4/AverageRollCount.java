package exercises.chapter4;

/**
 * AverageRollCount
 * Simulate experiment to roll a pair of dice 10000 for each
 * possible sum and print average number of rolls that takes to get it
 */
public class AverageRollCount {

    public static void main(String[] args) {
        System.out.println("Total On Dice       Average Number of Rolls");
        System.out.println("-------------       -----------------------");
        for (int i = 2; i <= 12; i++) {
            System.out.printf("%7d%27.4f%n", i, doTest(i));
        }
    }


    /**
     * Simulate rolling a pair of dice until we get
     * target sum 10000 times
     * @param target desired sum on a pair of dice
     * @return average number of tries to get a given target
     */
    private static double doTest(int target) {
        int total = 0;
        for (int i = 0; i < 10000; i++) {
            total += rollTo(target);
        }
        return total / 10000.0;
    }

    /**
     * Simulate a roll of pair of dice 10000 times and
     * return how many times we have got a desired sum
     * Precondition: parameter should be in range 2 to 12
     * @param target sum of dice for which we are testing
     * @return average number of rolls to get a given total value 10000 times
     */
    private static double performTest(int target) {
        int tries = 0;
        int count = 0;
        int die1;
        int die2;
        while (count != 10000) {
            tries++;
            die1 = (int)(Math.random()*6 + 1);
            die2 = (int)(Math.random()*6 + 1);
            if ( (die1 + die2) == target ) {
                count++;
            }
        }
        return tries / 10000.0;
    }

    /**
     * Simulates rolling a pair of dice until a given total show up
     * Precondition: target number must be in range 2 to 12
     * @param target the total that we want to get
     * @return the number of tries to get the wanted total
     * @throws IllegalArgumentException if the parameter is not in
     * valid range
     */
    private static int rollTo(int target) {
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
