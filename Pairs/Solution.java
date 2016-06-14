// https://www.hackerrank.com/challenges/pairs/submissions/code/21730272

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int numberOfInts = scan.nextInt();
        int difference = scan.nextInt();
        
        int[] arr = new int[numberOfInts];
        for (int i=0; i<numberOfInts; i++) {
            arr[i] = scan.nextInt();
        }
        scan.close();
        
        Arrays.sort(arr);
        
        int i = 0;
        int j = 1;
        int count = 0;
        while (j < arr.length) {
            while (j < arr.length && arr[j] - arr[i] < difference) {
                j++;
            }
            if (j < arr.length)
                if (arr[j] - arr[i] == difference) count++;
            i++;
            j = i+1;
        }
        System.out.println(count);
    }
}
