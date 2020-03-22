package com.blankj.easy._066;

import java.util.Arrays;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/05/06
 *     desc  :
 * </pre>
 */
public class Solution {
    public int[] plusOne(int[] digits) {

        int len = digits.length;
        for (int i = len - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] %= 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        digits = new int[len + 1];
        digits[0] = 1;
        return digits;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] digits = solution.plusOne(new int[]{4, 9, 9});
        System.out.println(Arrays.toString(digits));
    }
}
