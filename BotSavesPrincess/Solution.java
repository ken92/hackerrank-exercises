// https://www.hackerrank.com/challenges/saveprincess/submissions/game/21971348

import java.io.*;
import java.util.*;

public class Solution {
    private static enum CORNER {BOTTOM_LEFT, BOTTOM_RIGHT, TOP_LEFT, TOP_RIGHT}
    private static enum DIRECTION {DOWN, LEFT, UP, RIGHT}
    
    private static final boolean debug = false;
    private static void debug(String str) {
        if (debug)
            System.out.println(str);
    }
    
    private static class Node {
        public int marioRow = -1;
        public int marioColumn = -1;
        public CORNER corner = null;
        public DIRECTION direction = null;
        public Node parent = null;
        
        public Node(DIRECTION direction, CORNER corner, int marioRow, int marioColumn, Node parent) {
            this.corner = corner;
            this.parent = parent;
            this.direction = direction;
            this.marioRow = marioRow;
            this.marioColumn = marioColumn;
        }
    }
    
    public static class Solver {
        private String[] grid;
        
        public Solver() {
            Scanner scanner = new Scanner(System.in);
            int gridSize = 0;
            {
                String gridSizeStr = scanner.nextLine().trim();
                gridSize = Integer.parseInt(gridSizeStr);
            }
            
            grid = new String[gridSize];
            for (int row=0; row<gridSize; row++)
                grid[row] = scanner.nextLine().trim();
            
            scanner.close();
            
            boolean found = false;
            for (CORNER corner : CORNER.values()) {
                if (findPrincessInCorner(corner)) {
                    found = true;
                    break;
                }
            }
            if (!found)
                System.out.println("Princess not found!");
        }
        
        private boolean findPrincessInCorner(CORNER corner) {
            debug("trying to find corner: "+corner);
            int marioStart = (int) grid.length / 2;
            DIRECTION startDirection = null;
            
            Node root = new Node(startDirection, corner, marioStart, marioStart, null);
            Stack<Node> stack = new Stack<Node>();
            stack.push(root);
            
            while (!stack.empty()) {
                Node node = stack.pop();
                debug("  popped node: "+node.marioRow+","+node.marioColumn+"  direction: "+node.direction+"  corner: "+node.corner);
                if (isCorner(node.marioRow, node.marioColumn)) {
                    String row = grid[node.marioRow];
                    if (row.charAt(node.marioColumn) == 'p') {
                        printSolution(node);
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    expandNode(stack, node);
                }
            }
            return false;
        }
        
        private void printSolution(Node node) {
            StringBuilder builder = new StringBuilder();
            while (node != null) {
                if (node.direction != null)
                    builder.insert(0, node.direction + "\n");
                node = node.parent;
            }
            System.out.print(builder.toString());
        }
        
        private boolean isCorner(int row, int column) {
            // bottom left
            if (row+1 == grid.length && column == 0)
                return true;
            
            // bottom right
            if (row+1 == grid.length && column+1 == grid.length)
                return true;
            
            // top left
            if (row == 0 && column == 0)
                return true;
            
            // top right
            if (row == 0 && column+1 == grid.length)
                return true;
            
            return false;
        }
        
        private void expandNode(Stack<Node> stack, Node node) {
            DIRECTION nextDirection;
            switch (node.corner) {
                case BOTTOM_LEFT:
                    if (node.direction == DIRECTION.DOWN) {
                        if (node.marioColumn > 0)
                            nextDirection = DIRECTION.LEFT;
                        else
                            nextDirection = DIRECTION.DOWN;
                    } else {
                        if (node.marioRow < grid.length)
                            nextDirection = DIRECTION.DOWN;
                        else
                            nextDirection = DIRECTION.LEFT;
                    }
                    break;
                case BOTTOM_RIGHT:
                    if (node.direction == DIRECTION.DOWN) {
                        if (node.marioColumn < grid.length)
                            nextDirection = DIRECTION.RIGHT;
                        else
                            nextDirection = DIRECTION.DOWN;
                    } else {
                        if (node.marioRow < grid.length)
                            nextDirection = DIRECTION.DOWN;
                        else
                            nextDirection = DIRECTION.RIGHT;
                    }
                    break;
                case TOP_RIGHT:
                    if (node.direction == DIRECTION.UP) {
                        if (node.marioColumn < grid.length)
                            nextDirection = DIRECTION.RIGHT;
                        else
                            nextDirection = DIRECTION.UP;
                    } else {
                        if (node.marioRow > 0)
                            nextDirection = DIRECTION.UP;
                        else
                            nextDirection = DIRECTION.RIGHT;
                    }
                    break;
                case TOP_LEFT:
                    if (node.direction == DIRECTION.UP) {
                        if (node.marioColumn > 0)
                            nextDirection = DIRECTION.LEFT;
                        else
                            nextDirection = DIRECTION.UP;
                    } else {
                        if (node.marioRow > 0)
                            nextDirection = DIRECTION.UP;
                        else
                            nextDirection = DIRECTION.LEFT;
                    }
                    break;
                default:
                    nextDirection = DIRECTION.UP;
                    System.out.println("Invalid corner!");
                    System.exit(-1);
            }
            
            int nextMarioRow = node.marioRow;
            int nextMarioColumn = node.marioColumn;
            switch (nextDirection) {
                case UP:
                    nextMarioRow--;
                    break;
                case DOWN:
                    nextMarioRow++;
                    break;
                case LEFT:
                    nextMarioColumn--;
                    break;
                case RIGHT:
                    nextMarioColumn++;
                    break;
                default:
                    System.out.println("Invalid direction!");
                    System.exit(-1);
            }
            
            stack.push(new Node(nextDirection, node.corner, nextMarioRow, nextMarioColumn, node));
        }
    }

    public static void main(String[] args) {
        Solver solver = new Solver();
    }
}
