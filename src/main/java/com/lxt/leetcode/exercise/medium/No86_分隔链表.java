package com.lxt.leetcode.exercise.medium;

/**
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * <p>
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 * 示例 2：
 * <p>
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 *  
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目在范围 [0, 200] 内
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/partition-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No86_分隔链表 {

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
         * 双指针解决，一个指针p1指向做靠近头节点并且符合值大于等于x的节点的前一个节点，另一个指针p2指向遍历的当前节点
         */
        public static ListNode partition(ListNode head, int x) {

            if (head == null || head.next == null) {
                return head;
            }

            ListNode dump = new ListNode();
            dump.next = head;

            ListNode p1 = null;
            if (head.val >= x) {
                p1 = dump;
            }
            ListNode p2 = head;

            while (p2.next != null) {
                if (p2.next.val >= x) {
                    if (p1 == null) {
                        //定位p1
                        p1 = p2;
                    }
                } else {
                    if (p1 != null) {
                        //将当前节点放到p1后面
                        insertNode(p1, p2.next.val);
                        removeNode(p2);
                        p1 = p1.next;
                    }
                }
                p2 = p2.next;
            }
            return dump.next;
        }

        /**
         * 插入节点x到originNode后面
         *
         * @param originNode
         */
        public static void insertNode(ListNode originNode, int x) {
            if (originNode.next == null) {
                originNode.next = new ListNode(x);
            } else {
                ListNode tempNode = originNode.next;
                originNode.next = new ListNode(x);
                originNode.next.next = tempNode;
            }
        }

        /**
         * 清除originNode后面的一个节点
         *
         * @param originNode
         */
        public static void removeNode(ListNode originNode) {
            if (originNode.next != null) {
                originNode.next = originNode.next.next;
            }
        }


        public static void main(String[] args) {
            ListNode list1 = new ListNode(1);

            list1.next = new ListNode(4);
            list1.next.next = new ListNode(3);
            list1.next.next.next = new ListNode(2);
            list1.next.next.next.next = new ListNode(5);
            list1.next.next.next.next.next = new ListNode(2);

            ListNode listNode = partition(list1, 3);
            System.out.print("[");
            while (listNode != null) {
                System.out.print(listNode.val);
                System.out.print(",");
                listNode = listNode.next;
            }
            System.out.print("]");
        }
    }
}
