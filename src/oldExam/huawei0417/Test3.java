package oldExam.huawei0417;

import java.util.*;

/**
 * 第三题 time：2024年4月24日16:05:25 -> 2024年4月24日17:22:10
 */
public class Test3 {

    static Set<Integer> set = new HashSet<>();
    static int minTime = Integer.MAX_VALUE;
    static int curTime = 0;

    static List<Integer> path = new ArrayList<>();
    static List<List<Integer>> allPath = new ArrayList<>();

    public static void main(String[] args) {
        //核心思想：DFS深度优先遍历 给定一点 找到可以抵达的每一点的延迟时间
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[][] graph = new int[n][n];

        //开始接受输入
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                graph[i][j] = input.nextInt();
            }
        }

        int[] capacity = new int[n];
        for (int i = 0; i < n; i++){
            capacity[i] = input.nextInt();
        }

        int badOne = input.nextInt();
        int badCapacity = input.nextInt();

        //开始核心逻辑处理：给定一点，dfs遍历其可以抵达的所有点并记录下来

        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]){
                    return o1[0] - o2[0];
                }else {
                    return o1[1] - o2[1];
                }
            }
        });
        Map<Integer, Integer> map = new HashMap<>();
        set.add(badOne);
        path.add(badOne);
        DFSPlus(graph, badOne, map);
//        System.out.println(allPath);
//        System.out.println(map);

//        List<Integer> list = new ArrayList<>(map.keySet().stream().toList());
        List<Integer> list = new ArrayList<>();
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (map.get(o1).equals(map.get(o2))){
                    return o1 - o2;
                }else {
                    return map.get(o1) - map.get(o2);//升序
                }
            }
        });
        System.out.println(list.get(0));


    }

    //感觉只需要一次就可以打印出所有路径
    public static void DFSPlus(int[][] graph, int curIndex, Map<Integer, Integer> map){
        //终止条件
        for (int i = 0; i < graph.length; i++){
            if (set.contains(i)){
                continue;
            }
            if (graph[curIndex][i] != -1){
                curTime += graph[curIndex][i];
                set.add(i);
                path.add(i);
                allPath.add(new ArrayList<>(path));
                map.put(i, Math.min(map.getOrDefault(i, Integer.MAX_VALUE), curTime));

                DFSPlus(graph, i, map); //进入递归

                curTime -= graph[curIndex][i];
                set.remove(i); //回溯
                path.remove(path.size() - 1);
            }
        }
    }



}
