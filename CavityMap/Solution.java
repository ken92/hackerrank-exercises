// https://www.hackerrank.com/challenges/cavity-map/submissions/code/21866215

import java.io.*;
import java.util.*;

public class Solution {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = Integer.parseInt(scanner.nextLine().trim());
        String[] table = new String[rows];
        for (int i=0; i<rows; i++) {
            table[i] = scanner.nextLine();
        }
        
        for (int row=0; row<rows; row++) {
            String stringRow = table[row];
            for (int column=0; column<stringRow.length(); column++) {
                char c1 = stringRow.charAt(column);
                if (isCavity(table, row, column)) {
                    String substring1 = stringRow.substring(0, column);
                    String substring2 = stringRow.substring(column+1, stringRow.length());
                    stringRow = substring1 + "X" + substring2;
                    table[row] = stringRow;
                    // skip next number, because it cannot be a cavity
                    column++;
                }
            }
            System.out.println(stringRow);
        }
    }
    
    private static boolean isCavity(String[] table, int row, int column) {
        int rows = table.length;
        String stringRow = table[row];
        int columns = stringRow.length();
        
        char c1 = stringRow.charAt(column);
        
        // check left
        if (column-1 >= 0) {
            char c2 = stringRow.charAt(column-1);
            if (c2 == 'X' || c1 <= c2) {
                return false;
            }
        } else {
            return false;
        }
        
        // check right
        if (column+1 < columns) {
            char c2 = stringRow.charAt(column+1);
            if (c2 == 'X' || c1 <= c2) {
                return false;
            }
        } else {
            return false;
        }
        
        // check up
        if (row-1 >= 0) {
            String stringRow2 = table[row-1];
            char c2 = stringRow2.charAt(column);
            if (c2 == 'X' || c1 <= c2) {
                return false;
            }
        } else {
            return false;
        }
        
        // check down
        if (row+1 < rows) {
            String stringRow2 = table[row+1];
            char c2 = stringRow2.charAt(column);
            if (c2 == 'X' || c1 <= c2) {
                return false;
            }
        } else {
            return false;
        }
        
        return true;
    }
}
