# HuntOffer

# **一.刷题间接经验**

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
>
> 1. 高频题目链接：[Leetcode各标签高频题目排序](https://ovxmsaoguz.feishu.cn/docx/RHAudENIUoMtLEx5ljncKIgCngf) 

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
> ![img](https://ovxmsaoguz.feishu.cn/space/api/box/stream/download/asynccode/?code=NjkzZDZiNGZmOTk3YTNhN2UyODhhNWU1NmM2OWYzMDZfWnFiZVpvNmhzTndPUm05VmZlRFZYZVc5WFNidDhyVTJfVG9rZW46UEZiQmJrSHg0b0FiRk54ekRhMGNNNHhVbnNlXzE2OTc3MTk4MjU6MTY5NzcyMzQyNV9WNA)

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
> ![img](https://ovxmsaoguz.feishu.cn/space/api/box/stream/download/asynccode/?code=YWE5ZTRhYmU0ZWVmMGUzYTliMzFhOWFmNmRlYzI2ZGNfMk9ZbGdkZFJ4RnBRZDA2Z0Z5Yzl4TU11ajd5Y3RsWlJfVG9rZW46QmQ1RmJRcGVXb0VhdzV4NVNocWNLWkt4bm9nXzE2OTc3MTk4MjU6MTY5NzcyMzQyNV9WNA)

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
>    4. ```Java
>       Set<Integer> set= new HashSet<Integer>(); //只对key操作 不可重复
>       Map<Integer, Integer> map = new HashMap<>(); //对key-value操作 key不可重复 value可重复
>       ```
> 6. 当我们遇到了要快速**判断一个元素是否出现集合里**的时候，就要考虑哈希法
> 7. 如果在做面试题目的时候遇到需要**判断一个元素是否出现过**的场景也应该第一时间想到**哈希法**！
> 8. Map中存储的就是key-value的键值对，Set中只存储了Key
>    1. Map: Key-Value 模型【map可以判断此元素是否在集合里面，并且有几个！个数要求】
>    2. ![img](https://ovxmsaoguz.feishu.cn/space/api/box/stream/download/asynccode/?code=MGZmYmJmODFjODgxZWVhZThmYTMxYzg5NmViZDVlMmRfdTJlMjAyTVFKVzZRNUd5Mmh5UlNOcXdtWU9tM0V1dkhfVG9rZW46RXV1dmJ4TDRjb2E3S1Z4UjNoNWMwRGl0blhHXzE2OTc3MTk4MjU6MTY5NzcyMzQyNV9WNA)
>
>    3. Set: 纯 key 模型【set可以判断此元素是否在集合里面 仅此而已】
>    4. ![img](https://ovxmsaoguz.feishu.cn/space/api/box/stream/download/asynccode/?code=YjQ3OTk2Y2FmN2IyNWEyYTliN2VjZjExNWNiYjcwOGNfTXRJMm9SSnZxS0FNUHd3RDJsbVFYREdaZ3h5YkQwajVfVG9rZW46Q2h6T2JCRDhOb0dHa0h4QVRjMWM4Mk5rbmFjXzE2OTc3MTk4MjU6MTY5NzcyMzQyNV9WNA)
> 9. **循环判断可以用Set！**
> 10. 

**（2）算法知识点**

> 1. **== 与equals的区别**：==不仅比较值也比较引用地址。还需要注意的是：
>    1. 当使用Integer时，Integer 129！= Integer 129的。他们这样是不等的！因为使用Integer时。只能在-127到128内比较。这之外的就不可以使用==判断了。
> 2. 记住只有**Integer** 记住**-128~127** 是相同的！！！！
>
> ![img](https://ovxmsaoguz.feishu.cn/space/api/box/stream/download/asynccode/?code=ODIwNTZlODhmNjE4NWRiYTJjZWY1MWNjYjAwMzk2NGNfTUZ1WFZKZnBGTDJrTlYxTHpkV2RwMDJyT2NORkVQcElfVG9rZW46SGFvUGIzNnhIb21iYnV4elpVWmNkQ0g3bk9mXzE2OTc3MTk4MjU6MTY5NzcyMzQyNV9WNA)
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

**（3）重点题目**

哈希表2. [49. 字母异位词分组](https://leetcode.cn/problems/group-anagrams/) 这一题对Map和List的运用绝了！！！一定again

### **4.字符串**

**（1）理论知识点**

**（2）算法知识点**

**（3）重点题目**

### **5.双指针**

**（1）理论知识点**

**（2）算法知识点**

**（3）重点题目**

### **6.栈与队列**

**（1）理论知识点**

**（2）算法知识点**

**（3）重点题目**

### **7.二叉树**

**（1）理论知识点**

**（2）算法知识点**

**（3）重点题目**