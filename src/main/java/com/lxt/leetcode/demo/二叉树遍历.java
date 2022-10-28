package com.lxt.leetcode.demo;

import java.util.LinkedList;

/**
 * @author lixiaotian10
 * @version 1.0
 * @date 2022/7/19 19:53
 */
public class 二叉树遍历 {

    /**
     * 先序遍历-根-左-右
     *
     * @param root:
     * @author lixiaotian10
     * @date 2022/7/19 19:56
     **/
    public static void preOrderTraverse(BinaryTree root) {
        if (root != null) {
            System.out.print(root.val + "  ");
            preOrderTraverse(root.left);
            preOrderTraverse(root.right);
        }
    }

    /**
     * 中序遍历-左-根-右
     *
     * @param root:
     * @author lixiaotian10
     * @date 2022/7/19 19:57
     **/
    public static void inOrderTraverse(BinaryTree root) {
        if (root != null) {
            inOrderTraverse(root.left);
            System.out.print(root.val + "  ");
            inOrderTraverse(root.right);
        }
    }

    /**
     * 后序遍历-左-右-根
     *
     * @param root:
     * @author lixiaotian10
     * @date 2022/7/19 19:59
     **/
    public static void postOrderTraverse(BinaryTree root) {
        if (root != null) {
            postOrderTraverse(root.left);
            postOrderTraverse(root.right);
            System.out.print(root.val + "  ");
        }
    }

    /**
     * 层次遍历，即按照树的层级一层层遍历
     *
     * @author lixiaotian10
     * @date 2022/7/19 21:05
     * @param root:
     **/
    public static void levelTraverse(BinaryTree root) {
        if (root == null) {
            return;
        }
        LinkedList<BinaryTree> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BinaryTree node = queue.poll();
            System.out.print(node.val+"  ");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    static class BinaryTree {
        int val;
        BinaryTree left;
        BinaryTree right;

        public BinaryTree(int val){
            this.val = val;
        }
    }

    /**
     *                 1
     *            2         3
     *         4    5          6
     *            7   8
     */
    public static void main(String[] args) {
        BinaryTree root = new BinaryTree(1);
        root.left = new BinaryTree(2);
        root.right = new BinaryTree(3);
        root.left.left = new BinaryTree(4);
        root.left.right = new BinaryTree(5);
        root.left.right.left = new BinaryTree(7);
        root.left.right.right = new BinaryTree(8);
        root.right.right = new BinaryTree(6);
        System.out.println("先序遍历:");
        preOrderTraverse(root);
        System.out.println("");
        System.out.println("中序遍历:");
        inOrderTraverse(root);
        System.out.println("");
        System.out.println("后序遍历:");
        postOrderTraverse(root);
        System.out.println("");
        System.out.println("层次遍历:");
        levelTraverse(root);
    }
}
