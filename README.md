# HuntOffers

# **一.刷题间接经验**

1. DZY的算法笔记飞书版：[董政宇的算法笔记](https://ovxmsaoguz.feishu.cn/docx/PA2idKvjxolXkbxzzw8c1HCQnsb) 
2. DZY的刷题记录Github地址：https://github.com/HideInBlack/HuntOffer
3. 高频题目链接：[Leetcode各标签高频题目排序](https://ovxmsaoguz.feishu.cn/docx/RHAudENIUoMtLEx5ljncKIgCngf) 
4. 王显博的算法累计：[算法累计](https://kazjvwevgzj.feishu.cn/docx/T6Frdb96io5ZdtxUtCUc8hmBnKe) 
5. 阿犇的算法笔记：[李犇](https://lv1gasf7yw5.feishu.cn/docx/Gxu8dnUjJoLVGhxhMficclYFnib) 

> 先刷  ***剑指offer的题***  再刷 ***leetcode热题100题***  分类刷题（分类顺序 可以按照代码随想录顺序来） 先从常见的数据结构和常见的算法开始 action！
>
> 每个标签 大概要做10道题目
>
> LeetCode刷题顺序（土妹刷题经验）：
>
> 1. 跟着教程的章节来刷题；剑指offer和程序员面试经典
> 2. 按LeetCode分类中的频率从高到低刷，例如字符串、数组、二分查找、哈希表、链表、双指针、栈、树、深度优先搜索、广度优先搜索、回溯法、排序
> 3. 对于自己不会的题目，拓展一道题的相似题目
> 4. Top100或者精选200
> 5. 参加LeetCode周赛或者双周赛，基本做出前三道，有时能做出第四道，以中等难度为主，简单难度热身，弄懂Top100或者精选200中困难难度
> 6. 定期复习，面试前冲刺热题
>
> 往返重复，拿出对待高考的态度和方法：先做例题，再做课后题，最后再做各种模拟套题

# **二.开始刷题**

### **1.数组**

**（1）理论知识点**

> 1. 数组是存放在**连续内存**空间上的相同类型数据的集合
> 2. 数组的元素是不能删的，**只能覆盖**
> 3. java是**没有指针**的，同时也不对程序员暴露其元素的地址，寻址操作完全交给虚拟机。

**（2）算法知识点**

> 1. 一定要观察并巧妙利用所给输入数据本身所具有的**数据特性**！（递增、递减、非递增等等）
> 2. **双指针**！数组操作一定离不开双指针：可以有快慢指针（i-j）、也可以是两端指针（left-right）活用！
> 3. **快排知识点**：
>    1. ①首先选取第一个作为中间值p，并且两端指针left-right 
>    2. ②然后先从右端right开始往左找比中间值小的放在最左端（因为中间值左边都是比其小的） 
>    3. ③再从左端left开始往右找比中间值大的放在最右端（因为中间右边都是比其大的）
> 4. **滑动窗口**：滑动窗口，就是不断的调节子序列的起始位置和终止位置，从而得出我们要想的结果。也可以理解为双指针法的一种！只不过这种解法更像是一个窗口的移动，所以叫做滑动窗口更适合一些。
>    1.  ***实现滑动窗口，主要确定如下三点：***
>
>    2. 窗口内是什么？（这个很重要，一定要想清楚窗口内最需要把握的是什么！）
>    3. 如何移动窗口的起始位置？
>    4. 如何移动窗口的结束位置？
> 5. **哈希：**哈希的这个优势一定要把握住！
>
> ```Java
> //没有此key的话直接添加，如果有的话则在原来的值上面+1（还可以减1）
> pack.put(fruits[right], pack.getOrDefault(fruits[right], 0) + 1);
> ```
>
> 1. **螺旋矩阵：**螺旋矩阵最重要的就是自定义边界！自定义四个边界！然后从左到右、从上到下、从右到左、从下到上遍历一圈，遍历之后不要忘记边界的缩减！
> 2. **总结思维导图**
>
> ![img](https://ovxmsaoguz.feishu.cn/space/api/box/stream/download/asynccode/?code=OTdlZTg1YThlYjFjYmY1ZjYzOWUzOWE1YzlkYjM3MDlfaVN5cnRHUWJMbGR5bm03ZkZPZXBOTEpacHY3Z25KZXpfVG9rZW46UEZiQmJrSHg0b0FiRk54ekRhMGNNNHhVbnNlXzE2OTg0NjQ4NDQ6MTY5ODQ2ODQ0NF9WNA)

**（3）重点题目**

- 数组4.[有序数组的平方](https://leetcode.cn/problems/squares-of-a-sorted-array/)
- 数组5.[长度最小的子数组](https://leetcode.cn/problems/minimum-size-subarray-sum/) & [水果成篮](https://leetcode.cn/problems/fruit-into-baskets/)
- 数组6.[螺旋矩阵 II](https://leetcode.cn/problems/spiral-matrix-ii/)

### **2.链表**

**（1）理论知识点**

> 1. 链表的结构体定义
> 2. 链表的种类主要为：单链表，双链表，循环链表
> 3. 链表的存储方式：链表的节点在内存中是**分散存储**的，通过**指针连在**一起。
> 4. 链表操作中一个非常重要的技巧：**虚拟头节点**

**（2）算法知识点**

> 1. **单链表的删除**：
>    1. ①对于**头结点的删除**操作可以设置**虚拟节点**来！
>    2.  ②可以设置前置指针pre、当前指针now来辅佐删除节点，普通遍历和删除遍历的pre now指针改动不一样！需要注意
> 2. **链表的翻转**：
>    1. ①一般而言面试（机试）的时候不允许我们修改节点的值，而只能修改节点的指向操作。
>    2. ②采用头插法的话，有一个特征就是那个最开始的值，会一直处在最后面往后走
>    3. 就是childHead指针本来是头 childEnd指针本来是尾巴，但是翻转之后指针还是跟着走的！！！ 一定记住指针跟着节点走的！所有指针还是可以用的！！！
> 3. **链表相交：**注意：结点相交的位置不是节点的值相等！而是节点是同一个节点（指针相等）！这个不一样！ 其次可以用哈希值来判断是不是一个结点！！！
> 4. **环形链表：**
>    1. *巧妙利用链表节点的hashCode的唯一值！！！ （不可以用节点值表示唯一，就一定要用hashCode()表示唯一！）*
>    2. *一直往下走 走一个存储一个结点的 hashCode() 如果此值是存在过的(map的值为2)，则返回此节点，此节点便是环的入口*
>    3. ①首先是快慢指针 fast一次走两步 slow一次走一步如果有环则一定相遇 
>    4. ②其次是当相遇时 则一个指针从头结点开始 一个从当前相遇结点开始 第一次相遇的时候就是环形入口！
> 5. **循环判断：快慢指针**（fast一次走两步，slow一次走一步）判断是否有循环都可以用快慢指针！或者哈希集合Set
> 6. 总结
>
> ![img](https://ovxmsaoguz.feishu.cn/space/api/box/stream/download/asynccode/?code=NDJmN2IzN2U1ZTFmY2IxM2FjNmFiMGI0MmI5MGY0YjNfa0xZZGg5TFpXcGZ1V1ptOFk5a1hpUnM3NVRhb05NSlVfVG9rZW46QmQ1RmJRcGVXb0VhdzV4NVNocWNLWkt4bm9nXzE2OTg0NjQ4NDQ6MTY5ODQ2ODQ0NF9WNA)

**（3）重点题目**

- 链表 [删除排序链表中的重复元素 II](https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/)
- 链表4. [反转链表 II](https://leetcode.cn/problems/reverse-linked-list-ii/) 【面试时不可改变节点值 一定要多做几遍这个！】
- 链表6. [删除链表的倒数第 N 个结点](https://leetcode.cn/problems/remove-nth-node-from-end-of-list/)【这一题已做出 建议再做一遍 细节较多！】
- 链表8. [142. 环形链表 II](https://leetcode.cn/problems/linked-list-cycle-ii/)【快慢指针again！】

### **3.哈希表**

**（1）理论知识点**

> 1. 哈希表（英文为**Hash table**，**散列表**都是指hash table）
> 2. 哈希表是根据**关键码的值**而直接进行访问的数据结构。
> 3. 一般哈希表都是用来快速判断**一个元素是否出现集合里**。【只需要O(1)就可以做到】
> 4. **哈希碰撞**的两种解决方法： 拉链法和线性探测法。
> 5. 常见的三种**哈希结构**：
>    1. **数组 ！！**
>    2. set （集合）
>    3. map(映射)
>
> ```Java
> Set<Integer> set= new HashSet<Integer>(); //只对key操作 不可重复
> Map<Integer, Integer> map = new HashMap<>(); //对key-value操作 key不可重复 value可重复
> ```
>
> 1. 当我们遇到了要快速**判断一个元素是否出现集合里**的时候，就要考虑哈希法
> 2. 如果在做面试题目的时候遇到需要**判断一个元素是否出现过**的场景也应该第一时间想到**哈希法**！
> 3. Map中存储的就是key-value的键值对，Set中只存储了Key
>    1. Map: Key-Value 模型【map可以判断此元素是否在集合里面，并且有几个！个数要求】
>    2. ![img](https://ovxmsaoguz.feishu.cn/space/api/box/stream/download/asynccode/?code=ZjQ3YjQ0YzRjNTAxYTk4MjU0ZjE5NzU4NjM1NGRlMWFfNUVoOFBMVnY0b1RlcTFsTmh0ek1SMDN6UzFmUE90bjBfVG9rZW46RXV1dmJ4TDRjb2E3S1Z4UjNoNWMwRGl0blhHXzE2OTg0NjQ4NDQ6MTY5ODQ2ODQ0NF9WNA)
>
>    3. Set: 纯 key 模型【set可以判断此元素是否在集合里面 仅此而已】
>    4. ![img](https://ovxmsaoguz.feishu.cn/space/api/box/stream/download/asynccode/?code=OGRmNzk4MjRkMTBjYjFhYzhhMDNkOTU0Yjc2OGQ1OTJfb3Q1NXRDY2JXU2I0N2Fja2NNWHNLbW83aldIUEg1QWtfVG9rZW46Q2h6T2JCRDhOb0dHa0h4QVRjMWM4Mk5rbmFjXzE2OTg0NjQ4NDQ6MTY5ODQ2ODQ0NF9WNA)
> 4. **循环判断可以用Set！**
> 5. 

**（2）算法知识点**

> 1. **== 与equals的区别**：==不仅比较值也比较引用地址。还需要注意的是：
>    1. 当使用Integer时，Integer 129！= Integer 129的。他们这样是不等的！因为使用Integer时。只能在-127到128内比较。这之外的就不可以使用==判断了。
> 2. 记住只有**Integer** 记住**-128~127** 是相同的！！！！
>
> ```Java
> Integer test1 = 128;
> Integer test2 = 128;
> Integer test3 = 127;
> Integer test4 = 127;
> System.out.println(test1 == test2);        //false
> System.out.println(test1.equals(test2));   //true
> System.out.println(test3 == test4);        //true
> System.out.println(test3.equals(test4));   //true
> ```
>
> 1. **字符串相关**：
>    1. 可以把字符串toCharArray()先拆成char数组，然后再使用Arrays.sort对数组进行排序，再使用new String(char数组)把数组恢复成字符串！【字符串->排序后->字符串】
>    2. 以及map和list的结合操作！yyds！
> 2. 这个的应用很精彩！！一定要学到并且会巧妙的用
>
> ```Java
> //根据排序后的字符串在map中取值，没有则new 有则取出来
> List<String> list = map.getOrDefault(key, new ArrayList<>());
> list.add(strs[i]);
> map.put(key, list);
> ```
>
> 1. ***异位词****只关注****字母的个数****是否相同，而不关注字母的顺序*
> 2. **map的赋值操作**！
>
> ```Java
> //直接这样赋值是map 和 temp跟着一起做改变的 换句话说：map和temp为同一个
> Map<Character, Integer> temp = map；
> //这样赋值的话 就是把map里的所有值复制给temp，同时操作temp的值时，map中的值是不会改变的
> Map<Character, Integer> temp = new HashMap<>();
> temp.putAll(map);
> ```
>
> 1. **判断两个数组**的所有值**是否是相等的**！
>
> ```Java
> //判断两个数组的数值是否是完全相等的
> if (Arrays.equals(arrayS, arrayP)){
>     result.add(left);
> }
> ```
>
> 1. 相较于哈希map，还是数组的速度会更快！所以要学会**把26个字母映射到数组上来统计个数**的行为！【注意：知道固定多少个key时候可以用数组，不知道多少个key时候可以用哈希map！】
>
> ```Java
> //创建数据26个 并且直接进行赋给 p.charAt(i)-'a'为索引
> int arrayP[] = new int[26];
> for (int i = 0; i < p.length(); i++){
>     arrayP[p.charAt(i)-'a']++;
> }
> ```
>
> 1. **List、Set转化为int[]数组问题：**
>
> ```Java
> //List转化为int[]数组问题
> List<Integer> list = new ArrayList<>();
> //直接list.toArray()的话是转换成的Object[]数组问题，但是我们需要的是int[]数组问题【Java8 api把list转化为int[]数组问题】
> return list.stream().mapToInt(Integer::intValue).toArray();
> 
> //Set转化为int[]数组问题（没有专门的方法只能通过增强for循环）
> Set<Integer> set = new HashSet<>();
> set.add(1);set.add(2);set.add(3);
> for (int element : set){
>     System.out.println(element);
> }//output：1 2 3
> ```
>
> 1. **List、Set、Map的直接遍历问题：**
>
> ```Java
> //List遍历【直接用增强for循环】
> List<Integer> list = new ArrayList<>();
> list.add(5);list.add(6);list.add(7);
> for (int element : list){
>     System.out.println(element);//output：5 6 7
> }
> 
> //Set遍历【直接用增强for循环】
> Set<Integer> set = new HashSet<>();
> set.add(1);set.add(2);set.add(3);
> for (int element : set){
>     System.out.println(element);//output：1 2 3
> }
> 
> //Map遍历【先用增强for循环遍历map.keySet()，然后接着直接用map.get()遍历所有的值】
> Map<Integer, Integer> map = new HashMap<>();
> map.put(0, 0);  map.put(1, 100); map.put(2, 200);
> for (int element : map.keySet()){
>     System.out.println(element + " : " + map.get(element));
> }//0 : 0     1 : 100     2 : 200
> ```
>
> 1. 使用**哈希Set**【set.contains(nums2[i])】可以**在** ***O*****(1) 的时间内判断一个元素是否在集合中**
> 2. Java中常见的数学**Math函数**用法
>
> ```Java
> //开方演示：3.0
> System.out.println("开方演示：" + Math.sqrt(9));
> //平方（n次方）演示：9.0
> System.out.println("平方（n次方）演示：" + Math.pow(3, 2));//这里是3 的 2次方
> //取绝对值：100
> System.out.println("取绝对值：" + Math.abs(-100));
> //取最大值：9
> System.out.println("取最大值：" + Math.max(3, 9));
> //取最小值：3
> System.out.println("取最小值：" + Math.min(3, 9));
> ```
>
> 1. **质数，又称素数**，是指在大于1的自然数中，除了1和它本身以外不再有其他因数的自然数。【**注意：1既不是质数，也不是合数！！！！**】
>
> ```Java
> //判断一个数是不是质数 【当然，这里的输入默认是大于等于1的数，如果是小于1的数直接返回false】
> public boolean isPrimeNumber(int n) {
>     //注意：1 不是质数！遇到1直接返回false
>     if (n == 1){
>         return false;
>     }
>     //这里只需要遍历到对n开根号即可
>     for (int i = 2; i <= (int) Math.sqrt(n); i++){
>         if (n % i == 0){//如果n%i=0 则表明i是其因数
>             return false;
>         }
>     }
>     return true;
> }
> ```
>
> 1. **数组Array函数的常用方法**！方便的操作
>
> ```Java
> //1.Arrays.toString()方法 【作用：toString方法用于将数组转换为字符串，可以快速的输出数组的内容】
> int[] array = {1, 2, 3};
> System.out.println(Arrays.toString(array));
> 
> //2.Arrays.sort()方法 【作用：给数组排序，默认是升序的】
> int[] array = {5, 2, 3, 1, 4};
> Arrays.sort(array);
> System.out.println(Arrays.toString(array));
> 
> //3.Arrays.equals()方法 【作用：比较数组内容是否相等，如果相等返回true，否则返回false】
> int[] arr1 = {1, 2, 3};
> int[] arr2 = {1, 2, 3};
> boolean isEqual = Arrays.equals(arr1, arr2);
> System.out.println(isEqual);
> 
> //4.Arrays.binarySearch()方法【作用：在数组中查找元素，如果找到返回找到的元素在数组中的下标，如果没找到就返回-1】
> int[] arr = {1, 2, 3, 4, 5};
> int index = Arrays.binarySearch(arr, 3);
> System.out.println(index);
> 
> //5.Arrays.copyOf()方法【作用：复制指定数组，当中的第一个参数是复制的原数组，第二个参数是复制的长度】
> int[] arr1 = {1, 2, 3};
> int[] arr2 = Arrays.copyOf(arr1, 5);
> System.out.println(Arrays.toString(arr2));
> 
> //6.Arrays.copyOfRange()方法【作用：是上个方法的改进版，这个方法里可以指定起始位置和结束位置】
> int[] arr1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
> int[] arr2 = new int[5];
> arr2 = Arrays.copyOfRange(arr1, 3, 5);
> System.out.println(Arrays.toString(arr2));//[4, 5]左闭右开 而且会全覆盖arr2 也就是说arr2上面的初始化无效
> 
> //7.Arrays.aslist()方法【作用：将数组转化为List集合】
> Integer[] arr = {1, 2, 3, 4, 5};
> List<Integer> list = Arrays.asList(arr);
> System.out.println(list);
> 
> //8. Arrays.fill()方法【作用：将指定的int值分配给指定的int数组中的每个元素。】
> int[] array = new int[n];
> Arrays.fill(array, 1);
> ```
>
> 1. **int与long【结论是：先进行转型 再进行运算（如果先运算的话 就会转型无效了）】**
>
> ```Java
> System.out.println(499979 * 499979);             //870897273
> System.out.println((long) (499979 * 499979));    //870897273（无效转型）
> System.out.println((long) 499979 * 499979);      //249979000441（有效转型）
> System.out.println((long) 499979 * (long)499979);//249979000441（有效转型）
> ```
>
> 1. **Integer数组**转化成链表存储时， 当数组改变时**已存储的链表也跟着改变** **【****结论：list不管存储在哪里，只要原list改变，就会跟着改变的****】**
>
> ```Java
> //例1
> List<List<Integer>> list = new ArrayList<>();
> Integer[] arr = new Integer[3];
> arr[0] = 1; arr[1] = 2; arr[2] = 3;
> //首先把数组转化成链表存进去
> list.add(Arrays.asList(arr));
> System.out.println(list.get(0));//[1, 2, 3]
> //此时再重新改变数组的值 发现存储的链表也改变了
> arr[0] = 7; arr[1] = 8; arr[2] = 9;
> System.out.println(list.get(0));//[7, 8, 9]
> 
> //例2
> List<List<Integer>> list = new ArrayList<>();
> List<Integer> childList = new ArrayList<>();
> childList.add(1);childList.add(2);childList.add(3);
> //存储子链表
> list.add(childList);
> System.out.println(list);//[[1, 2, 3]]
> //改变子链表的值
> childList.set(0, 7);
> childList.set(1, 7);
> childList.set(2, 7);
> System.out.println(list);//list存储的也跟着改变了：[[7, 7, 7]]
> ```
>
> 1. **List、Set、Map区别和主要用途**
>    1. List：有序、可重复。
>    2. Set：无序、不可重复的集合。重复元素会覆盖掉。
>    3. Map：键值对，键唯一、值不唯一。Map 集合中存储的是键值对，键不能重复，值可以重复。
> 2. **跳出指定for循环**
>
> ```Java
> out:
> for (int i = 0; i < 5; i++){
>     for (int j = 0; j < 5; j++){
>         if (j == 3){
>             break out;
>         }
>     }
> }
> ```
>
> 1. **四数之和一定要再做一遍！【其和三数之和的思想一样，都是把最后两层for循环表示成双指针来遍历】**
> 2. **又是int的溢出情况：**
>
> ```Java
> int nums[] = {1000000000,1000000000,1000000000,1000000000};
> System.out.println(-294967296 == nums[0] + nums[1] + nums[2] + nums[3]);       //true
> System.out.println(-294967296 < ((long)nums[0] + nums[1] + nums[2] + nums[3]));//true
> System.out.println(nums[0] + nums[1] + nums[2] + nums[3])                      //-294967296
> System.out.println((long)nums[0] + nums[1] + nums[2] + nums[3]);               //4000000000
> ```
>
> 1. 看清楚题目中是**要求到底有多少个**（统计数量，请你想到map）？还是把这所有的多少个都要列出来？这也决定着完全不一样的解答方式，前者的解答会更有多样性！更具启发式的操作！
> 2. 

**（3）重点题目**

哈希表2. [49. 字母异位词分组](https://leetcode.cn/problems/group-anagrams/) 这一题对Map和List的运用绝了！！！一定again

哈希表4. [263. 丑数](https://leetcode.cn/problems/ugly-number/) 这一题的思想不错 虽然我已不同思想做出

哈希表4. [264. 丑数 II](https://leetcode.cn/problems/ugly-number-ii/) 此题不是简单的丑数1的拓展 涉及到后面的知识（动态规划） 所以后续再做

哈希表4. [204. 计数质数](https://leetcode.cn/problems/count-primes/) 此题说来也简单 但是暴力枚举解法会超时，所以可以使用倍数跳动填补数组

哈希表6. [454. 四数相加 II](https://leetcode.cn/problems/4sum-ii/) 与n数之和不一样的做法！因为其数组是独立的 而不是操作的同一个数组

哈希表7. [383. 赎金信](https://leetcode.cn/problems/ransom-note/) 数组again！虽然已经数组做对 但再练一下数组解决字符串的思想【数组非常快】

哈希表8. [15. 三数之和](https://leetcode.cn/problems/3sum/) 细节很多！十分建议again！

哈希表8. [16. 最接近的三数之和](https://leetcode.cn/problems/3sum-closest/) 可以来练手！双指针来解决多层循环的思想！

哈希表8. [18. 四数之和](https://leetcode.cn/problems/4sum/) 这几个n数之和 就来做四数之和和最接近的3数吧

### **4.字符串**

**（1）理论知识点**

> 1. 字符串牛！

**（2）算法知识点**

> 1. char数组到字符串 and 字符串到char数组
>
> ```Java
> //String s
> char[] chars = s.toCharArray(); //字符串 -> char数组
> s = new String(chars);          //char数组 -> 字符串
> ```
>
> 1. Java库函数的字符串翻转
>
> ```Java
> //测试Java库函数的翻转字符串
> String test = "abcdefghijk";
> String result = new StringBuilder(test).reverse().toString();
> System.out.println(result);
> ```
>
> 1. 其实很多**数组填充类**的问题，都可以先预先**给数组扩容**带填充后的大小，然后在**从后向前进行操作**。这么做有两个好处：
>    1. 不用申请新数组。
>    2. 从后向前填充元素，避免了从前向后填充元素时，每次添加元素都要将添加元素之后的所有元素向后移动的问题。
> 2. 在做**字符串按照空格切分**的时候注意：
>    1. *注意空格也会被分开 但是只会被分成“” 或者“ ”。*
>    2. *字符串的截取substring(i, j) 是左闭右开*
> 3. **正则表达式中“\s”**匹配任何空白字符，包括空格、制表符、换页符等等, 等价于[ \f\n\r\t\v]。而“\s+”则表示**匹配任意多个上面的字符**。另因为反斜杠在Java里是转义字符，所以**在Java里**，我们要这么用“**\\s+**”.
>
> ```Java
> "\\s" 表示：空格,回车,换行等空白符； 
>  + 号表示一个或多个的意思,所以..."\\s+"任意匹配单个或多个空格等空白符。
> ```
>
> 1. 去除字符串的前缀和后缀空白
>    1. **trim()**可以去除字符串**前后的半角空白**字符
>    2. **strip()**可以去除字符串**前后的全角和半角空白**字符
>
> ```Java
> //trim()、strip()都是去掉首尾空格
> String.trim();
> String.strip();
> ```
>
> 1. **StringBuilder 对字符串的灵活使用****【对于字符串处理单个字符可以使用string.toCharArray()就够了，但是如果处理里面的“子字符串”的话，就需要用到StringBuilder了】**
>
> ```Java
> //StringBuilder
> StringBuilder stringBuilder = new StringBuilder();
> stringBuilder.append("hello world");
> stringBuilder.indexOf("l");//查询首个“字符串”的位置【2】
> stringBuilder.indexOf("l", 4);//从n位置开始查询首个“字符串”的位置【9】
> //下面是增删改查 （涉及双边界都是左闭右开）
> int index = stringBuilder.indexOf(" ");
> stringBuilder.replace(index, index + 1, "..");//替换“字符串”（setCharAt：替换单个字符）【hello..world】
> stringBuilder.delete(index, index + 2);//删除“字符串”（deleteCharAt：删除单个字符）【helloworld】
> stringBuilder.insert(index, "!-!");//插入“字符串” 【hello!-!world】
> stringBuilder.setCharAt(index, '%');//替换单个字符 【hello%-!world】
> stringBuilder.charAt(index);//查询单个字符 【%】
> stringBuilder.substring(0, 5);//获取任意位置子“字符串” 【hello】
> stringBuilder.reverse();//字符翻转 【dlrow!-%olleh】
> ```
>
> 1. 子字符串的匹配问题：注意匹配完不成立时， i 指针还是要拐回来的！
> 2. 有**连续相同重复**的数字，但我们**只需要保留1个**时，使用以下方法！【*去重的思想！*】
>
> ```Java
> //输入：__hello___world__  输出：hello_world
> publc String deleteSpace(String s){
>     s = s.strip();
>     StringBuilder stringBuilder = new StringBuilder(s);
>     for (int i = 0; i < stringBuilder.length(); i++){
>         //此为空格 且前一个也是空格才进行删掉【不删除第一个空格，只删除第一个之后的空格】
>         if (stringBuilder.charAt(i) == ' ' && stringBuilder.charAt(i-1) == ' '){
>             stringBuilder.deleteCharAt(i);
>             //这里特别注意！因为你把它删掉了所以要i--
>             i--;
>         }
>     }
>     return stringBuilder.toString();
> }
> System.out.println(myString.deleteSpace("  hello   world   "));//hello world
> ```
>
> 1. 

**（3）重点题目**

字符串6. [28. 找出字符串中第一个匹配项的下标](https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/) 【暴力两个循环即可 or KMP?】更简单就用StringBuilder

字符串6. [459. 重复的子字符串](https://leetcode.cn/problems/repeated-substring-pattern/) 【思路很难，比较难想，建议again】（利用数据本身特有的特性！）

### **5.双指针**

**（1）理论知识点**

> 1. 其实所有内容都在上面概括了 可学习总结

**（2）算法知识点**

> 1. 暂无 

**（3）重点题目**

暂无

### **6.栈与队列**

**（1）理论知识点**

> 1. 队列是先进先出
> 2. 栈是先进后出。
> 3. **Stack 栈**的使用 **Stack<Integer> s = new Stack();**
>
> ![img](https://ovxmsaoguz.feishu.cn/space/api/box/stream/download/asynccode/?code=MmZjMjIxZjUwMmQyYmNmYzM0N2U4N2I4Y2JhZjFiMGVfQ2kzVmJlcWw3eE41eDJ4dlJUVE1qM2RSeXdJRDdFR2FfVG9rZW46U3VlTGJkVERJb1lhUXp4cDVCZ2N0Y2hpbjVlXzE2OTg0NjQ4NDQ6MTY5ODQ2ODQ0NF9WNA)
>
> 1. **Queue 队列**的使用 **Queue<Integer> q = new LinkedList<>();**
>
> ![img](https://ovxmsaoguz.feishu.cn/space/api/box/stream/download/asynccode/?code=MzE2ZjlkODc1MWUxOTJiZjVjNmI1ODNjYjc3ZDEwNGZfM2xOc2pQMnpyTk01aWVMZWdoYzFHRnZWWDRrTEh6bTJfVG9rZW46T0x2cmJtR1YzbzZGNkV4M0dEc2N0YXZtbmlWXzE2OTg0NjQ4NDQ6MTY5ODQ2ODQ0NF9WNA)
>
> 1. **Deque(双端队列)** **Deque deque = new LinkedList()**【允许两头都进，两头都出，这种队列叫双端队列（Double Ended Queue）】
>
> ![img](https://ovxmsaoguz.feishu.cn/space/api/box/stream/download/asynccode/?code=Yjg0Y2M0MjJjNTFhMDAzYTdhZDBmMWFkODIwOTU4N2Zfclo1OXp6MzhtWTQ0V2lZZ3JrMnZRRjI1N0pJRUJCS1lfVG9rZW46WEtkMmJVVTFSbzB0Wmt4ZmZNOWNkRzJZbkljXzE2OTg0NjQ4NDQ6MTY5ODQ2ODQ0NF9WNA)
>
> 1、Queue只能从队尾插入，从对头删除。
>
> 2、Deque对头队尾都可以进行插入和删除。
>
> 3、Deque根据不同的插入和删除方法可以实现队列和堆栈（也就是栈）这两种数据结构。
>
> 1. 使用**双栈来表示队列**时：
>    1. 在push数据的时候，只要数据放进输入栈就好
>    2. 在pop的时候，操作就复杂一些，输出栈如果为空，就把进栈数据全部导入进来（注意是全部导入），再从出栈弹出数据，如果输出栈不为空，则直接从出栈弹出数据就可以了
> 2. 使用**双队列来表示栈**时：
>    1. 用栈实现队列， 和用队列实现栈的思路还是不一样的，这取决于这两个数据结构的性质。
>    2. 依然还是要用两个队列来模拟栈，只不过没有输入和输出的关系，而是**另一个队列完全用来备份的**！
>    3. 用两个队列que1和que2实现队列的功能，**把que1最后的元素以外的元素都备份到que2，然后弹出最后面的元素，再把其他元素从que2导回que1。**
> 3. **队列来表示栈：单个队列也可以实现！【只需要利用好队列的size，一边遍历一边插入到自己队列中，直到最后一个元素（第size-1个元素）进行排出或取得即可】**
> 4. 

**（2）算法知识点**

> 1. 

**（3）重点题目**

### **7.二叉树**

**（1）理论知识点**

**（2）算法知识点**

**（3）重点题目**

### **8.回溯算法**

**（1）理论知识点**

**（2）算法知识点**

**（3）重点题目**

### **9.贪心算法**

**（1）理论知识点**

**（2）算法知识点**

**（3）重点题目**

### **10.动态规划**

**（1）理论知识点**

**（2）算法知识点**

**（3）重点题目**

### **11.单调栈**

**（1）理论知识点**

**（2）算法知识点**

**（3）重点题目**

### **12.图论**

**（1）理论知识点**

**（2）算法知识点**

**（3）重点题目**