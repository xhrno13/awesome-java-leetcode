package com.blankj.easy._020;

import java.util.HashMap;
import java.util.Stack;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/04/28
 *     desc  :
 * </pre>
 */
public class Solution {

    private HashMap<String, String> map = new HashMap<>();

    public Solution() {
        this.map.put(")", "(");
        this.map.put("]", "[");
        this.map.put("}", "{");
    }


    public boolean isValid(String s) {
        Stack<String> stack = new Stack<>();
        if (s.length() == 0) {
            return true;
        }
        for (char c : s.toCharArray()) {

            if (map.containsKey(String.valueOf(c))) {

                String temp = stack.empty() ? "#" : stack.pop();
                if (!temp.equals(map.get(String.valueOf(c)))) {
                    return false;
                }
            } else {
                stack.push(String.valueOf(c));
            }

        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.isValid("()[]{}({[]})"));
//        System.out.println(solution.isValid("(])]"));
        System.out.println(solution.isValid("]"));
    }
}
