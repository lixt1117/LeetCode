package com.lxt.leetcode.exercise.medium;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.util.function.IntFunction;

/**
 * 给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10的n次方。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出：91
 * 解释：答案应为除去 11、22、33、44、55、66、77、88、99 外，在 0 ≤ x < 100 范围内的所有数字。
 * 示例 2：
 * <p>
 * 输入：n = 0
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-numbers-with-unique-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No357_统计各位数字都不同的数字个数 {

    /**
     * 总结：
     * 枚举法YYDS哈哈哈
     * <p>
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38 MB
     * , 在所有 Java 提交中击败了
     * 53.29%
     * 的用户
     * 通过测试用例：
     * 9 / 9
     *
     * @param n
     * @return
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }
        if (n == 2) {
            return 91;
        }
        if (n == 3) {
            return 739;
        }
        if (n == 4) {
            return 5275;
        }
        if (n == 5) {
            return 32491;
        }
        if (n == 6) {
            return 168571;
        }
        if (n == 7) {
            return 712891;
        }
        return 2345851;
    }

    /**
     * 总结:
     * 我自己想出来的普通方案，先计算固定长度的各位置唯一数字的数量，在求和
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.2 MB
     * , 在所有 Java 提交中击败了
     * 34.90%
     * 的用户
     * 通过测试用例：
     * 9 / 9
     *
     * @param n
     * @return
     */
    public int countNumbersWithUniqueDigitsV1(int n) {
        if (n == 0) {
            return 1;
        }
        int count = 1;
        for (int i = 1; i <= n; i++) {
            count += countNumbersWithUniqueDigits4FixedLength(i);
        }
        return count;
    }

    /**
     * 统计固定长度的各位置唯一的数字数量,9*9*8*7...
     *
     * @param n
     * @return
     */
    public static int countNumbersWithUniqueDigits4FixedLength(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 9;
        }
        int count = 9;
        int num = 11 - n;
        do {
            count = count * num;
            num++;
        } while (num <= 9);
        return count;
    }

}
