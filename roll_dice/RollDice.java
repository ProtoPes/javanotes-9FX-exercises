/**
 * RollDice
 */
public class RollDice {
    private static int roll;
    private static int total;

    public static void main(String[] args) {
        doRoll();
        System.out.printf("The first die comes up %d\n", roll);
        doRoll();
        System.out.printf("The second die comes up %d\n", roll);
        System.out.printf("Your total roll is %d\n", total);
    }

    private static void doRoll() {
        roll = (int)(Math.random()*6) + 1;
        total += roll;
    }
}
