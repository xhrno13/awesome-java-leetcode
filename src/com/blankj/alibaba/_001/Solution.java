package com.blankj.alibaba._001;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Created by tyreke.xu on 2020-04-05.
 */
public class Solution {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static Set<String> TRADE_DAY_SET = new HashSet<>();
    private static final int TRADE_DAY_OVER_MARK = 17;

    public void init(List<String> tradeDayList) {
        //空检查
        if (tradeDayList == null || tradeDayList.size() == 0) {
            return;
        }

        //遍历初始化的日期集合 日期格式为yyyyMMdd
        //1、如果字符合法性检查失败 跳过单次循环，但是我个人觉得直接抛异常初始化失败更好，为了简单先跳过吧
        //2、如果判断是不是周末 and 不是节假日 添加进set集合
        for (String tradeDay : tradeDayList) {
            if (!isValidDateString(tradeDay)) {
                continue;
            }
            if (!isWeekend(tradeDay) && !isHoliday(tradeDay)) {
                TRADE_DAY_SET.add(tradeDay);
            }
        }

    }

    /**
     * 算法主逻辑
     * 1.假设当前日期为D；
     * 2.如果需要获取接下来的第5个交易日，那么如果把当前时间加上5天，也就是(D + 5)；
     * 3.这个(D + 5)不一定是我们的真实答案，这5天中间有周末的同时可能还有国家规定的一些节日假期；
     * 4.这时我们需要找出这中间假期的天数M1，然后算出(D + 5 + M1)；
     * 5.但是从(D + 5)到(D + 5 + M1)中又有可能存在假期，所以需要再次找出假期天数M2……
     * 最后，当(D + 5 + M1 + … + Mn)到(D + 5 + M1 + … + Mn + Mn1)之间的假期天数Mn1为零时，(D + 5 + M1 + … + Mn + Mn1)即为结果。
     */
    public String getTradeDay(Date date, int offsetDays) {
        //判断是否交易时间超过当天收盘时间
        //为真 依据offsetDays的正负性做加1 or 减1操作
        if (isNextTradeDay(date)) {
            if (offsetDays > 0) {
                offsetDays++;
            } else {
                offsetDays--;
            }
        }

        //取绝对值当作循环条件
        int cnt = Math.abs(offsetDays);
        Date tradingDate = date;
        while (cnt > 0) {
            tradingDate = addDay(date, offsetDays);
            cnt = getVacationNum(date, tradingDate);
            date = tradingDate;
            offsetDays = offsetDays > 0 ? cnt : -cnt;
        }
        return formatter.format(tradingDate.toInstant());
    }

    /**
     * 获取一个时间段内的假期数量
     */
    private int getVacationNum(Date date, Date tradingDate) {
        LocalDate left = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate right = tradingDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int offset;
        LocalDate start;
        //处理区间，获取差异天数以及后续迭代的起始日期
        if (right.isAfter(left)) {
            offset = Period.between(left, right).getDays();
            start = left;
        } else {
            offset = Period.between(right, left).getDays();
            start = right;
        }

        offset++;
        int count = 0;
        while (offset > 0) {
            if (!TRADE_DAY_SET.contains(start.format(formatter))) {
                count++;
            }
            offset--;
            start = start.plusDays(1);

        }
        return count;
    }

    private Date addDay(Date myDate, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.DAY_OF_MONTH, amount);
        return cal.getTime();
    }

    /**
     * 判断传入时间是否超过交易截止时间17点整
     * e.g. 20160701 16:55:00 -> false
     * e.g. 20160701 17:05:00 -> true
     */
    private boolean isNextTradeDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY) >= TRADE_DAY_OVER_MARK;

    }

    /**
     * 检查date string合法性，利用jdk的原生DateTimeParseException 做解析检查
     */
    private boolean isValidDateString(String date) {
        try {
            LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException ex) {
            return false;
        }
        return true;

    }

    /**
     * 检查是否为周六日
     */
    private boolean isWeekend(String date) {
        LocalDateTime now = LocalDateTime.parse(date, formatter);
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;

    }

    /**
     * 检查是否为节假日
     */
    private boolean isHoliday(String date) {
        //todo, 每年的节假日可以用db或者配置中心维护
        //如果存在一个节假日db 这个方法的逻辑是去db里查看某个日期存在判断这个日期是否为节假日
        return true;
    }
}
