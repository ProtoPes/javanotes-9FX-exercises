package exercises.chapter3;
import textio.TextIO;

/**
 * SalesReport
    - Outline of a program:
        while ( no EOF )
            curChar = getChar()
            if ( curChar == ':' )
                curLine = readLine()
                try
                    value = Double.valueOf(curLine)
                    total += value
                catch ( NumberFormatException e )
                    exceptionCounter ++
 */
public class SalesReport {

    public static void main(String[] args) {
        
        String file;
        char curChar;
        String curString;
        double total = 0.0;
        int counter = 0;

        try {
            TextIO.readFile("sales.dat");
        } catch (IllegalArgumentException e1) {
            System.out.println("Hello! File 'sales.dat' was not found! Provide a file, please:");
            file = TextIO.getlnWord();
            try {
                TextIO.readFile(file);
            } catch (IllegalArgumentException e2) {
                System.out.println("File not found!");
                System.out.println("Terminating the protgram!");
                System.exit(1);
            }
        }

        while (TextIO.eof() == false) {
            curChar = TextIO.getAnyChar();
            if (curChar == ':') {
                curString = TextIO.getln();
                try {
                    total += Double.valueOf(curString);
                } catch (NumberFormatException e) {
                    counter ++;
                }
            }
        }
        System.out.printf("Total Sales: %1.2f , Cities without data: %d%n", total, counter);
    }
}
