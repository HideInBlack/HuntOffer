package util;


public class DzyUtils {

    /**
     * 打印一维数组
     * @param nums
     */
    public static void printArray(int[] nums){
        for (int i = 0; i < nums.length; i++){
            if (i == 0){System.out.print("[");}
            System.out.print(nums[i]);
            if (i == nums.length - 1){System.out.println("]");}
            else {
                System.out.print(", ");
            }
        }
    }

    /**
     * 打印二维数组
     * @param nums
     */
    public static void printDoubleArray(int[][] nums){
        int y = nums.length;
        int x = nums[0].length;
        for (int j = 0; j < y; j++){
            for (int i = 0; i < x; i++){
                System.out.print(nums[j][i]);
                if (i == nums.length - 1){System.out.println();}
                else {
                    System.out.print(", ");
                }
            }
        }

    }


//    public static void main(String[] args) {
//        int[][] nums = {{1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}};
//        DzyUtils.printDoubleArray(nums);
//    }
}
