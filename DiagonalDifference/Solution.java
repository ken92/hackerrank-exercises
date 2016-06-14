// https://www.hackerrank.com/challenges/diagonal-difference/submissions/code/21731359

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tableWidth = scanner.nextInt();
        
        int sumPrimaryDiagonal = 0;
        int sumSecondaryDiagonal = 0;
        int secondaryRowCounter = 0;
        int secondaryColumnCounter = tableWidth - 1;
        for (int row=0; row<tableWidth; row++) {
            for (int column=0; column<tableWidth; column++) {
                int nextInt = scanner.nextInt();
                if (row == column) {
                    sumPrimaryDiagonal += nextInt;
                }
                if (row == secondaryRowCounter && column == secondaryColumnCounter) {
                    secondaryRowCounter++;
                    secondaryColumnCounter--;
                    sumSecondaryDiagonal += nextInt;
                }
            }
        }
        System.out.println(Math.abs(sumPrimaryDiagonal - sumSecondaryDiagonal));
        
        scanner.close();
    }
}
