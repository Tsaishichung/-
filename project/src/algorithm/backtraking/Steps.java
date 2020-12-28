package algorithm.backtraking;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Steps 多阶段决策（回溯实现,这个只是一种写法）
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年12月17日 10:24
 */
public class Steps {

    public static void main(String[] args) {
        List<List<Integer>> resultList = new ArrayList<>();
        //在你面前有一个n阶的楼梯(n>=100且n<500)，你一步只能上1阶或3阶。（小米笔试题）
        System.out.println(fibonacci(new BigInteger[101], 100, 0, BigInteger.ZERO, resultList, new ArrayList<>(), 0).toString());
        for(List<Integer> result : resultList){
            for(Integer step : result){
                System.out.print(step + " ");
            }
            System.out.println();
        }
    }
    /**
     * Steps
     * @description
     * @param mem 备忘录（剪枝操作，无法统计出所有路径，所以resultList与剪枝操作互斥，取消剪枝则可以统计出所有路径，但效率低）
     * @param steps 总共步数
     * @param cSteps 当前步数
     * @param cWays 可行方法
     * @param resultList 所有结果集
     * @param result 当前结果集
     * @param cIndex 结果集插入元素下标
     * @return
     * @author caizhichong
     * @date 2020/12/28
     * @version V1.0
     */
    private static BigInteger fibonacci(BigInteger[] mem, int steps, int cSteps, BigInteger cWays, List<List<Integer>> resultList, List<Integer> result, int cIndex){
        if(cSteps <= steps && mem[cSteps] != null){
            return mem[cSteps];
        }
        //大于等于指定楼梯数则停止，刚好等于楼梯数则满足条件，+1返回
        if(cSteps >= steps){
            if(cSteps == steps){
                resultList.add(new ArrayList<>(result.subList(0, cIndex)));
                return cWays.add(BigInteger.valueOf(1L));
            }
            return cWays;
        }
        BigInteger wayHalf1 = BigInteger.ZERO;
        BigInteger WayHalf2 = BigInteger.ZERO;
        //走一步
        if(cSteps + 1 <= steps){
            if(result.size() < cIndex + 1){
                result.add(cIndex,1);
            }else{
                result.set(cIndex,1);
            }
            wayHalf1 = fibonacci(mem, steps, cSteps + 1, cWays, resultList, result, cIndex + 1);
        }
        //走三步
        if(cSteps + 3 <= steps){
            if(result.size() < cIndex + 1){
                result.add(cIndex,3);
            }else{
                result.set(cIndex,3);
            }
            WayHalf2 = fibonacci(mem, steps, cSteps + 3, cWays, resultList, result, cIndex + 1);
        }
        BigInteger totalWay = wayHalf1.add(WayHalf2);
        mem[cSteps] = totalWay;
        return totalWay;
    }


}
