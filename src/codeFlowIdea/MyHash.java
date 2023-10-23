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
     * ×超时 (2)-(1) 找到字符串中所有字母异位词 438. time：2023年10月23日18:33:58 -> 2023年10月23日19:08:17
     * 我的思路：双指针 + 滑动窗口 (异位词只关注字母的个数是否相同，而不关注字母的顺序)
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()){
            return result;
        }
        int left = 0; int right = p.length() - 1;
        Map<Character, Integer> map = new HashMap<>();
        //先统计所给p中的个数
        for (int i = 0; i < p.length(); i++){
            map.put(p.charAt(i), map.getOrDefault(p.charAt(i), 0) + 1);
        }
        //System.out.println(map.toString());
        while (right<s.length()){
            Map<Character, Integer> temp = new HashMap<>();
            temp.putAll(map);
            //遍历窗口内部
            for (int i = left; i <= right; i++){
                if (!temp.containsKey(s.charAt(i))){
                    break;
                }
                temp.put(s.charAt(i), temp.getOrDefault(s.charAt(i), 0) - 1);
                if (temp.get(s.charAt(i)).equals(0)){
                    temp.remove(s.charAt(i));
                }
            }
            //判断是否为空 为空时是字母异位词
            if (temp.isEmpty()){
                result.add(left);
            }
            left++;
            right++;
        }
        return result;
    }
    // √（相同的思想使用数组尝试一下）上面的方法是使用了hashmap超时了 下面使用一下数组 time：2023年10月23日19:52:48 -> 2023年10月23日20:09:25
    //重点笔记：数组快在什么地方呢？快在其可以快速判断两个数组是否是相等值的！ + 双指针+滑动固定窗口即可！
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()){
            return result;
        }
        int left = 0; int right = p.length() - 1;
        int arrayP[] = new int[26];
        for (int i = 0; i < p.length(); i++){
            arrayP[p.charAt(i)-'a']++;
        }
        while (right < s.length()){
            int arrayS[] = new int[26];
            for (int i = left; i <= right; i++){
                arrayS[s.charAt(i)-'a']++;
            }
            //判断两个数组的数值是否是完全相等的
            if (Arrays.equals(arrayS, arrayP)){
                result.add(left);
            }

            left++;
            right++;
        }
        return result;
    }

    /**
     * √（3）两个数组的交集 349. time： 2023年10月23日20:23:53 -> 2023年10月23日20:37:05
     * 我的思路：需要用到交集 并且交集每个元素都是唯一的 想到可以使用hashSet试一试 判断个包含不包含
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        //先把唯一不重复集合给求出来
        for (int i = 0; i < nums1.length; i++){
            if (!set.contains(nums1[i])){
                set.add(nums1[i]);
            }
        }
        //再进行判断 nums2中是否存在
        for (int i = 0; i < nums2.length; i++){
            if (set.contains(nums2[i])){
                list.add(nums2[i]);
                set.remove(nums2[i]);
            }
        }
        //遍历list赋值给数组返回
        int[] result = new int[list.size()];
        int i = 0;
        for (int element : list){
            result[i++] = element;
        }

        //Java8 api把list转化为int[]数组问题
        return result;
    }

    // √（3.1）两个数组的交集 II 350. time：2023年10月23日21:52:47 -> 2023年10月23日22:14:30
    //我的思路：这个涉及到出现的个数，要用map来解决【把两个集合的个数全都统计了，然后进行比较都有的key则是交集，并且谁个数小选谁的个数！】
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int i = 0; i < nums1.length; i++){
            //统计个数
            map1.put(nums1[i], map1.getOrDefault(nums1[i], 0) + 1);
        }
        for (int i = 0; i < nums2.length; i++){
            //统计个数
            map2.put(nums2[i], map2.getOrDefault(nums2[i], 0) + 1);
        }
        //那map1的key去map2的key做比较
        List<Integer> list = new ArrayList<>();
        for (int key : map2.keySet()){
            if (map1.containsKey(key)){//如果都有则是交集 取最小的个数
                int min = 0;
                if (map1.get(key) < map2.get(key)){
                    min = map1.get(key);
                }else {
                    min = map2.get(key);
                }
                for (int j = 1; j <= min; j++){
                    list.add(key);
                }
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }



    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {
        MyHash myHash = new MyHash();

//        Set<Integer> set = new HashSet<>();
//        set.add(1);set.add(2);set.add(3);
//        for (int element : set){
//            System.out.println(element);
//        }

        //Map遍历
//        Map<Integer, Integer> map = new HashMap<>();
//        map.put(0, 0);  map.put(1, 100); map.put(2, 200);
//        for (int element : map.keySet()){
//            System.out.println(element + " : " + map.get(element));
//        }

        //测试 (2)-(1) 找到字符串中所有字母异位词 438.
//        System.out.println(myHash.findAnagrams("baa", "aa").toString());

        //测试 (2)有效的字母异位词
//        System.out.println(myHash.isAnagram("rat", "car"));

    }
}
