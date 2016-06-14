// https://www.hackerrank.com/challenges/staircase/submissions/code/21757218

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int height = scanner.nextInt();
        int spaceCount = height - 1;
        for (int i=0; i<height; i++) {
            for (int j=0; j<height; j++) {
                if (j < spaceCount)
                    System.out.print(" ");
                else
                    System.out.print("#");
            }
            spaceCount--;
            System.out.println("");
        }
        
        scanner.close();
    }
}
