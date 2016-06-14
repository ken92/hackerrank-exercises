// https://www.hackerrank.com/challenges/pangrams/submissions/code/22027031

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine().trim();
        scanner.close();
        
        if (isPangram(line))
            System.out.println("pangram");
        else
            System.out.println("not pangram");
    }
    
    private static boolean isPangram(String line) {
        line = line.toLowerCase();
        int rangeMin = 97;
        int rangeMax = 122;
        for (int i=rangeMin; i<=rangeMax; i++) {
            if (line.indexOf(i) == -1) {
                return false;
            }
        }
        return true;
    }
}
