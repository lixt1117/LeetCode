package com.lxt.leetcode.exercise.medium;

import sun.reflect.generics.tree.VoidDescriptor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 * <p>
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 * <p>
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *  
 * <p>
 * 提示：
 * <p>
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No2_两数相加 {

    /**
     * 遍历l1,将l2的数值加到l1上，同时赋值给l2，如果数值和大于10，取个位赋值当前节点，并对下一位进一
     * 如果l1遍历完了，判断l2是否还有剩余，有的话直接加在l1上
     * 最后l1、l2谁长返回谁
     * <p>
     * 注意处理边界
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pointNode1 = l1;
        ListNode pointNode2 = l2;
        //返回标识，true返回l1，false返回l2
        boolean returnFlag = true;
        //进位值
        int carry = 0;
        pointNode1.val = pointNode1.val + pointNode2.val;
        if (pointNode1.val >= 10) {
            carry = 1;
            pointNode1.val -= 10;
        }
        pointNode2.val = pointNode1.val;
        for (; ; ) {
            if (pointNode1.next != null) {
                pointNode1 = pointNode1.next;
                if (pointNode2.next != null) {
                    pointNode2 = pointNode2.next;
                    //如果l1不为空，l2不为空，两者相加
                    pointNode1.val = pointNode1.val + pointNode2.val + carry;
                    if (pointNode1.val >= 10) {
                        carry = 1;
                        pointNode1.val -= 10;
                    } else {
                        carry = 0;
                    }
                    pointNode2.val = pointNode1.val;
                } else {
                    //如果l1不为空，l2为空，把l1的值加上进位值
                    pointNode1.val = pointNode1.val + carry;
                    if (pointNode1.val == 10) {
                        carry = 1;
                        pointNode1.val = 0;
                    } else {
                        carry = 0;
                    }
                }
            } else {
                if (pointNode2.next != null) {
                    pointNode2 = pointNode2.next;
                    returnFlag = false;
                    //如果l1 null,l2不为空，将把l2的值加上进位值
                    pointNode2.val = pointNode2.val + carry;
                    if (pointNode2.val == 10) {
                        carry = 1;
                        pointNode2.val = 0;
                    } else {
                        carry = 0;
                    }
                } else {
                    //如果l1、l2都为null。判断进位值tensPlace是否为1
                    if (carry == 1) {
                        if (returnFlag) {
                            pointNode1.next = new ListNode(1);
                        } else {
                            pointNode2.next = new ListNode(1);
                        }
                    }
                    break;
                }
            }
        }
        if (returnFlag) {
            return l1;
        }
        return l2;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        void print() {
            ListNode listNode = this;
            System.out.print("[ ");
            do {
                System.out.print(listNode.val);
                System.out.print(" ");
                listNode = listNode.next;
            } while (listNode != null);
            System.out.println("]");
        }
    }

    /**
     * 题解做法，思路和我写的是一样的，
     * 只不过我为了不额外创建新的链表，写的复杂了写，但是时间空间复杂度没有区别
     * 确实新建一个链表返回比较好
     */
    public ListNode addTwoNumbers01(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;

            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);

            cur = cur.next;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode();
        l1.val = 2;
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode();
        l2.val = 5;
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        No2_两数相加 no2_两数相加 = new No2_两数相加();
        no2_两数相加.addTwoNumbers(l1, l2).print();
    }
}
