package com.blankj.easy._088;

import java.util.Arrays;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/06/01
 *     desc  :
 * </pre>
 */
public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;
        while (p1 >= 0 && p2 >= 0) {
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = new int[10];
        for (int i = 0; i < 5; ++i) {
            nums1[i] = 2 * i;
        }
        int[] nums2 = new int[5];
        for (int i = 0; i < 5; ++i) {
            nums2[i] = 2 * i + 1;
        }
        solution.merge(nums1, 5, nums2, 5);
        System.out.println(Arrays.toString(nums1));
    }
}
