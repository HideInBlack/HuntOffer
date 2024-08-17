package codeFlowIdea;

import java.util.*;

/**
 * codeFlowIdea 代码随想录学习记录 time：2023年9月23日15:06:14 -> 2023年10月8日15:53:08
 * author：董政宇
 * 第一部分 数组部分：MyArray
 */
public class MyArray {
    //√（1）数组理论基础
    //√（2）704. 二分查找 time:2023年9月23日15:17:45 -》 2023年9月23日15:51:07
    //笔记要点：①记得wile中的是i<=j!要带等号 ②记得提前判断特殊情况省时！
    public int search(int[] nums, int target) {
        //提前判断 这样有利于节省时间 target直接超出时
        if (target < nums[0] || target > nums[nums.length-1]) {
            return -1;
        }
        int length = nums.length;
        int i = 0; int j = length-1;
        int t = (i + j) / 2;
        while(i<=j){
            if(target < nums[t]){
                j = t-1;
            }else if(target > nums[t]){
                i = t+1;
            }else{
                return t;
            }
            t = (i + j) / 2;
        }
        return -1;
    }

    //√（2.1方法一）34. 排序数组中查找元素的第一个和最后一个位置(二分查找) time: 2023年9月23日17:20:21 -》 2023年9月23日17:38:09
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        int first = -1; int second = -1;
        if(nums.length == 0 || target > nums[nums.length - 1] || target <nums[0]){
            return result;
        }

        int i = 0; int j = nums.length-1;
        int q = (i + j) / 2;
        while(i<=j){
            if (target <nums[q]){
                j = q - 1;
            }else if(target > nums[q]){
                i = q + 1;
            }else {
                for (int x = q; x >= 0; x--){
                    first = x;
                    if(nums[q] != nums[x]){
                        first = x+1;
                        break;
                    }
                }
                for (int y = q; y <= nums.length-1; y++){
                    second = y;
                    if(nums[q] != nums[y]){
                        second = y-1;
                        break;
                    }
                }
                result[0] = first;
                result[1] = second;
                System.out.println(first +","+ second);
                break;
            }
            q = (i + j) / 2;
        }
        return result;
    }

    //√（2.1方法二）34. 排序数组中查找元素的第一个和最后一个位置(二分查找) time：2023年9月23日20:12:24 -》 2023年9月23日20:50:14
    public int[] searchRange1(int[] nums, int target) {
        int i = 0; int j = nums.length - 1;
        int n = 0; int k = nums.length - 1;
        int q = (i + j) / 2;
        int p = (n + k) / 2;
        int left = -1; int right = -1;
        int[] result = new int[2];
        if(nums.length == 0 || target > nums[nums.length - 1] || target <nums[0]){
            result[0] = -1; result[1] = -1;
            return result;
        }
        while( i <= j){
            if (target < nums[q] || (target == nums[q] && q > 0 && nums[q-1] == nums[q] )){
                j = q - 1;
            }else if (target > nums[q] ){
                i = q + 1;
            }else {
                left = q;
                break;
            }
            q = (i + j) / 2;
        }
        while( n <= k){
            if (target < nums[p] ){
                k = p - 1;
            }else if (target > nums[p] || (target == nums[p] && p < nums.length - 1 && nums[p] == nums[p+1] )){
                n = p + 1;
            }else {
                right = p;
                break;
            }
            p = (n + k) / 2;
        }
        result[0] = left;
        result[1] = right;
        System.out.println(result[0] + "," + result[1]);
        return result;
    }

    //√（3）27.移除元素 time：2023年9月23日16:08:41 -》 2023年9月23日16:32:58
    //笔记要点：观察数据本身的特性 ①双指针的妙用 ②从后往前补 这样思路更清晰
    public int removeElement(int[] nums, int val) {
        int length = nums.length;
        for(int i = nums.length-1; i >= 0; i--){
            if(nums[i] == val){
                nums[i] = nums[length-1];
                length--;
            }
        }
        return length;
    }

    //√（3.1）删除有序数组中的重复项 time: 2023年9月23日21:30:30 -》 2023年9月23日21:48:31
    //笔记要点：这个也是首先数据本身的特性 + 双指针的使用
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0){ return 0;}
        int i = 0; int j = 1;
        while(j < nums.length){
            //1.相同时固定i不要动 j一直往前走 2.直到不同时 赋值给i+1，同时i、j都往前走一个
            if(nums[i] != nums[j]){
                //当值不同时，把j的值赋给i+1（记住不是不动）
                nums[i+1] = nums[j];
                i++;
            }
            j++;
        }
        return i+1;
    }

    //√（3.2）有序数组的平方 time：2023年10月6日09:30:37  -> 2023年10月6日10:00:43
    //我的思路：从俩头往中间比较 双指针i、 j开始 直到ij相遇（那么数组平方的最大值就在数组的两端，不是最左边就是最右边，不可能是中间。此时可以考虑双指针法了，i指向起始位置，j指向终止位置。）
    //笔记要点：一定要先观察输入数据中本身具有的格式特征！（例如本题中的特征便是平方后最大的一定是在两端，其次就是如果第一个数为>0的话，则后面所有的数可以直接平方输出）
    public int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int i = 0; int j = nums.length - 1; int k = nums.length-1;
        while( i <= j ){
            if (nums[i] >= 0){
                for (int x = j; x >= i; x--){
                    result[k] = nums[x] * nums[x];
                    k--;
                }
                break;//直接进行一个结束
            }else {
                int square_i = nums[i] * nums[i];
                int square_j = nums[j] * nums[j];
                if ( square_i > square_j){
                    result[k] = square_i;
                    i++; k--;
                }else {
                    result[k] = square_j;
                    j--; k--;
                }

            }
        }
        return result;
    }

    //√（3.2 方法二）有序数组的平方 2023年10月6日13:57:19 -> 2023年10月6日15:48:19
    //笔记要点：试一下快排
    public int[] sortedSquares1(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            nums[i] = nums[i] * nums[i];
        }
        quickSort(nums, 0, nums.length-1);
        return nums;
    }
    //递归快速排序！递归方法
    public int[] quickSort(int[] nums, int left, int right){
        int temp_left = left;
        int temp_right = right;
        int p = nums[left];

        while (left < right){
            while(nums[right] > p && left < right){
                right--;
            }
            nums[left] = nums[right];
            nums[right] = p;
            left++;

            while (nums[left] < p && left < right){
                left++;
            }
            nums[right] = nums[left];
            nums[left] = p;
            right--;
        }

        if(temp_left < left-1){//得在满足情况的条件下进行递归 用if！！！
            quickSort(nums, temp_left, left-1);
        }
        if(right+1 < temp_right){//得在满足情况的条件下进行递归
            quickSort(nums, right+1, temp_right);
        }
        return nums;
    }

    //√（3.3）移动零 time：2023年10月6日10:42:18 -> 2023年10月6日10:53:49
    //我的思路：确定一个指针为数组的长度，然后直接遍历覆盖即可(i为慢指针 j为快指针)
    public void moveZeroes(int[] nums) {

        int i = 0; int j = 0;
        while(j < nums.length){//x先遍历一遍 j走到头
            if (nums[j] != 0){
                nums[i] = nums[j];
                i++;
                j++;
            }else {
                j++;
            }
        }
        for (int x = i; x < nums.length; x++){//这时j已经走到头了 把所有i后面的置为0即可
            nums[x] = 0;
        }
//        DzyUtils.printArray(nums);
    }

    //√（4）有序数组的平方（已做过 如上3.2）

    //*** ×（5）长度最小的子数组 209 time：2023年10月7日11:01:17 -> 放弃 不会
    public int minSubArrayLen(int target, int[] nums) {
        //√ 滑动窗口 看完题解之后的解法 time：2023年10月7日11:49:17 -> 2023年10月7日12:25:50
        //笔记要点：最重要的是滑动窗口的思路，起始位置left 和 终止位置right，其中for循环的应该是终止位置，窗口缩动的位置应该是起始位置，
        //重点：left是从最左端一个个移过去的！ right是for遍历。
        int i = 0;//i窗口最左端
        int sum = 0;
        int childLength = nums.length + 1;
        for (int j = 0; j < nums.length; j++){//j是窗口的最右端，for循环一定是循环的最右端 因为最右端是加一个，窗口滑起来会增加一个 这样操作合适，此时最左端的可以通过缩减窗口来控制
            sum = sum + nums[j];
            if (sum >= target){//未移动之前先看看满不满足条件
                if (j - i + 1 < childLength){
                    childLength = j - i + 1;
                }
            }
            while (sum > target){
                sum = sum - nums[i];//窗口最左端往右移
                i++;//移动之后不需要再移动回去，因为移动之后一定满足，还是要移回来
                if (sum >= target){//移了之后还满足条件呢
                    if (j - i + 1 < childLength){
                        childLength = j - i + 1;
                    }
                }
            }
        }
        if (childLength == nums.length + 1){
            return 0;
        }
        return childLength;
    }
    public int minSubArrayLen1(int target, int[] nums) {
        // √ 暴力解法 试一试 time：2023年10月7日11:11:52 -> 2023年10月7日11:28:02
        int childLength = nums.length + 1;
        for(int i = 0; i < nums.length; i++){
            int sum = 0;
            for (int j = i; j < nums.length; j++){
                sum = sum + nums[j];
                if (sum >= target){
                    if ((j - i + 1)< childLength){
                        childLength = j - i + 1;
                    }
                    break;
                }
            }
        }
        if (childLength == nums.length + 1){
            return 0;
        }
        return childLength;
    }

    //***** √（5）-（1）水果成篮 904 time：2023年10月7日13:08:48 -> 2023年10月7日14:14:54
    //我的思路：滑动窗口 保持窗口内只有两种类型的数字，要求窗口尽可能的大。left指针一定是从当前窗口与的最后一个往前找相同的！
    //笔记重点：
    public int totalFruit(int[] fruits) {
        if (fruits.length == 1){return 1;}
        if (fruits.length == 2){return 2;}
        int[] pack = {-1, -1};//-1代表篮子为空
        int left = 0; pack[0] = fruits[0];
        int maxNum = 0;
        for (int right = 1; right < fruits.length; right++){
            if (pack[1] == -1 && fruits[right] != pack[0]){//篮子2为空 并且水果不等于篮子1 此时放进来
                pack[1] = fruits[right];
            }else if(fruits[right] != pack[0] && fruits[right] != pack[1]){// 1.都不在篮子里时 跳出来

                if (maxNum < right - left){maxNum = right -left;}
                //重置篮子
                pack[0] = fruits[right-1];
                pack[1] = fruits[right];//重置篮子别忘记重置这个呀！！重置为空
                //寻找left的位置
                left = right - 1;
                while(left >= 0 && pack[0] == fruits[left]){
                    left--;
                }
                left++;
            }else if(right == fruits.length - 1){//2.走到最后了也要跳出来
                if (maxNum < right - left + 1){maxNum = right -left + 1;}
            }

        }
        return maxNum;
    }
    //***** √ （5）-（1）方法二 水果成篮 滑动窗口 + 哈希 time：2023年10月7日17:07:33
    //笔记重点：巧妙利用滑动窗口 + 哈希可以统计数量的优势，代码思路稳定！left是从左边一个个过去的，和最小子数组求和很像！
    //此方法也是把求最大转换成求最小的感觉：因为他是有size=3 -> size=2 的时候
    public int totalFruit1(int[] fruits) {
        int left = 0;
        int result = 0;
        Map<Integer, Integer> pack = new HashMap<Integer, Integer>();
        for (int right = 0; right < fruits.length; right++){
            //没有此key的话直接添加，如果有的话则在原来的值上面+1
            pack.put(fruits[right], pack.getOrDefault(fruits[right], 0) + 1);
            while(pack.size() > 2){
                //left往右移 则其数据上减少一个
                pack.put(fruits[left], pack.getOrDefault(fruits[left], 0) - 1);
                //如果移动之后 其数量变为0了，则删除
                if (pack.get(fruits[left]) == 0){
                    pack.remove(fruits[left]);
                }
                left++;//left前进一个
            }
            //刚由三个类型变两个类型 此时是最大数目（不一定 往后继续走 只要还是size=2 都可以进行算成是最终结果）
            result = Math.max(result, right - left + 1);//这里并不是每一步都在计算 而是先滑动完才进行计算的
        }
        return result;
    }

    //练习 快速排序 time：2023年10月8日09:47:38 -> 2023年10月8日10:14:14
    public int[] quickSortTry(int[] nums, int left, int right){
        int i = left;
        int j = right;
        int midValue = nums[i];
        while(i < j){
            while(i < j){//从右往左 找比中间值小的
                if (nums[j] < midValue){
                    nums[i] = nums[j];
                    i++;
                    break;
                }
                j--;
            }
            while (i < j){//从左往右 找比中间值大的
                if (nums[i] > midValue){
                    nums[j] = nums[i];
                    j--;
                    break;
                }
                i++;
            }
        }
        nums[i] = midValue;//最后循环之后 当i=j时，把最后的中间值赋给i

        //在进行递归排序其他部分（一定要先判断是否满足条件 再进行递归）
        if(left < i-1){
            quickSortTry(nums, left, i-1);
        }
        if (i + 1 < right){
            quickSortTry(nums, i + 1, right);
        }

        return nums;
    }

    //*** × （6）螺旋矩阵II 59 time：2023年10月8日10:21:28 -> 2023年10月8日10:47:48 放弃
    //题解四边界 time：2023年10月8日10:50:41 -> 2023年10月8日11:07:14
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int num = 1;
        //定义四个边界 这是这个题解的精髓之处
        int left = 0; int right = n - 1; int top = 0; int bottom = n - 1;
        while(num <= n * n){
            //从左到右
            for (int i = left; i <= right; i++){
                matrix[top][i] = num;
                num++;
            }
            top++;
            //从上到下
            for (int i = top; i <= bottom; i++){
                matrix[i][right] = num;
                num++;
            }
            right--;
            //从右往左
            for(int i = right; i >= left; i--){
                matrix[bottom][i] = num;
                num++;
            }
            bottom--;
            //从下到上
            for (int i = bottom; i >= top; i--){
                matrix[i][left] = num;
                num++;
            }
            left++;
        }
        return matrix;
    }

    // √ (6)-(1) 螺旋矩阵 time：2023年10月8日14:50:13 -> 2023年10月8日15:04:51
    //我的思路：和上一题的螺旋矩阵赋值的是一样的，都是使用四个边界来进行操作，只不过赋值操作变成了取值操作
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length; int n = matrix[0].length;
        int left = 0; int right = n - 1; int top = 0; int bottom = m - 1;
        List<Integer> result = new ArrayList<>();

        while(result.size() < m * n){
            //从左到右
            for (int i = left; i <= right; i++){
                result.add(matrix[top][i]);
            }
            top++;
            //从上到下
            for (int i = top; i <= bottom; i++){
                result.add(matrix[i][right]);
            }
            right--;
            //从右到左
            for (int i = right; i >= left; i--){
                result.add(matrix[bottom][i]);
            }
            bottom--;
            //从下到上
            for (int i = bottom; i >= top; i--){
                result.add(matrix[i][left]);
            }
            left++;
        }
        //我dzy的巧妙思路：这样存储在有些情况下是会存储多于的，这个时候只需要在最后把多于的全部删掉就可以了。 而不需要在上面每一步都要做一个判断
        while(result.size() > m * n){
            //因为存储在list里面的顺序 所有数据存储的顺序一定是正确的 可能数量会多于 但只要删除多于就可以。
            result.remove(result.size() - 1);
        }
        return result;
    }


    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {
        MyArray myArray = new MyArray();

        int nums[] = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        myArray.quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));


        //(6)-(1) 螺旋矩阵
//        int[][] matrix = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}};
//        System.out.println((myArray.spiralOrder(matrix)).toString());

        //（6）螺旋矩阵II
//        DzyUtils.printDoubleArray(myArray.generateMatrix(4));

        //测试快排
//        int[] nums = {45,80,55,40,42,85, 41, 90};
//        DzyUtils.printArray(myArray.quickSortTry(nums, 0, nums.length-1));

        //（5）-（1）水果成篮
//        int[] nums = {0,1,1,4,3};
//        System.out.println(myArray.totalFruit1(nums));

        //（5）长度最小的子数组 暴力求解
//        int[] nums = {11};
//        System.out.println(myArray.minSubArrayLen(11, nums));

        //（3.2 方法二）有序数组的平方
//        int[] nums = {-7};
//        DzyUtils.printArray(myArray.sortedSquares1(nums));

        //（3.3）移动零
//        int[] nums = {0};
//        myArray.moveZeroes(nums);

        //（3.2）有序数组的平方
//        int[] nums = {-4};
//        DzyUtils.printArray(myArray.sortedSquares(nums));

        //（3.1）删除有序数组中的重复项
//        int[] nums = { 1,2,3};
//        System.out.println(myArray.removeDuplicates(nums));

        //（3）移除元素
//        int[] nums = { 0,1,2,2,3,0,4,2};
//        System.out.println(myArray.removeElement(nums, 2));

        //（2.1）第一个位置好最后一个位置
//        int[] nums = {1};
//        System.out.println(myArray.searchRange( nums, 1));
//        int[] nums = {5};
//        System.out.println(myArray.searchRange1( nums, 5));

        //（2）二分查找
//        int[] nums = { -1,0,3,5,9,12 };
//        System.out.println(myArray.search( nums, 12));
    }
}
