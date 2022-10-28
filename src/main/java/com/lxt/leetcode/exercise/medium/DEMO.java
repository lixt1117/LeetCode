package com.lxt.leetcode.exercise.medium;

import com.lxt.leetcode.demo.二叉树遍历;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.*;

/**
 * @author lixiaotian10
 * @version 1.0
 * @date 2022/8/13 16:51
 */
public class DEMO {

    /**
     * 字典树
     */
    static class Trie {
        /**
         * 子节点
         */
        private final Trie[] nodes;

        /**
         * 是否末级
         */
        private boolean endFlag;

        /**
         * 菜单字符串-id map
         */
        private Map<String, Integer> map;

        private Integer currentId = 1;

        /**
         * 当前菜单字符串
         */
        private String str;

        public Trie() {
            nodes = new Trie[32];
            map = new HashMap<>();
        }

        public Trie(String str) {
            nodes = new Trie[32];
            this.str = str;
            map = new HashMap<>();
        }

        /**
         * 插入
         */
        public void insert(String str) {
            Trie trie = this;
            String[] strings = str.split("-");
            for (String e : strings) {
                if (!map.containsKey(e)) {
                    map.put(e, ++currentId);
                }
                Trie[] nodes = trie.nodes;
                int index = map.get(e);
                if (nodes[index] == null) {
                    Trie newTrie = new Trie(e);
                    nodes[index] = newTrie;
                }
                trie = nodes[index];
            }
            trie.endFlag = true;
        }
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("一级菜单1-二级菜单2-三级菜单3-四级菜单3");
        strings.add("一级菜单2-二级菜单1-三级菜单1-四级菜单4");
        strings.add("一级菜单1-二级菜单2-三级菜单2-四级菜单1");
        strings.add("一级菜单3-二级菜单2-三级菜单1-四级菜单4");
        strings.add("一级菜单1-二级菜单3-三级菜单1-四级菜单2");
        strings.add("一级菜单4-二级菜单2-三级菜单3-四级菜单4");
        strings.add("一级菜单1-二级菜单1-三级菜单1-四级菜单4");
        strings.add("一级菜单1-二级菜单2-三级菜单1-四级菜单3");
        strings.add("一级菜单2-二级菜单1-三级菜单1-四级菜单4");
        strings.add("一级菜单2-二级菜单2-三级菜单2-四级菜单3");
        strings.add("一级菜单1-二级菜单2-三级菜单1-四级菜单4");
        strings.add("一级菜单3-二级菜单4-三级菜单1-四级菜单4");
        strings.add("一级菜单1-二级菜单2-三级菜单3-四级菜单4");
        strings.add("一级菜单4-二级菜单4-三级菜单1-四级菜单2");
        strings.add("一级菜单1-二级菜单2-三级菜单4-四级菜单4");
        strings.add("一级菜单4-二级菜单3-三级菜单1-四级菜单1");
        Trie trie = new Trie();
        strings.forEach(trie::insert);
        System.out.println("");
    }
}
