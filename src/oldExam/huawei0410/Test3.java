package oldExam.huawei0410;

import java.util.*;

/**
 * 华为 第三题 （已过测试用例 9/15）time：2024年4月23日15:42:22 -> 2024年4月23日16:32:47
 */
public class Test3 {

    static Integer count = 0;
    static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                graph[i][j] = input.nextInt();
            }
        }
        input.nextLine();
        String[] exposed = input.nextLine().split(" ");

        //核心思想：遍历一个exposed节点，看哪一个dfs的节点数量最多
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o2[1] == o1[1]){
                    return o1[0] - o2[0];
                }else {
                    return o2[1] - o1[1];
                }
            }
        });
        for (int i = 0; i < exposed.length; i++){
            count = 0;
            set.clear();
            int index = Integer.parseInt(exposed[i]);
            DFS(graph, index, 10);
            queue.add(new int[]{index, count});
        }

        //开始打印
        System.out.println(queue.peek()[0]);
    }

    private static void DFS(int[][] graph, int curIndex, int p){
        set.add(curIndex);
        count++; //数量+1
        //终止条件？
        for (int i = 0; i < graph.length; i++){
            if (set.contains(i)) continue;

            if (graph[curIndex][i] > 0 && p >= graph[curIndex][i]){
                DFS(graph, i, graph[curIndex][i]);
            }
        }
    }
}
