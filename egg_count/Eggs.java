import java.util.Scanner;

class Eggs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many eggs do you have?");
        int quantity = scanner.nextInt();
        int gross = quantity / 144;
        int dozen = quantity % 144 / 12;
        int leftover = quantity % 144 % 12;
        System.out.printf("Your number of eggs is %d gross, %d dozen, and %d%n", gross, dozen, leftover);
    }
}


