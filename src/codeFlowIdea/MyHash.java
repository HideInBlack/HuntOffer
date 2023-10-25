package codeFlowIdea;

import java.util.*;

/**
 * codeFlowIdea 代码随想录学习记录 time：2023年10月19日18:02:16 -> 2023年10月25日21:09:41
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
     * √（4）快乐数 202. time：2023年10月24日09:30:53 -> 2023年10月24日09:42:37
     * 我的思路：首先用哈希Set来存储结果 如果出现已经出现过的解 直接返回false 如果结果为1则返回为true
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        int temp = n;
        while (true){
            int sum = 0;
            while (temp != 0){
                sum = sum + (temp % 10)*(temp % 10);
                temp = temp / 10;
            }
            if (set.contains(sum)){
                return false;
            }
            if (sum == 1){
                return true;
            }
            set.add(sum);
            temp = sum;
        }
    }
    // √（4.1）各位相加 258. time：2023年10月24日09:44:55 -> 2023年10月24日09:54:12
    public int addDigits(int num) {
        while (num/10 != 0){//temp/10 == 0代表temp此时为个位数
            int sum = 0;
            int temp = num;
            //下面是各位相加过程
            while (temp != 0){
                sum = sum + temp % 10;
                temp = temp / 10;
            }
            num = sum;
        }
        return num;
    }
    //√（4.2）丑数 263. time：2023年10月24日09:55:06 ->2023年10月24日14:05:09
    //我的思路：从1到根号下n 进行遍历判断 如果n%i=0 则表明i是n的质因数 把所有的质因数都存到哈希Set中进行判断包含与否
    public boolean isUgly(int n) {
        if (n <= 0){
            return false;
        }
        Set<Integer> set = new HashSet<>();
        set.add(2); set.add(3); set.add(5);
        for (int i = 1; i <= (int) Math.sqrt(n); i++){
            //如果n%i=0 则表明i是其因数 因此下面还要判断一下其是不是质数
            if (n % i == 0){
                if (isPrimeNumber(i) && !set.contains(i) ){//首先其是因数、也是质数 并且还是不包含在2 3 5里面的 可以直接退出返回false
                    return false;
                }
                if (isPrimeNumber(n / i) && !set.contains(n / i)){//同上 只不过处理的是n相对于i的另一个因数
                    return false;
                }
            }
        }

        return true;
    }
    //判断一个数是不是质数【质数，又称素数，是指在大于1的自然数中，除了1和它本身以外不再有其他因数的自然数。】
    public boolean isPrimeNumber(int n) {
        //注意：1 不是质数！遇到1直接返回false
        if (n == 1){
            return false;
        }
        for (int i = 2; i <= (int) Math.sqrt(n); i++){
            //如果n%i=0 则表明i是其因数
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }
    //×【超出时间限制】（4.3）264. 丑数 II time：2023年10月24日14:24:28 -> 2023年10月24日14:35:15
    public int nthUglyNumber(int n) {
        int count = 0;
        int num = 1;
        while (true){
            if (isUgly(num)){
                count++;
            }
            if (count == n){
                return num;
            }
            num++;
        }
    }
    //方法二 丑数 判断是不是丑数
    //题解方法思想：若 n 是丑数，则 n 可以写成 n=2^a × 3^b × 5^c  可以对 n 反复除以 2,3,5，直到 n 不再包含质因数 2,3,5。若剩下的数等于 1，则说明 n 不包含其他质因数，是丑数；否则不是丑数。

    public boolean isUgly2(int n){
        if (n <= 0){
            return false;
        }
        int[] uglyGroup = {2, 3, 5};
        //绝妙的思想：先除2、3、5中的谁都行！一直都除干净 再判断最后是不是等于1
        for (int element : uglyGroup){
            while (n % element == 0){
                n = n / element;
            }
        }
        return n == 1;
    }
    //×【超出时间限制】（4.4）计算质数 204. time：2023年10月24日14:48:25 -> 2023年10月24日14:58:25
    public int countPrimes(int n) {
        if (n <= 1){
            return 0;
        }
        int count = 0;
        int num = 1;
        while (num < n){
            if (isPrime(num)){
                count++;
            }
            num++;
        }
        return count;
    }
    //判断一个数是不是质数
    public boolean isPrime(int n){
        if (n <= 1){
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++){
            if (n % i == 0){//如果有除了1和其本身之外的因数 则其不是质数
                return false;
            }
        }
        return true;
    }
    //√（4.4）题解方法一 计数质数 time：2023年10月24日15:49:13 -> 2023年10月24日16:09:27
    // 题解思路：申请数组 从第一个质数2出发去填充2*2 2*3 2*4.....等所有质数
    // 重点笔记：直接初始化数组，使用数组的赋值操作来。无需判断是否为质数，直接从最开始的2、3出发 从倍数出发去填补数组。
    public int countPrimes2(int n) {
        int count = 0;
        int[] array = new int[n];
        //全部初始化为1
        Arrays.fill(array, 1);
        for (int i = 2; i < n; i++){
            //如果此数为质数则直接进行++
            if (array[i] == 1){
                count++;
                //则此质数的倍数都为合数
                if ((long)i * i < n){
                    for (int j = i * i; j < n; j = j + i){//注意 j=j+i 代表着i*i、i*(i+1)、i*(i+2)...
                        //则令其为合数
                        array[j] = 0;
                    }
                }

            }
        }
        return count;
    }

    /**
     * √（5）1.两数之和 time：2023年10月24日18:30:26 -> 2023年10月24日18:41:01
     * 我的思路：双指针？暴力枚举？
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++){
            for (int j = i + 1; j < nums.length; j++){
                if (nums[i] + nums[j] == target){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }
    // √（5）方法二 两数之和 time：2023年10月24日18:46:46 -> 2023年10月24日18:51:25
    //使用哈希map 遍历一遍即可
    public int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            if (map.containsKey(target - nums[i])){
                result[0] = i;
                result[1] = map.get(target - nums[i]);
            }
            map.put(nums[i], i);
        }
        return result;
    }

    /**
     * ×【错误】（6）454. 四数相加 II time：2023年10月25日19:18:17 -> 2023年10月25日19:48:46
     * 此题数组为相互独立的四个数组，而三数之和和四数之和都是操作的一个数组不一样！所以此题不可以使用双指针【同一个数组时建议使用双指针】
     * 所以得此题题解思路是：(两两一组 再用map进行查询)i、j、k、l都是相互独立的 且题目只要求个数！操作性很大 可以使用map来做
     * √ 题解方法：2023年10月25日19:51:45 -> 2023年10月25日20:11:35
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int i = 0; i < nums1.length; i++){
            for (int j = 0; j < nums2.length; j++){
                //把数组1和数组2中的和存为key 其数量存为value
                map1.put(nums1[i] + nums2[j], map1.getOrDefault(nums1[i] + nums2[j], 0) + 1);
            }
        }
        for (int i = 0; i < nums3.length; i++){
            for (int j = 0; j < nums4.length; j++){
                //把数组3和数组4中的和也存为key 其数量存为value
                map2.put(nums3[i] + nums4[j], map2.getOrDefault(nums3[i] + nums4[j], 0) + 1);
            }
        }
        //再进行判断是否两组的key是否有加起来等于0的
        int count = 0;
        for (int key : map1.keySet()){
            if (map2.containsKey(-key)){
                //此时需要把map1数量和map2数量乘起来 因为你有2个 我有3个 我们一共可以组合2*3=6个
                count = count + map1.get(key) * map2.get(-key);
            }
        }
        return count;
    }
    // √ 方法二 （6）454. 四数相加 II 再遍历第二组时就进行统计
    public int fourSumCount2(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int i = 0; i < nums1.length; i++){
            for (int j = 0; j < nums2.length; j++){
                //把数组1和数组2中的和存为key 其数量存为value
                map1.put(nums1[i] + nums2[j], map1.getOrDefault(nums1[i] + nums2[j], 0) + 1);
            }
        }
        int count = 0;
        for (int i = 0; i < nums3.length; i++){
            for (int j = 0; j < nums4.length; j++){
                if (map1.containsKey(-(nums3[i] + nums4[j]))){
                    count = count + map1.get(-(nums3[i] + nums4[j]));
                }
            }
        }
        return count;
    }

    /**
     *  √（7）383. 赎金信 time：2023年10月24日19:03:51 -> 2023年10月24日19:16:40
     * 我的思路：先使用map统计字符个数 然后全包含的时候前者字符的个数要小于后者的个数
     * 关键操作：1.map统计字符的数量 2.增强for循环遍历map的key
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> mapRansomNote = new HashMap<>();
        Map<Character, Integer> mapMagazine = new HashMap<>();
        //统计个数
        for (int i = 0; i < ransomNote.length(); i++){
            mapRansomNote.put(ransomNote.charAt(i), mapRansomNote.getOrDefault(ransomNote.charAt(i), 0) + 1);
        }
        for (int i = 0; i < magazine.length(); i++){
            mapMagazine.put(magazine.charAt(i), mapMagazine.getOrDefault(magazine.charAt(i), 0) + 1);
        }
        //挨个取出前者的key 进行与后者比较
        for (char key : mapRansomNote.keySet()){
            //如果后者不包含 直接返回false
            if (!mapMagazine.containsKey(key)){
                return false;
            }else if (mapRansomNote.get(key) > mapMagazine.get(key)){//如果后者包含 但是前者的数量比后者多 直接false
                return false;
            }
        }
        return true;
    }
    // √【果然还是数组更快！】（7）方法二 383. 赎金信 数组方法 time：2023年10月24日19:20:54 -> 2023年10月24日19:25:45
    public boolean canConstruct2(String ransomNote, String magazine) {
        int[] arrayA = new int[26];
        int[] arrayB = new int[26];
        for (int i = 0; i < ransomNote.length(); i++){
            arrayA[ransomNote.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < magazine.length(); i++){
            arrayB[magazine.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < 26; i++){
            if (arrayA[i] > arrayB[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * ×【超出时间限制】（8）三数之和 15. time：2023年10月24日19:35:51 -> 2023年10月24日20:00:35
     * 我的思路：三层for循环、暴力解法、他只需要输出和为0的元组
     */
    public List<List<Integer>> threeSum(int[] nums) {
        //先对数组进行排序
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++){
            //在第一层的时候判断如果大于0 直接退出循环
            if (nums[i] > 0){
                break;
            }
            for (int j = i+1; j < nums.length; j++){
                if (nums[i] + nums[j]> 0){
                    break;
                }
                for (int k = j + 1; k < nums.length; k++){
                    if (nums[i] + nums[j] + nums[k] > 0){
                        break;
                    }
                    if (nums[i] + nums[j] + nums[k] == 0){
                        //由于先进行了排序所以数组已经有序了 必然是i<j<k
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        if (!result.contains(list)){
                            result.add(list);
                        }
                    }
                }
            }
        }
        return result;
    }
    //×【解答错误】（8）三数之和 方法二 哈希优化三层循环为双层循环 time：2023年10月25日10:24:33 -> 2023年10月25日11:50:54
    public List<List<Integer>> threeSum2(int[] nums) {
        //先对数组进行排序
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            //在第一层的时候判断如果大于0 直接退出循环
            if (nums[i] > 0) {
                break;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] > 0) {
                    break;
                }
                if (set.contains(-(nums[i] + nums[j]))){
                    //这里声明为Integer的！！！ 要在循环内部new 不然一直操作的是一个list
                    Integer[] tuple = new Integer[3];
                    tuple[0] = nums[i];
                    tuple[1] = nums[j];
                    tuple[2] = -(nums[i] + nums[j]);
                    Arrays.sort(tuple);
                    if (!result.contains(Arrays.asList(tuple))){
                        result.add(Arrays.asList(tuple));
                    }
                    set.remove(-(nums[i] + nums[j]));
                }else{
                    set.add(nums[j]);
                }


            }
        }
        return result;
    }
    //√（8）三数之和 方法三 题解方法 一层遍历 + 双指针 +去重操作 time：2023年10月25日13:25:26 -> 2023年10月25日14:00:27
    public List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        //先进行数组排序因为本方法要在数组有序的情况下进行
        Arrays.sort(nums);
        //先进行第一层的遍历固定i 同时定义left、right双指针从i的右边块中的最左边和最右边进行往中间遍历（此相当于第二层和第三层遍历）
        int left; int right;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] > 0){
                break;
            }
            //这里是在剪枝相同的i 但是一定要是 i 与 i-1比，而不能和i+1比
            if (i != 0 && nums[i] == nums[i-1]){
                continue;
            }
            left = i + 1;
            right = nums.length - 1;
            //下面开始移动双指针 直到i与j相遇才停止（这里是要遍历一整遍的！）
            while (left < right){
                if (nums[i] + nums[left] + nums[right] > 0){//三数之和大于0 则移动右指针 整体之和会变小
                    right--;
                }else if (nums[i] + nums[left] + nums[right] < 0){//三数之和小于0 则移动左指针 整体之和会变大
                    left++;
                }else {//三数之和等于0时！保存！
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    //此时去重left 和 right操作
                    while (right > left && nums[left] == nums[left + 1]){
                        left++;
                    }
                    while (right > left && nums[right] == nums[right-1]){
                        right--;
                    }
                    //上面只是移动到最后一个相同的 还是要再往下走一个
                    left++;
                    right--;
                }
            }
        }
        return result;
    }
    // √（8.1）16. 最接近的三数之和 time：2023年10月25日14:04:26 -> 2023年10月25日15:01:17
    // 我的思路：与上一题思路接近（只不过三数之和是求其和为0的tuple有很多， 而这里是求最接近的和就多少！）
    public int threeSumClosest(int[] nums, int target) {
        //上来先进行对数组进行排序
        Arrays.sort(nums);
        int result = -1;
        int sum;
        //最小值的设置有讲究！这里看到target < 10^4 就设置了1万 结果不行 因为还有nums[]的范围！所以要设置为10万！就足够啦
        int minDifference = 100000;
        //下面使用单循环 + 双指针进行找最优解
        int left; int right;
        for (int i = 0; i < nums.length; i++){
            left = i + 1;
            right = nums.length - 1;
//            //剪枝优化？
            if (i > 2 && nums[i-2] > 0 && (nums[i] + nums[i-1] + nums[i-2]) > target){
                break;
            }
            //移动双指针
            while (left < right){
                sum = nums[i] + nums[left] + nums[right];
                if(sum < target){
                    //计算最小差值
                    if (target - sum < minDifference ){
                        minDifference = target - sum;
                        result = sum;
                    }
                    left++;
                }else if (sum > target){
                    //计算最小差值
                    if (sum - target < minDifference){
                        minDifference = sum - target;
                        result = sum;
                    }
                    right--;
                }else {
                    //都等于0了 那必是最优解直接返回即可
                    return sum;
                }
            }

        }
        return result;
    }

    /**
     * √（9）四数之和 18. time：2023年10月25日18:10:03 -> 2023年10月25日18:38:04
     * 我的思路：两层for循环 + 双指针 + 去重操作？ 是的就是这样！
     * 注意：注意int的溢出 sum建议使用long！！！【不用剪枝优化也可以通过！下面再试一下剪枝优化√】
     * 通过剪枝优化节省了一半的时间： 30ms -> 15ms
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //上来先对数组进行排序
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int left; int right; long sum;
        out:
        for (int i = 0; i < nums.length; i++){
            //剪枝优化
            if ((long)nums[i] > 0 && (long)nums[i] > target){
                break;
            }
            //去重复a【这里一定是与前面的比进行去重 不然与后面的比会缺少解 但与前面的比不会】
            if (i != 0 && nums[i] == nums[i - 1]){
                continue;
            }
            for (int j = i + 1; j < nums.length; j++){
                //剪枝优化
                if ((long)nums[i] > 0 && (long)nums[j] > target){
                    break out;
                }
                //去重复b
                if (j != i + 1 && nums[j] == nums[j - 1]){
                    continue;
                }
                //这里就需要针对第三层、第四层循环使用双指针了
                left = j + 1;
                right = nums.length - 1;
                while (left < right){
                    //这里会溢出！请使用long类型
                    sum = (long)nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum < target){
                        left++;
                    } else if (sum > target) {
                        right--;
                    } else {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        result.add(list);
                        //去重复c、d 循环找到最后一个相同的数字
                        while (left < right && nums[left] == nums[left + 1]){
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]){
                            right--;
                        }
                        left++;
                        right--;
                    }
                }

            }
        }
        return result;
    }




    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {
        MyHash myHash = new MyHash();

        //哈希-完结打卡

        //测试 （9）四数之和
//        int nums[] = {1,0,-1,0,-2,2};
//        System.out.println(myHash.fourSum(nums, 0).toString());

//        int[] test1 = {-1,0,1,2,-1,-4};
//        myHash.threeSum(test1);

        //list链表的相关操作
//        List<List<Integer>> list = new ArrayList<>();
//        List<Integer> childList = new ArrayList<>();
//        childList.add(1);childList.add(2);childList.add(3);
//        //存储子链表
//        list.add(childList);
//        System.out.println(list);//[[1, 2, 3]]
//        //改变子链表的值
//        childList.set(0, 7);
//        childList.set(1, 7);
//        childList.set(2, 7);
//        System.out.println(list);//list存储的也跟着改变了：[[7, 7, 7]]

//        Integer test1 = 128;
//        Integer test2 = 128;
//        Integer test3 = 127;
//        Integer test4 = 127;
//        System.out.println(test1 == test2);
//        System.out.println(test1.equals(test2));
//        System.out.println(test3 == test4);
//        System.out.println(test3.equals(test4));

        //测试（4.4）
//        System.out.println(myHash.countPrimes2(499979));

//        System.out.println(499979 * 499979);
//        System.out.println((long) (499979 * 499979));
//        System.out.println((long) 499979 * 499979);
//        System.out.println((long) 499979 * (long)499979);

        //测试（4.3）丑数2
//        System.out.println(myHash.nthUglyNumber(10));


//        System.out.println("开方演示：" + Math.sqrt(9));
//        System.out.println("平方（n次方）演示：" + Math.pow(3, 2));//这里是3 的 2次方
//        System.out.println("取绝对值：" + Math.abs(-100));
//        System.out.println("取最大值：" + Math.max(3, 9));
//        System.out.println("取最小值：" + Math.min(3, 9));

        //测试 是否为质数
//        System.out.println(myHash.isPrimeNumber(1));
//        System.out.println(myHash.isPrimeNumber(2));
//        System.out.println(myHash.isPrimeNumber(3));
//        System.out.println(myHash.isPrimeNumber(5));
//        System.out.println(myHash.isPrimeNumber(9));

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
