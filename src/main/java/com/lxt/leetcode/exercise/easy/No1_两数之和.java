package com.lxt.leetcode.exercise.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 * <p>
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No1_两数之和 {

    /**
     * 1、遍历数组将数组存在hashmap中，key-数字值，value-index
     * 2、遍历map的key，判断target-key在map是否存在，如果存在就代表符合条件
     * <p>
     * 这我自己写的解法，还是看了标签提示的，复杂度2N，太蠢了，明明一次遍历就能做到。。
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (nums[i] + nums[i] == target) {
                    result[0] = map.get(nums[i]);
                    result[1] = i;
                    return result;
                }
            }
            map.put(nums[i], i);
        }
        try {
            map.keySet().forEach(num -> {
                if (map.containsKey(target - num)) {
                    result[0] = map.get(num);
                    result[1] = map.get(target - num);
                    throw new Error();
                }
            });
        } catch (Throwable e) {
        }
        return result;
    }

    /**
     * 题解写法，应该是时间效率最高了
     *
     * 直接遍历数组，判断target 减 当前数据num在map中是否存在
     *
     * 存在直接返回数据下标
     * 不存在将当前num写入map
     */
    public int[] twoSum01(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }
}
