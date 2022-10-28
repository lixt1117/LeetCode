package com.lxt.leetcode.exercise.medium;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 * <p>
 * 输入：s = "cbbd"
 * 输出："bb"
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No5_最长回文串 {

    /**
     * 自己写的超烂写法，遍历两次字符串，一次找出aba这种回文，一次找出abba这种
     */
    public String longestPalindrome(String s) {
        String result = "";
        int len = s.length();
        for (int i = 0; i < len; i++) {
            String strOdd = getOddLongestPalindromeByIndex(s, i, len);
            String strEven = getEvenLongestPalindromeByIndex(s, i, len);
            if (result.length() < strOdd.length()) {
                result = strOdd;
            }
            if (result.length() < strEven.length()) {
                result = strEven;
            }
        }
        if (result.length() == 0) {
            return String.valueOf(s.charAt(0));
        }
        return result;
    }

    public static String getOddLongestPalindromeByIndex(String s, int index, int len) {
        String result = "";
        int left = index - 1;
        int right = index + 1;
        while (left >= 0 && right < len) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            } else {
                result = s.substring(left, right + 1);
                left--;
                right++;
            }
        }
        return result;
    }

    public String getEvenLongestPalindromeByIndex(String s, int index, int len) {
        String result = "";
        int left = index;
        int right = index + 1;
        while (left >= 0 && right < len) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            } else {
                result = s.substring(left, right + 1);
                left--;
                right++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        No5_最长回文串 no5_最长回文串 = new No5_最长回文串();
        System.out.println(no5_最长回文串.longestPalindrome("babad"));
    }

    /**
     * 中心扩散法
     */
    public String longestPalindrome1(String s) {

        if (s == null || s.length() == 0) {
            return "";
        }
        int strLen = s.length();
        int left = 0;
        int right = 0;
        int len = 1;
        int maxStart = 0;
        int maxLen = 0;

        for (int i = 0; i < strLen; i++) {
            left = i - 1;
            right = i + 1;
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                len++;
                left--;
            }
            while (right < strLen && s.charAt(right) == s.charAt(i)) {
                len++;
                right++;
            }
            while (left >= 0 && right < strLen && s.charAt(right) == s.charAt(left)) {
                len = len + 2;
                left--;
                right++;
            }
            if (len > maxLen) {
                maxLen = len;
                maxStart = left;
            }
            len = 1;
        }
        return s.substring(maxStart + 1, maxStart + maxLen + 1);

    }

    /**
     * 动态规划
     */
    public String longestPalindrome02(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int strLen = s.length();
        int maxStart = 0;  //最长回文串的起点
        int maxEnd = 0;    //最长回文串的终点
        int maxLen = 1;  //最长回文串的长度

        boolean[][] dp = new boolean[strLen][strLen];

        for (int r = 1; r < strLen; r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;
                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }

}
