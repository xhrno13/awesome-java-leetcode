package com.blankj.easy._136;

/**
 * Created by tyreke.xu on 2020-03-24.
 */
public class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}
