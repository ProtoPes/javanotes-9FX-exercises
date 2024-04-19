import java.util.Scanner;

/**
 * SnakeEyesCount
 */
public class SnakeEyesCount {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String exit;
        do {
            System.out.println("Starting new round!");
            doRound();
            System.out.print("Exit? [y/N] ");
            exit = in.nextLine();
            System.out.println();
        } while (! exit.equalsIgnoreCase("y"));
        in.close();
    }

    private static int rolldie() {
        return (int)(Math.random()*6) + 1;
    }

    private static void doRound() {
        int die1;
        int die2;
        int count = 0;

        do {
            die1 = rolldie();
            die2 = rolldie();
            count++;
        } while (die1 != 1 || die2 != 1);

        System.out.printf("It took %d attempts to get \"Snake eyes\", die1:%d, die2:%d%n", count, die1, die2);
    }
}
