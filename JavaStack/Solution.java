// https://www.hackerrank.com/challenges/java-stack/submissions/code/22152964

import java.util.*;

class Solution {
   public static void main(String[] argh) {
      Scanner sc = new Scanner(System.in);
      while (sc.hasNext()) {
          String input = sc.nextLine();
          Stack<Character> stack = new Stack<Character>();
          for (int i=0; i<input.length(); i++) {
              Character c = input.charAt(i);
              if (c == '(' || c == '{' || c == '[')
                  stack.push(c);
              else if (c == ')' || c == '}' || c == ']') {
                  if (stack.empty()) {
                      // ensure that the stack is not empty
                      stack.push('e');
                      break;
                  }
                  Character stackCharacter = stack.pop();
                  boolean characterMatches = false;
                  if (c == ')' && stackCharacter == '(')
                      characterMatches = true;
                  else if (c == '}' && stackCharacter == '{')
                      characterMatches = true;
                  else if (c == ']' && stackCharacter == '[')
                      characterMatches = true;
                  
                  if (!characterMatches) {
                      // ensure that the stack will not be empty
                      stack.push(stackCharacter);
                      break;
                  }
              }
          }
          if (stack.empty())
              System.out.println("true");
          else
              System.out.println("false");
      }
      sc.close();
   }
}
