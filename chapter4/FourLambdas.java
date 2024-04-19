package exercises.chapter4;

/**
 * FourLambdas
 */
public class FourLambdas {

    public static final ArrayProcessor computeMax = arr -> {
            double maxVal = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (maxVal < arr[i]) {
                    maxVal = arr[i];
                }
            }
            return maxVal;
        };
    public static final ArrayProcessor computeMin = arr -> {
            double minVal = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (minVal > arr[i]) {
                    minVal = arr[i];
                }
            }
            return minVal;
        };
    public static final ArrayProcessor computeSum = arr -> {
            double sumVal = 0.0;
            for (int i = 0; i < arr.length; i++) {
                sumVal += arr[i];
            }
            return sumVal;
        };
    public static final ArrayProcessor computeAvg = arr -> computeSum.apply(arr) / arr.length;

    public static void main(String[] args) {
        double[] arrayD = {1.2, 1.1, 0.6, 5.6, 4.8, 1.1};
        System.out.println("Max value: " + computeMax.apply(arrayD));
        System.out.println("Min value: " + computeMin.apply(arrayD));
        System.out.println("Sum value: " + computeSum.apply(arrayD));
        System.out.println("Avg value: " + computeAvg.apply(arrayD));

        System.out.println("Occurence value: " + (int)counter(1.1).apply(arrayD));
    }

    public static ArrayProcessor counter( double value ) {
        ArrayProcessor occurenceCounter = arr -> {
            int occurence = 0;
            for (int i = 0; i < arr.length; i++) {
                if (value == arr[i]) {
                    occurence++;
                }
            }
            return occurence;
        };
        return occurenceCounter;
    }
}
