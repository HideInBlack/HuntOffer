package codeFlowIdea;

import java.util.*;

/**
 * codeFlowIdea 代码随想录学习记录 time：2023年10月19日18:02:16 ->
 * author：董政宇
 * 第三部分 哈希部分：MyHash
 */
public class MyHash {


    /**
     * √ (2)有效的字母异位词 242. time：2023年10月19日18:10:43 -> 2023年10月19日18:28:34
     * 我的思路：看到要统计次数的题目 直接想到用hashmap！
     * 重点笔记：重点关注 Integer 在-128到127之外 == 不相等！
     */
    public boolean isAnagram(String s, String t) {
        //特殊条件在开始进行判断 省时间
        if (s.length() != t.length()){
            return false;
        }
        Map<Character, Integer> mapS = new HashMap<>();
        Map<Character, Integer> mapT = new HashMap<>();
        for (int i = 0; i < s.length(); i++){
            //如果有就在基础值上加一，如果没有则就设置其默认为0+1=1
            mapS.put(s.charAt(i), mapS.getOrDefault(s.charAt(i), 0) + 1);
            mapT.put(t.charAt(i), mapT.getOrDefault(t.charAt(i), 0) + 1);
        }
        for (int i = 0; i < s.length(); i++){
            if (!mapS.get(s.charAt(i)).equals(mapT.get(s.charAt(i)))){
                return false;
            }
        }

        return true;
    }
    //×（2）-（1）字母异位词分组 49. time：2023年10月19日19:30:49 ->
    //我的思路：暴力解法 两层for循环 两两比较 + 利用上面的有效的字母异位词。
    public List<List<String>> groupAnagrams1(String[] strs) {
        return null;
    }
    //（2）-（1）字母异位词分组 time:2023年10月19日20:29:38 -> 2023年10月19日20:41:50
    //首先思路：使用的是排序的字符串比较【即先把字符串分成char数组，然后排序，排序后再合成字符串】
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++){
            //首先字符串拆成char数组进行排序 排序之后再进行合并成字符串
            char[] str =strs[i].toCharArray();
            Arrays.sort(str);
            String key = new String(str);
            //根据排序后的字符串在map中取值，没有则new 有则取出来
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(strs[i]);
            map.put(key, list);
        }
        List<List<String>> result = new ArrayList<>(map.values());
        return result;
    }

    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {
        MyHash myHash = new MyHash();



        //测试 (2)有效的字母异位词
//        System.out.println(myHash.isAnagram("rat", "car"));

    }
}
