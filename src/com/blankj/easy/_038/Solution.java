package com.blankj.easy._038;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/05/03
 *     desc  :
 * </pre>
 */
public class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        return say(countAndSay(n - 1));
    }

    private String say(String s) {

        StringBuilder sb = new StringBuilder();
        int count = 1;
        char num = s.charAt(0);
        for (int i = 1; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == num) {
                count++;
            } else {
                sb.append(count);
                sb.append(num);
                count = 1;
                num = c;
            }
        }
        sb.append(count);
        sb.append(num);
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        for (int i = 1; i < 10; i++) {
            System.out.println(i + ":" + solution.countAndSay(i));
        }
    }
}
