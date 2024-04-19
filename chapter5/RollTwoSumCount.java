package exercises.chapter5;

import exercises.chapter5.PairOfDice;

/**
 * RollTwoSumCount
 */
public class RollTwoSumCount {

    public static void main(String[] args) {
        PairOfDice pDice = new PairOfDice();
        int rolled = 0;
        int firstDie; 
        int secondDie;
        do {
            pDice.roll();
            rolled++;
            firstDie = pDice.getDie1();
            secondDie = pDice.getDie2();
        } while (firstDie + secondDie != 2);

        System.out.println("It took " + rolled + " tries to get total two on the dice");
        System.out.println(pDice);
    }
}
