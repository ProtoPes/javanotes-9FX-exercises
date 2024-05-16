package chapter5.own_tests;
/**
 * CreateStudent
 */
public class CreateStudent {

    public static void main(String[] args) {
        Student student = new Student("Loser");

        String name = student.getName();

        name = "Winner";

        System.out.println(student.getName());
    }
}
