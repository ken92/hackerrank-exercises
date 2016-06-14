// https://www.hackerrank.com/challenges/time-conversion/submissions/code/21758303

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String twelveHourTime = scanner.nextLine();
        scanner.close();
        
        String[] twelveHourTimeSplit = twelveHourTime.split(":");
        twelveHourTimeSplit[twelveHourTimeSplit.length - 1] = twelveHourTimeSplit[twelveHourTimeSplit.length - 1].substring(0, 2);
        boolean isPM = twelveHourTime.charAt(twelveHourTime.length() - 2) == 'P';
        if (isPM) {
            if (!twelveHourTimeSplit[0].equals("12")) {
                int hour = Integer.parseInt(twelveHourTimeSplit[0]);
                hour += 12;
                twelveHourTimeSplit[0] = String.valueOf(hour);
            }
        }
        else if (twelveHourTimeSplit[0].equals("12")) {
            twelveHourTimeSplit[0] = "00";
        }
        
        System.out.print(twelveHourTimeSplit[0]+":"+twelveHourTimeSplit[1]+":"+twelveHourTimeSplit[2]);
    }
}
