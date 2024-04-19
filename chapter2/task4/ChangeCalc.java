import java.util.Scanner;

/**
 * ChangeCalc
 */
public class ChangeCalc {

    private static int quarters;
    private static int dimes;
    private static int nickels;
    private static int pennies;
    private static double total;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        askHowMany("quarters");
        quarters = input.nextInt();
        askHowMany("dimes");
        dimes = input.nextInt();
        askHowMany("nickels");
        nickels = input.nextInt();
        askHowMany("pennies");
        pennies = input.nextInt();

        total = (quarters * 25 + dimes * 10 + nickels * 5 + pennies) / 100.0;

        System.out.printf("You have %1.2f US dollars!\n", total);
        System.out.println();

    }
    private static void askHowMany(String what) {
        System.out.printf("How many %s do you have?\n", what);
    }
}
