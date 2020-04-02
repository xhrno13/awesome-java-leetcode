package com.blankj.easy._504;

/**
 * Created by tyreke.xu on 2020-03-26.
 */
public class Solution {
    public static String convertToBase7(int num) {
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        if (num < 0) {
            num = -num;
            flag = true;
        }

        while (num / 7 != 0) {
            sb.append(num % 7);
            num /= 7;
        }
        sb.append(num);

        return flag ? "-" + sb.reverse().toString() : sb.reverse().toString();

    }

    private static String numberToChinese(int number) {
        String[] numbers = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] units = {"", "十", "百", "千", "万", "十", "百", "千", "亿", "十"};
        String sign = number < 0 ? "负" : "";
        if (number < 0) {
            number = -number;
        }
        StringBuilder result = new StringBuilder(sign);
        String string = String.valueOf(number);
        int n = string.length();
        char[] numberCharArray = string.toCharArray();
        for (int i = 0; i < n; i++) {
            int digNum = n - i; // 位数
            int num = numberCharArray[i] - '0';
            if (num != 0) {
                result.append(numbers[num]).append(units[digNum - 1]);
                continue;
            }

            if (digNum % 4 == 1) {
                result.append(units[digNum - 1]);
            } else {
                result.append(numbers[0]);
            }
        }
        return result.toString();
    }


    public static void main(String[] args) {
//        System.out.println(convertToBase7(100));
//        System.out.println(convertToBase7(-7));
//        System.out.println(convertToBase7(7));
        System.out.println(numberToChinese(223102301));
    }
}
