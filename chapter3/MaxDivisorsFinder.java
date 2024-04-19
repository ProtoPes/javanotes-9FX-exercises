/**
 * MaxDivisorsFinder
 */
public class MaxDivisorsFinder {

    public static void main(String[] args) {

        int targetNumber = 1;
        int divisorsQuantity = 1;

        for (int i = 1; i <= 10000; i++) {
            int divisorCount = 0;
            for (int testDivisor = 1; testDivisor <= i; testDivisor++) {
                if ( i % testDivisor == 0 ) {
                    divisorCount++;
                }
            }
            if (divisorsQuantity <= divisorCount) {
                targetNumber = i;
                divisorsQuantity = divisorCount;
            }
        }
        System.out.printf("The number with max divisors is %d, quantity of divisors:%d%n", targetNumber, divisorsQuantity);
    }
}
