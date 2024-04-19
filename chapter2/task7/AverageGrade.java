import textio.TextIO;
/**
 * AverageGrade
 */
public class AverageGrade {
    public static void main(String[] args) {
        TextIO.readFile("testdata.txt");
        String studentName = TextIO.getln();
        double total;
        int score;
        score = TextIO.getInt();
        total = score;
        score = TextIO.getInt();
        total += score;
        score = TextIO.getInt();
        total += score;
        total = total / 3;
        System.out.printf("The average score of %s is %1.2f%n", studentName, total);
    }
}
