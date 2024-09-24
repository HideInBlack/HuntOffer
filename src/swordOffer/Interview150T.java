package swordOffer;

import java.util.*;

public class Interview150T {

    //（82）46. 全排列 time：

    //（83）35. 搜索插入位置 time：2024年9月24日10点56分 ->
    public int searchInsert(int[] nums, int target) {
        return searchInsertBinary(nums, target, 0, nums.length - 1);
    }
    private static int searchInsertBinary(int[] nums, int target, int left, int right) {
        if (left == right && nums[left] != target){
            if (target < nums[left]) return left;
            if (target > nums[right]) return left + 1;
        }

        int mid = (left + right) / 2;
        if (nums[mid] == target) {
            return mid; // 中间
        } else if (nums[mid] < target && (mid + 1) <= right){
            return searchInsertBinary(nums, target, mid + 1, right); // 左边
        } else if (nums[mid] > target && left <= mid - 1){
            return searchInsertBinary(nums, target, left, mid - 1); // 右边
        } else {
            return mid;
        }
    }

    //（84）34. 在排序数组中查找元素的第一个和最后一个位置 time：2024年9月24日12点20分 -> 12点38分
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0){
            return new int[]{-1, -1};
        }
        int[] result = new int[2];
        int index = searchRangeBinary(nums, target, 0, nums.length - 1);
        // 1.没有找到的情况
        if (nums[index] != target){
            result[0] = -1;
            result[1] = -1;
            return result;
        }

        // 2.找到的情况
        // 2.1 找左边界
        for (int i = index; i >= 0; i--){
            if (nums[i] == nums[index]) {
                result[0] = i;
            } else {
                break;
            }
        }
        // 2.2 找到右边界
        for (int i = index; i < nums.length; i++){
            if (nums[i] == nums[index]){
                result[1] = i;
            } else {
                break;
            }
        }
        return result;
    }
    private static int searchRangeBinary(int[] nums, int target, int left, int right) {
        int mid = (left + right) / 2;
        if (nums[mid] == target){
            return mid;
        } else if (nums[mid] > target && left <= (mid - 1)){ // 左边并且没有越界
            return searchRangeBinary(nums, target, left, mid - 1);
        } else if (nums[mid] < target && (mid + 1) <= right){ // 右边并且没有越界
            return searchRangeBinary(nums, target, mid + 1, right);
        } else {
            return mid; // 再递归会越界，因此返回当前mid
        }
    }

    //（85）值1-13对应二叉搜索树在长13数组中的顺序 time：2024年9月24日12点39分 -> 12点46分
    private static TreeNode buildBST(int[] nums, int left, int right){
        if (left <= right){
            int mid = (left + right) / 2;
            TreeNode root = new TreeNode(nums[mid]);
            root.left = buildBST(nums, left, mid - 1);
            root.right = buildBST(nums, mid + 1, right);
            return root;
        }
        return null;
    }
    // 层序遍历二叉树
    private static void levelPrint(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();

        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);

        while (!deque.isEmpty()){
            List<Integer> level = new ArrayList<>();
            int size = deque.size();

            for (int i = 0; i < size; i++){
                TreeNode cur = deque.removeFirst();
                level.add(cur.val);
                if (cur.left != null) deque.addLast(cur.left);
                if (cur.right != null) deque.addLast(cur.right);
            }
            result.add(level);
        }
        System.out.println(result);
    }




    public static void main(String[] args) {
//        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
//        TreeNode root = buildBST(nums, 0, nums.length - 1);
//        levelPrint(root);

        String input = "255.1.1.1";
        String[] strings = input.split("\\.");
        long result = 0;
        for (int i = 0; i < strings.length; i++){
            long cur = Long.parseLong(strings[i]);
            result += cur << 8 * (3 - i);
        }
        System.out.println(result);

        long ipInt = 4278255873L;
        long cur1 = ipInt & 255;
        System.out.println(cur1);

        long cur2 = (ipInt >> 8) & 255;
        System.out.println(cur2);

        long cur3 = (ipInt >> 16) & 255;
        System.out.println(cur3);

        long cur4 = (ipInt >> 24) & 255;
        System.out.println(cur4);
    }

}
