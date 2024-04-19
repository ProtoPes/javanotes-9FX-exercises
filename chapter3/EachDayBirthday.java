package exercises.chapter3;
/**
 * EachDayBirthday
 * Outline:
 * We need to repeatedly check random people when do they have
 * a birthday until we found every day of the year.
 * At the same time we need to remember how many people we have already asked
 *
 * Pseudocode:
 * array of boolean
 * int daysFound
 * int peopleChecked
 *
 * infinite loop:
 *      compute random int from 0 to 365
 *      if a value by random index is false:
 *          daysFound++
 *          value = true
 *      peopleChecked++
 *      if daysFound == 365:
 *          break
 *  print peopleChecked
 *
 *
 *  Comparing to a solution:
 *  used an infinite loob with a break statement
 *  more elegant way is to use while until daysFound < 365
 */
public class EachDayBirthday {

    public static void main(String[] args) {

        boolean[] daysOfYear = new boolean[365];
        int daysFound = 0;
        int peopleChecked = 0;

        while (true) {
            int randomInt = (int)(Math.random()*365);
            peopleChecked++;
            if ( daysOfYear[randomInt] == false ) {
                daysFound++;
                daysOfYear[randomInt] = true;
            }
            if ( daysFound == 365 ) {
                break;
            }
        }
        System.out.printf("%d people checked before every birthday of the year were found%n", peopleChecked);
    }
}
