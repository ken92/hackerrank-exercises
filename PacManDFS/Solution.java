// https://www.hackerrank.com/challenges/pacman-dfs/submissions/game/21886837

import java.io.*;
import java.util.*;

public class Solution {
    private static enum MOVE_DIRECTION {UP, DOWN, LEFT, RIGHT}
    private static boolean debug = false;
    private static void debug(String str) {
        if (debug)
            System.out.println(str);
    }
    
    private static class Node {
        public MOVE_DIRECTION moveDirection = null;
        public Node parent = null;
        public int pacmanX = -1;
        public int pacmanY = -1;
        public Node(MOVE_DIRECTION moveDirection, int pacmanX, int pacmanY, Node parent) {
            this.moveDirection = moveDirection;
            this.pacmanX = pacmanX;
            this.pacmanY = pacmanY;
            this.parent = parent;
        }
    }
    
    private static class DFSSolver {
        private Stack<Node> nodeStack;
        private String[] map;
        private int rows, columns;
        private HashMap<String, Integer> expanded;
        private StringBuilder builder;
        private int exploredCount = 0;
        
        public DFSSolver() {}
        
        public void start() {
            Scanner scan = new Scanner(System.in);

            int pacmanX = -1;
            int pacmanY = -1;
            {
                String pacmanPosition = scan.nextLine();
                String[] pacmanPositionSplit = pacmanPosition.split(" ");
                pacmanX = Integer.parseInt(pacmanPositionSplit[1].trim());
                pacmanY = Integer.parseInt(pacmanPositionSplit[0].trim());
                if (pacmanX == -1 || pacmanY == -1) {
                    System.out.println("Could not parse pacman position!");
                    return;
                }
            }

            int foodX = -1;
            int foodY = -1;
            {
                String foodPosition = scan.nextLine();
                String[] foodPositionSplit = foodPosition.split(" ");
                foodX = Integer.parseInt(foodPositionSplit[1].trim());
                foodY = Integer.parseInt(foodPositionSplit[0].trim());
                if (foodX == -1 || foodY == -1) {
                    System.out.println("Could not parse food position!");
                    return;
                }
            }

            rows = -1;
            columns = -1;
            {
                String rowsColumns = scan.nextLine();
                String[] rowsColumnsSplit = rowsColumns.split(" ");
                rows = Integer.parseInt(rowsColumnsSplit[0].trim());
                columns = Integer.parseInt(rowsColumnsSplit[1].trim());
                if (rows == -1 || columns == -1) {
                    System.out.println("Could not parse rows and columns!");
                    return;
                }
            }
            
            debug("rows: "+rows+"   columns: "+columns);
            
            map = new String[rows];
            for (int row=0; row<rows; row++) {
                map[row] = scan.nextLine().trim();
                debug("  row "+row+": "+map[row]);
            }
            scan.close();
            
            expanded = new HashMap<String, Integer>();
            builder = new StringBuilder();
            
            nodeStack = new Stack<Node>();
            nodeStack.push(new Node(null, pacmanX, pacmanY, null));
            solveIteratively(nodeStack, foodX, foodY);
        }
        
        private void solveIteratively(Stack<Node> stack, int foodX, int foodY) {
            while(!stack.empty()) {
                Node node = stack.pop();
                exploredCount++;
                builder.append(node.pacmanY + " " + node.pacmanX + "\n");
                debug("Exploring "+node.pacmanY+" "+node.pacmanX);
                if (node.pacmanX == foodX && node.pacmanY == foodY) {
                    printSolution(node);
                    return;
                }

                if (expandNode(stack, node, foodX, foodY)) {
                    debug("    found solution!!");
                    return;
                }
            }

            System.out.println("No solution found!");
        }

        private void printSolution(Node node) {
            builder.insert(0, exploredCount + "\n");
            System.out.print(builder.toString());
            builder.delete(0, builder.length());
            
            int count = -1;
            while (node != null) {
                builder.insert(0, node.pacmanY + " " + node.pacmanX + "\n");
                count++;
                node = node.parent;
            }
            builder.insert(0, count + "\n");
            System.out.println(builder.toString());
        }

        private boolean expandNode(Stack<Node> stack, Node node, int foodX, int foodY) {
            debug("Expanding node pacmanX, pacmanY: "+node.pacmanX+", "+node.pacmanY);
            
            // up
            if (node.pacmanY - 1 >= 0 && expanded.get((node.pacmanY - 1) + " " + node.pacmanX) == null) {
                char up = map[node.pacmanY - 1].charAt(node.pacmanX);
                if (up != '%') {
                    Node newNode = new Node(MOVE_DIRECTION.UP, node.pacmanX, node.pacmanY - 1, node);
                    if (processNewNode(stack, newNode, foodX, foodY))
                        return true;
                }
            }

            // left
            if (node.pacmanX - 1 >= 0 && expanded.get(node.pacmanY + " " + (node.pacmanX - 1)) == null) {
                char left = map[node.pacmanY].charAt(node.pacmanX - 1);
                if (left != '%') {
                    Node newNode = new Node(MOVE_DIRECTION.LEFT, node.pacmanX - 1, node.pacmanY, node);
                    if (processNewNode(stack, newNode, foodX, foodY))
                        return true;
                }
            }

            // right
            if (node.pacmanX + 1 < columns && expanded.get(node.pacmanY + " " + (node.pacmanX + 1)) == null) {
                char right = map[node.pacmanY].charAt(node.pacmanX + 1);
                if (right != '%') {
                    Node newNode = new Node(MOVE_DIRECTION.RIGHT, node.pacmanX + 1, node.pacmanY, node);
                    if (processNewNode(stack, newNode, foodX, foodY))
                        return true;
                }
            }

            // down
            if (node.pacmanY + 1 < rows && expanded.get((node.pacmanY + 1) + " " + node.pacmanX) == null) {
                char down = map[node.pacmanY + 1].charAt(node.pacmanX);
                if (down != '%') {
                    Node newNode = new Node(MOVE_DIRECTION.DOWN, node.pacmanX, node.pacmanY + 1, node);
                    if (processNewNode(stack, newNode, foodX, foodY))
                        return true;
                }
            }
            
            return false;
        }
        
        private boolean processNewNode(Stack<Node> stack, Node newNode, int foodX, int foodY) {
            debug("  Push "+newNode.moveDirection+" node "+newNode.pacmanX+", "+newNode.pacmanY);
            debug("    foodX, foodY: "+foodX+", "+foodY);
            if (newNode.pacmanX == foodX && newNode.pacmanY == foodY) {
                debug("    found solution!");
                builder.append(newNode.pacmanY + " " + newNode.pacmanX + "\n");
                exploredCount++;
                printSolution(newNode);
                return true;
            }
            expanded.put(newNode.pacmanY + " " + newNode.pacmanX, 1);
            stack.push(newNode);
            return false;
        }
    }
    
    public static void main(String[] args) {
        DFSSolver solver = new DFSSolver();
        solver.start();
    }
}
