package com.blankj.easy._167;

import java.util.Arrays;

/**
 * Created by tyreke.xu on 2020-03-22.
 * 双指针思想
 */
public class Solution {
    private static int[] twoSum(int[] numbers, int target) {
        int[] idx = new int[]{-1, -1};
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return idx;
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{2, 7, 11, 15};
        System.out.println(Arrays.toString(twoSum(numbers, 19)));
    }
}
