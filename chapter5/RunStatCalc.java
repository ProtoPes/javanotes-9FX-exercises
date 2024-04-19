package exercises.chapter5;

import textio.TextIO;
/**
 * RunStatCalc
 */
public class RunStatCalc {

    public static void main(String[] args) {
        StatCalc calc = new StatCalc();

        System.out.println("Enter big numbers please, '0' to finish entering:");
        while (true) {
            System.out.print(">  ");
            double num = TextIO.getlnDouble();
            if ( num == 0 )
                break;
            calc.enter(num);
        }

        if ( calc.getCount() != 0 ) {
            System.out.println(); System.out.println("Statistics:");
            System.out.println("Max value: " + calc.getMax());
            System.out.println("Min value: " + calc.getMin());
            System.out.println("Sum: " + calc.getSum());
            System.out.println("Count: " + calc.getCount());
            System.out.println("Mean: " + calc.getMean());
            System.out.println("Deviation: " + calc.getStandardDeviation());
        } else {
            System.out.println("No numbers were added");
        }

    }
}
