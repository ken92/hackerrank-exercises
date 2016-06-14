// https://www.hackerrank.com/challenges/the-grid-search/submissions/code/21768287

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTests = Integer.parseInt(scanner.nextLine().trim());
        for (int t=0; t<numberOfTests; t++) {
            String[] grid = getGrid(scanner);
            String[] patternGrid = getGrid(scanner);
            
            if (gridContainsPatternGrid(grid, patternGrid, 0, -1, 0))
                System.out.println("YES");
            else
                System.out.println("NO");
            
            int gridWidth = grid[0].length();
            int gridHeight = grid.length;
            int patternGridWidth = patternGrid[0].length();
            int patternGridHeight = patternGrid.length;
            
        }
        scanner.close();
    }
    
    private static String[] getGrid(Scanner scanner) {
        String[] dimensions = scanner.nextLine().trim().split(" ");
        int gRows = Integer.parseInt(dimensions[0]);
        int gColumns = Integer.parseInt(dimensions[1]);
        String[] grid = new String[gRows];
        for (int r=0; r<gRows; r++) {
            grid[r] = scanner.nextLine().trim();
        }
        return grid;
    }
    
    private static boolean gridContainsPatternGrid(String[] grid, String[] patternGrid, int gridLineIndex, int gridIndex, int patternGridLineIndex) {
        // search was successful if it is trying to find a line in the pattern that does not exist
        if (patternGridLineIndex >= patternGrid.length)
            return true;
        
        // search was unsuccessful if it is searching grid lines that do not exist
        if (gridLineIndex >= grid.length)
            return false;
        
        String gridLine = grid[gridLineIndex];
        String patternLine = patternGrid[patternGridLineIndex];
        
        // check if this ongoing search has failed
        if (patternGridLineIndex != 0) {
            int index = gridLine.indexOf(patternLine, gridIndex);
            if (index != gridIndex)
                return false;
            return gridContainsPatternGrid(grid, patternGrid, gridLineIndex+1, gridIndex, patternGridLineIndex+1);
        }
        
        // patternGridLineIndex == 0 in this case; start any new searches that might be applicable and continue down the grid
        gridIndex = gridLine.indexOf(patternLine);
        while (gridIndex != -1) {
            boolean b = gridContainsPatternGrid(grid, patternGrid, gridLineIndex+1, gridIndex, patternGridLineIndex+1);
            if (b) return true;
            gridIndex = gridLine.indexOf(patternLine, gridIndex+1);
        }
        
        return gridContainsPatternGrid(grid, patternGrid, gridLineIndex+1, -1, 0);
    }
}
