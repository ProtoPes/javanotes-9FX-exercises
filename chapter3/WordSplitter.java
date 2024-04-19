package exercises.chapter3;

import textio.TextIO;

/**
 * WordSplitter
    - Outline of a program
        while ( curChar != '\n' )
            read new Curchar
            if ( curChar is '\'' (tick) && word is not blank && nextChar is a letter )
                
            if ( curChar is a letter ):
                word = word + curChar
            else if ( word is not blank )
                print word + newLine
                word = ""
            - success!
 */
public class WordSplitter {

    public static void main(String[] args) {

        char curChar; // The character that was readed the last
        String word = ""; // Variable for store the word before printing

        System.out.println("Please type a line of text:");

        do {
            curChar = TextIO.getAnyChar();
            if ( Character.isLetter(curChar) ) {
                word += curChar;
            } else if ( curChar == '`' || curChar ==  '’' ) {
                if ( !word.isEmpty() && Character.isLetter(TextIO.peek()) ) {
                    word += curChar;
                }
            } else if ( ! word.isEmpty() ) {
                System.out.println(word);
                word = "";
            }
        } while ( curChar != '\n' );
    }
}
