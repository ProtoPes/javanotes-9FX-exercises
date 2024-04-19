package exercises.chapter3;
/**
 * MaxDivisorsFinderImproved
 *
 * This program ountuts the numbers from range 1 to 10000 that has
 * the largest numbers of divisors
 *
 * Outline:
 * process every number in range
 * store count in an array
 * compare current max count with new count
 * store the new count if it is bigger
 * go through the array and print every numbrer that has max count
 */
public class MaxDivisorsFinderImproved {

    public static void main(String[] args) {

        int[] divisors = new int[10000];
        int maxDivisors = 1;

        for (int i = 1; i <= divisors.length; i++) {
            int divisorsCount = 0;
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    divisorsCount++;
                }
            }
            if (maxDivisors < divisorsCount){
                maxDivisors = divisorsCount;
            }
            divisors[i - 1] = divisorsCount;
        }

        System.out.println("Among integers between 1 and 10000,");
        System.out.printf("The maximum number of divisors was %d%n", maxDivisors);
        System.out.println("Numbers with that many divisors include:");
        for (int i = 0; i < divisors.length; i++) {
            if (divisors[i] == maxDivisors) {
                System.out.printf("%8d%n", i + 1);
            }
        }



    }
}
