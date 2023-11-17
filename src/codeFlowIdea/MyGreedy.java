package codeFlowIdea;

import java.util.Arrays;

/**
 * codeFlowIdea 代码随想录学习记录 time：2023年11月17日21:33:13 ->
 * author：董政宇
 * 第九部分 贪心部分：MyGreedy
 */
public class MyGreedy {

    /**
     * （2） 455. 分发饼干 time：2023年11月17日21:36:11 -> 2023年11月17日21:56:15
     */
    public int findContentChildren(int[] g, int[] s) {
        if (s.length == 0) return 0;
        //先直接对g，s进行排序【两边都从最小的开始】
        Arrays.sort(g);
        Arrays.sort(s);
        //下面使用双指针来做
        int curCake = 0; int count = 0;
        for (int i = 0; i < g.length; i++){//以孩子为根本进行遍历！因为目的是满足尽可能多的孩子
            if (curCake >= s.length) return count;
            //找到能够满足当前需求最小孩子的 最小cake
            while (s[curCake] < g[i]){
                curCake++;
                if (curCake >= s.length) return count;
            }
            //找到s了！: s[curCake] >= g[i]
            count++;
            curCake++; // cake消耗
        }
        return count;
    }


    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {

    }
}
