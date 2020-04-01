package com.blankj.medium._912;

/**
 * Created by tyreke.xu on 2020-03-31.
 */
public class Solution {

    /**
     * 选择排序
     */
//    private int[] sortArray(int[] nums) {
//        int len = nums.length;
//        for (int i = 0; i < len - 1; ++i) {
//            int minIdx = i;
//            for (int j = i + 1; j < len; ++j) {
//                if (nums[j] < nums[minIdx]) {
//                    minIdx = j;
//                }
//            }
//            swap(nums, minIdx, i);
//        }
//        return nums;
//    }

    /**
     * 插入排序
     */
//    private int[] sortArray(int[] nums) {
//        int len = nums.length;
//        for (int i = 1; i < len; ++i) {
//            int j = i;
//            int temp = nums[i];
//            while (j > 0 && temp < nums[j - 1]) {
//                nums[j] = nums[j - 1];
//                j--;
//            }
//            nums[j] = temp;
//        }
//        return nums;
//    }

    /**
     * 快排
     */
    private static int[] sortArray(int[] nums) {
        int len = nums.length;
        quickSort(nums, 0, len - 1);
        return nums;
    }

    private static void quickSort(int[] nums, int left, int right) {
        if (left > right) {
            return;
        }
        int partition = divide(nums, left, right);
        quickSort(nums, left, partition - 1);
        quickSort(nums, partition + 1, right);
    }

    private static int divide(int[] nums, int left, int right) {
        int pivot = nums[right];
        while (left < right) {
            while (left < right && nums[left] <= pivot) {
                left++;
            }

            if (left < right) {
                swap(nums, left, right);
                right--;
            }

            while (left < right && pivot <= nums[right]) {
                right--;
            }
            if (left < right) {
                swap(nums, left, right);
                left++;
            }
        }
        return right;
    }


    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 7, 2, 9, 1, 4, 6, 8, 10, 5};
        sortArray(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
