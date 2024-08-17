package codeFlowIdea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * codeFlowIdea 代码随想录学习记录 time：2024年3月14日15:45:03
 * author：董政宇
 * 第十一部分 图论部分：MyGraph
 */
public class MyGraph {

    // 深度优先 797. 所有可能的路径 time：2024年3月14日15:49:57 -> 2024年3月14日16:17:08
    public List<List<Integer>> allPTResult = new ArrayList<>();
    public List<Integer> allPTPath = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        //从0开始！
        allPTPath.add(0);
        allPTBackTracking(graph, 0);
        return allPTResult;
    }
    public void allPTBackTracking(int[][] graph, int index){
        int[] cur = graph[index];

        for (int i = 0; i < cur.length; i++){
            allPTPath.add(cur[i]); //操作
            if (cur[i] == graph.length - 1){
                allPTResult.add(new ArrayList<>(allPTPath));
            }

            allPTBackTracking(graph, cur[i]); //进入递归访问下一个节点

            allPTPath.remove(allPTPath.size() - 1); //回溯
        }
    }

    // 200. 岛屿数量 time ：2024年3月14日16:41:33 ->
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == '1'){
                    count++;
                    //找到一个1 就要开始淹没大陆
                    numDFS(grid, i, j);
                }
            }
        }
        return count;
    }
    public void numDFS(char[][] grid, int i, int j){
        //如果越界 或者遍历到0 就直接返回 停止递归发散搜索
        if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1 || grid[i][j] == '0'){
            return;
        }

        //淹没大陆为陆地 则最后就不会重复遍历
        grid[i][j] = '0';

        //下面进行上下左右的进行遍历
        numDFS(grid, i - 1, j);
        numDFS(grid, i + 1, j);
        numDFS(grid, i, j + 1);
        numDFS(grid, i, j - 1);

    }

    // 695. 岛屿的最大面积 time：2024年3月14日17:42:04 -> 2024年3月14日17:53:51
    //和统计岛屿数量一样的做法 遍历二维数组+DFS淹没大陆
    public int curSum = 0;
    public int maxAreaOfIsland(int[][] grid) {
        int maxSum = 0;

        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 1){
                    curSum = 0; //每次需要重新设置为0！
                    DFSMax(grid, i, j);
                    maxSum = Math.max(maxSum, curSum);
                }
            }
        }
        return maxSum;
    }
    public void DFSMax(int[][] grid, int i, int j){
        if (i < 0 || i >= grid.length) return;// i越界
        if (j < 0 || j >= grid[0].length) return;// j越界
        if (grid[i][j] == 0) return; //不是大陆 不需要淹没

        //开始操作：淹没+统计面积
        grid[i][j] = 0;
        curSum++;
        //开始上下左右DFS遍历 淹没
        DFSMax(grid, i - 1, j);
        DFSMax(grid, i + 1, j);
        DFSMax(grid, i, j - 1);
        DFSMax(grid, i, j + 1);

    }

    // 1020. 飞地的数量 time：2024年3月14日18:02:15 -> 2024年3月14日18:20:21
    // 此题需要统计所有没越界的岛屿总数量
    public int curSum1020 = 0;
    public boolean isGrid = false; //方便后续判断是否越界！ 默认为false没越界

    public int numEnclaves(int[][] grid) {
        int sum = 0;

        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 1){
                    curSum1020 = 0; //要开始统计了 这里先清0
                    isGrid = false; //这里也要重置越界属性！

                    DFSEnclaves(grid, i, j);

                    if (!isGrid) {
                        //只有不越界的才会被纳入统计
                        sum += curSum1020;
                    }
                }
            }
        }
        return sum;
    }
    public void DFSEnclaves(int[][] grid, int i, int j){
        if (i < 0 || i >= grid.length) {// i越界
            //这里越界 要表明此次淹没是会越界的 则不应该纳入无法移动的数目中
            isGrid = true;
            return;
        }

        if (j < 0 || j >= grid[0].length) { // j越界
            //这里越界 要表明此次淹没是会越界的 则不应该纳入无法移动的数目中
            isGrid = true;
            return;
        }

        if (grid[i][j] == 0) return; //不是大陆 不需要淹没

        //开始操作：淹没+统计个数
        grid[i][j] = 0;
        curSum1020++;
        //开始上下左右DFS遍历 淹没
        DFSEnclaves(grid, i - 1, j);
        DFSEnclaves(grid, i + 1, j);
        DFSEnclaves(grid, i, j - 1);
        DFSEnclaves(grid, i, j + 1);

    }

    // 130. 被围绕的区域 time：2024年3月14日19:00:46 -> 2024年3月14日19:27:28
    //我的做法: 先遍历四个边界 使得所有的O变成Y， 集体遍历所有的内陆O变成X，再遍历一遍把Y变回O
    public void solve(char[][] board) {
        //先访问四个边界 把o变成Y
        //左右边界
        for (int i = 0; i < board.length; i++){
            if (board[i][0] == 'O') DFSSolve(board, i, 0, 'Y');
            if (board[i][board[0].length - 1] == 'O') DFSSolve(board, i, board[0].length - 1, 'Y');
        }
        //上下边界
        for (int i = 0; i < board[0].length; i++){
            if (board[0][i] == 'O') DFSSolve(board, 0, i, 'Y');
            if (board[board.length - 1][i] == 'O') DFSSolve(board, board.length - 1, i, 'Y');
        }

        //遍历所有的不在边界的 把O变成X
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] == 'O'){
                    DFSSolve(board, i, j, 'X');
                }
            }
        }
        //把Y变回O
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] == 'Y'){
                    board[i][j] = 'O';
                }
            }
        }

    }
    public void DFSSolve(char[][] board, int i, int j, char character){
        if (i < 0 || i >= board.length) return;
        if (j < 0 || j >= board[0].length) return;
        if (board[i][j] == 'X') return;
        //所以这里一定要注意！遇到y也一定要返回！
        if (board[i][j] == 'Y') return;

        //开始操作
        board[i][j] = character;
        DFSSolve(board, i - 1, j, character);
        DFSSolve(board, i + 1, j, character);
        DFSSolve(board, i, j - 1, character);
        DFSSolve(board, i, j + 1, character);

    }

    // √ 1971. 寻找图中是否存在路径 time：2024年4月23日20:15:20 -> 2024年4月23日20:34:42
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        //核心思想：就是按照二维数组来造一个并查集（father数组），然后来判断两个点是否存在一个集合里

        //1.并查集的初始化
        int[] father = new int[n];
        for (int i = 0; i < father.length; i++){
            father[i] = i;
        }

        //2.遍历所有边 加入到对应的并查集中去
        for (int i = 0; i < edges.length; i++){
            join(father, edges[i][0], edges[i][1]);
        }

        //3.判断两个节点 是不是在同一个并查集中
        int iRoot = findRoot(father, source);
        int jRoot = findRoot(father, destination);
        return iRoot == jRoot;
    }
    //把边加入到并查集中（并查集中的集合就代表着连通性）
    public void join(int[] father, int i, int j){
        int iRoot = findRoot(father, i);
        int jRoot = findRoot(father, j);
        //只有两个根不一样的时候，才把两个root合并到一起
        if (iRoot != jRoot) {
            father[jRoot] = iRoot;
        }
    }
    //找到当前index的根 root index并返回
    public int findRoot(int[] father, int index){
        if (father[index] == index){
            return index;
        }else {
            //往下递归进行寻找 同时更新缩短路径
            return father[index] = findRoot(father, father[index]);
        }
    }

    // √ 684. 冗余连接 time：2024年4月23日20:36:53 -> 2024年4月23日21:00:03
    public int[] findRedundantConnection(int[][] edges) {
        //核心思想：使用并查集，在加入到并查集的过程中，如果找到iRoot、jRoot是一样的，则表明其已经满足一个树的要求了，不需要继续添加
        //1.并查集的初始化
        int[] father = new int[edges.length];
        for (int i = 0; i < father.length; i++){
            father[i] = i;
        }

        //2.遍历每一条边加入到并查集中
        for (int i = 0; i < edges.length; i++){
            boolean joined = join1(father, edges[i][0] - 1, edges[i][1] - 1);
            if (joined){
                return edges[i];
            }
        }

        return new int[]{-1, -1};
    }
    //并查集操作-加入到并查集操作
    public boolean join1(int[] father, int i, int j){
        int iRoot = findRoot1(father, i);
        int jRoot = findRoot1(father, j);
        if (iRoot == jRoot){
            return true;
        }else {
            father[jRoot] = iRoot;
            return false;
        }
    }
    //并查集操作-递归寻找根root节点
    public int findRoot1(int[] father, int index){
        if (father[index] == index){
            return index;
        }else {
            return father[index] = findRoot1(father, father[index]);
        }
    }




    public static void main(String[] args) {
        int[][] test = {{4,3,1},{3,2,4},{3},{4},{}};

        MyGraph myGraph = new MyGraph();
        System.out.println(myGraph.allPathsSourceTarget(test));

    }
}

