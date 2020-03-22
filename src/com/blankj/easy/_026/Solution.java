package com.blankj.easy._026;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/04/30
 *     desc  : 快慢指针
 * </pre>
 */
public class Solution {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }
        int idx = 0;
        for (int i = 1; i < len; ++i) {
            if (nums[idx] != nums[i]) {
                idx++;
                nums[idx] = nums[i];
            }
        }
        return idx + 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] data = new int[]{0, 1, 1, 2, 3, 3, 3};
        int len = solution.removeDuplicates(data);
        for (int i = 0; i < len; i++) {
            System.out.print(data[i] + (i == len - 1 ? "" : ", "));
        }
    }
}
