package com.lxt.leetcode.exercise.easy;

import static com.lxt.leetcode.exercise.easy.No21_合并两个有序链表.Solution.mergeTwoLists2;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 * <p>
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *  
 * <p>
 * 提示：
 * <p>
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No21_合并两个有序链表 {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    static class Solution {

        /**
         * 我的思路：
         * 1、将链表1逐个像链表2插入
         * 2、如果链表1节点小于等于链表2节点，则往前插入
         */
        public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if (list1 == null) {
                return list2;
            }
            if (list2 == null) {
                return list1;
            }

            ListNode noneNode = new ListNode();
            noneNode.next = list2;
            ListNode point1 = list1;
            ListNode point2 = list2;
            ListNode preNode = noneNode;
            while (true) {
                if (point1 == null) {
                    break;
                }
                if (point2 == null) {
                    preNode.next = point1;
                    break;
                }
                if (point1.val < point2.val) {
                    //如果链表1节点值小于等于链表2节点值
                    preNode.next = new ListNode();
                    preNode.next.val = point1.val;
                    preNode.next.next = point2;

                    point1 = point1.next;
                    preNode = preNode.next;
                } else {
                    //如果链表1节点值大于链表2节点值
                    point2 = point2.next;
                    preNode = preNode.next;
                }

            }
            return noneNode.next;
        }


        /**
         * 拉链思路：
         * 1、新建一个虚拟头节点，为合并链表l3的头
         * 2、遍历比较l1和l1的节点值，将值较小的节点放在l3的后面
         */
        public static ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
            if (list1 == null) {
                return list2;
            }
            if (list2 == null) {
                return list1;
            }

            ListNode headNode = new ListNode();
            ListNode point = headNode;
            while (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    point.next = new ListNode();
                    point.next.val = list1.val;

                    point = point.next;
                    list1 = list1.next;
                } else {
                    point.next = new ListNode();
                    point.next.val = list2.val;

                    point = point.next;
                    list2 = list2.next;
                }
            }

            if (list1 != null) {
                point.next = list1;
            }
            if (list2 != null) {
                point.next = list2;
            }
            return headNode.next;
        }

        /**
         * 递归思路:
         */
        public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            } else if (l2 == null) {
                return l1;
            } else if (l1.val < l2.val) {
                l1.next = mergeTwoLists2(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists2(l1, l2.next);
                return l2;
            }
        }
    }


    public static void main(String[] args) {
        ListNode list1 = new ListNode();
        ListNode list2 = new ListNode();

        list1.val = 1;
        list1.next = new ListNode();
        list1.next.val = 2;
        list1.next.next = new ListNode();
        list1.next.next.val = 4;

        list2.val = 1;
        list2.next = new ListNode();
        list2.next.val = 3;
        list2.next.next = new ListNode();
        list2.next.next.val = 4;

//        list1.val = 2;
//        list2.val = 1;

        ListNode listNode = mergeTwoLists2(list1, list2);
        System.out.print("[");
        while (listNode != null) {
            System.out.print(listNode.val);
            System.out.print(",");
            listNode = listNode.next;
        }
        System.out.print("]");
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
    }
}
