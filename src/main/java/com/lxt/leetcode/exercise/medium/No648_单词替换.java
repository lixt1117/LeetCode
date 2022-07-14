package com.lxt.leetcode.exercise.medium;

import sun.reflect.generics.tree.VoidDescriptor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * 在英语中，我们有一个叫做 词根(root) 的概念，可以词根后面添加其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
 * <p>
 * 现在，给定一个由许多词根组成的词典 dictionary 和一个用空格分隔单词形成的句子 sentence。你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。
 * <p>
 * 你需要输出替换之后的句子。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * 输出："the cat was rat by the bat"
 * 示例 2：
 * <p>
 * 输入：dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
 * 输出："a a b c"
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] 仅由小写字母组成。
 * 1 <= sentence.length <= 10^6
 * sentence 仅由小写字母和空格组成。
 * sentence 中单词的总量在范围 [1, 1000] 内。
 * sentence 中每个单词的长度在范围 [1, 1000] 内。
 * sentence 中单词之间由一个空格隔开。
 * sentence 没有前导或尾随空格。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/replace-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No648_单词替换 {

    /**
     * 自己的写法：
     * 1、先对dictionary进行排序，长度小的排前面
     * 2、遍历sentence的单词，从长度最小的dictionary进行匹配
     */
    public String replaceWords(List<String> dictionary, String sentence) {
        dictionary.sort(Comparator.comparingInt(String::length));

        String[] strings = sentence.split(" ");
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < dictionary.size(); j++) {
                if (strings[i].startsWith(dictionary.get(j))) {
                    strings[i] = dictionary.get(j);
                    break;
                }
            }
        }
        return String.join(" ", strings);
    }

    /**
     * 通过字典树实现，字典树介绍：https://segmentfault.com/a/1190000040801084、https://zhuanlan.zhihu.com/p/28891541
     */
    public String replaceWords01(List<String> dictionary, String sentence) {
        Trie root = new Trie();
        //将字典list存入字典树
        dictionary.forEach(root::insert);
        String[] strings = sentence.split(" ");
        for (int i = 0; i < strings.length; i++) {
            String prefix = root.findShortestPrefix(strings[i]);
            if (Objects.nonNull(prefix)) {
                strings[i] = prefix;
            }
        }
        return String.join(" ", strings);
    }

    /**
     * 字典树
     */
    static class Trie {
        /**
         * 子节点
         */
        private final Trie[] nodes;

        /**
         * 结尾标志
         */
        private boolean endFlag;

        /**
         * 到达该节点的完整字符串
         */
        private String str;

        public Trie() {
            nodes = new Trie[26];
        }

        /**
         * 插入
         */
        public void insert(String str) {
            Trie trie = this;
            for (int i = 0; i < str.length(); i++) {
                Trie[] nodes = trie.nodes;
                int index = str.charAt(i) - 'a';
                if (nodes[index] == null) {
                    nodes[index] = new Trie();
                }
                trie = nodes[index];
            }
            trie.endFlag = true;
            trie.str = str;
        }

        /**
         * 最短前缀查找
         */
        public String findShortestPrefix(String str) {
            Trie trie = this;
            for (int i = 0; i < str.length(); i++) {
                Trie[] nodes = trie.nodes;
                int index = str.charAt(i) - 'a';
                if (nodes[index] != null) {
                    if (nodes[index].endFlag) {
                        return nodes[index].str;
                    }
                    trie = nodes[index];
                } else {
                    return null;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        No648_单词替换 no648_单词替换 = new No648_单词替换();
        List<String> dictionary = new ArrayList<>();
        dictionary.add("a");
        dictionary.add("aa");
        dictionary.add("aaa");
        dictionary.add("aaaa");
        String str = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa";
        System.out.println(no648_单词替换.replaceWords01(dictionary, str));
    }

}
