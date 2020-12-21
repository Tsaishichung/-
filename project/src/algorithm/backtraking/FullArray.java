package algorithm.backtraking;

import java.util.ArrayList;
import java.util.List;

/**
 * FullArray 全排列（回溯算法求解）
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年12月18日 16:41
 */
public class FullArray {

    public static void main(String[] args) {
        //给定一个 没有重复 数字的序列，返回其所有可能的全排列。
        //已通过leetcode（中国）46. 全排列
        //执行结果：通过
        //执行用时：1 ms, 在所有 Java 提交中击败了98.70%的用户
        //内存消耗：38.6 MB, 在所有 Java 提交中击败了87.42%的用户
        // 1 2 3 4 5 -- 前3位固定，最后2位排序
        // 1 2 3 5 4 -- 前3位固定，最后2位排序
        // 1 2 4 5 3 -- 前2位固定，最后3位排序
        // 1 2 4 3 5 -- 前2位固定，最后3位排序
        // 1 2 5 3 4 -- 前2位固定，最后3位排序
        // 1 2 5 4 3 -- 前2位固定，最后3位排序

        // 1 3 2 4 5 -- 前1位固定，最后4位排序
        // 1 3 2 5 4 -- 前1位固定，最后4位排序
        // 1 3 4 5 2 -- 前1位固定，最后4位排序
        // 1 3 4 2 5 -- 前1位固定，最后4位排序
        // 1 3 5 2 4 -- 前1位固定，最后4位排序
        // 1 3 5 4 2 -- 前1位固定，最后4位排序

        // 1 4 2 3 5 -- 前1位固定，最后4位排序
        // 1 4 2 5 3 -- 前1位固定，最后4位排序
        // 1 4 3 2 5 -- 前1位固定，最后4位排序
        // 1 4 3 5 2 -- 前1位固定，最后4位排序
        // 1 4 5 2 3 -- 前1位固定，最后4位排序
        // 1 4 5 3 2 -- 前1位固定，最后4位排序

        // 1 5 2 3 4 -- 前1位固定，最后4位排序
        // 1 5 2 4 3 -- 前1位固定，最后4位排序
        // 1 5 3 2 4 -- 前1位固定，最后4位排序
        // 1 5 3 4 2 -- 前1位固定，最后4位排序
        // 1 5 5 2 3 -- 前1位固定，最后4位排序
        // 1 5 4 3 2 -- 前1位固定，最后4位排序
        int[] array = new int[]{1, 2, 3, 4, 5};
        List<List<Integer>> resultList = permute(array);
        for(List<Integer> result : resultList){
            for(int element : result){
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }


    private static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        fullArray(nums, 0, new boolean[nums.length], resultList, new ArrayList<>());
        return resultList;
    }

    /**
     * FullArray
     * @description
     * @param array 给定数组
     * @param count 当前添加的第count个元素
     * @param visited 数组节点是否访问
     * @param resultList 结果集结合
     * @param result 结果集
     * @return
     * @author caizhichong
     * @date 2020/12/21
     * @version V1.0
     */
    private static void fullArray(int[] array, int count, boolean[] visited, List<List<Integer>> resultList, List<Integer> result){
        //递归终止条件(所有节点均已访问过了)
        if(count == array.length){
            resultList.add(new ArrayList<>(result));
            return;
        }
        for(int i = 0; i < array.length; i++){
            if(visited[i]){
                continue;
            }
            //对第count位赋值
            if(result.size() < count + 1){
                result.add(array[i]);
            }else{
                result.set(count, array[i]);
            }
            visited[i] = true;
            fullArray(array, count + 1, visited, resultList, result);
            visited[i] = false;
        }
    }
}
