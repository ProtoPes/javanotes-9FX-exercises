import java.util.Scanner;

public class Greeter {
    private static String userName;

    public static void main(String[] args) {
        System.out.println("What is your name?");
        Scanner sc = new Scanner(System.in);
        userName = sc.nextLine();
        sc.close();
        System.out.printf("Hello, %s, nice to meet you!\n", userName.toUpperCase());
    }
}

