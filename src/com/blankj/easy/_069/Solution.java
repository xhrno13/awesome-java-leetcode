package com.blankj.easy._069;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/05/08
 *     desc  :
 * </pre>
 */
public class Solution {
    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }

        double x0 = x;
        double x1 = (x0 + x / x0) / 2.0;
        while (Math.abs(x0 - x1) >= 1) {
            x0 = x1;
            x1 = (x0 + x / x0) / 2.0;
        }

        return (int) x1;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.mySqrt(10));
    }
}
