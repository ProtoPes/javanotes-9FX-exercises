package exercises.chapter5;


/**
 * This program computes average number of tries
 * to get each possible total of two dice, it simulates
 * rolling a pair of dice 10000 times for each total.
 * It also show maximum number of tries for each run and
 * a standard deviation.
 */
public class DiceAverageFrom10000 {

    public static void main(String[] args) {
        PairOfDice pDice = new PairOfDice();
        for (int i = 2; i <= 12; i++) {
            StatCalc sCalc = new StatCalc();

            for (int j = 0; j < 10000; j++) {
                int numberOfTries = 0;
                while (true) {
                    numberOfTries++;
                    pDice.roll();
                    if ( pDice.getTotal() == i) {
                        break;
                    }
                }
                sCalc.enter(numberOfTries);
            }

            System.out.println("Results for " + i);
            System.out.printf("Average tries :%10.2f%n", sCalc.getMean());
            System.out.printf("Max tries     :%10.2f%n", sCalc.getMax());
            System.out.printf("Deviation     :%10.2f%n", sCalc.getStandardDeviation());
            System.out.println("=========================");
        }
    }
}
