// https://www.hackerrank.com/challenges/plus-minus/submissions/code/21756193

import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfInts = scanner.nextInt();
        int positiveCount = 0;
        int negativeCount = 0;
        int zeroCount = 0;
        for (int i=0; i<numberOfInts; i++) {
            int nextInt = scanner.nextInt();
            if (nextInt < 0)
                negativeCount++;
            else if (nextInt == 0)
                zeroCount++;
            else
                positiveCount++;
        }
        scanner.close();
        
        double positivePercent = (double) positiveCount / numberOfInts;
        double negativePercent = (double) negativeCount / numberOfInts;
        double zeroPercent = (double) zeroCount / numberOfInts;
        
        DecimalFormat df = new DecimalFormat("0.000000");
        System.out.println(df.format(positivePercent));
        System.out.println(df.format(negativePercent));
        System.out.println(df.format(zeroPercent));
    }
}
