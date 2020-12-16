package algorithm.backtraking;

/**
 * KnapsackProblem 0-1背包问题（使用回溯算法解决，动态规划better）
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年12月15日 16:44
 */
public class KnapsackProblem {

    public static void main(String[] args) {
        //1.定义背包总质量
        int totalWeight = 100;
        //2.定义各个物品的重量
        //int[] items = new int[]{12, 23, 34, 45, 56, 67, 78, 87, 90, 1};//bug
        //int[] items = new int[]{14, 27, 25, 33, 40, 10, 50, 9, 8, 11};
        //int[] items = new int[]{8, 13, 5, 20, 12, 1, 31, 9, 4, 8};
        int[] items = new int[]{55, 43, 53, 47, 41};
        //3.回溯穷举
        int maxWeight = knapsack(totalWeight, items, 0,0,  0);
        System.out.println("最大重量：" + maxWeight);
    }

    private static int knapsack(int totalWeight, int[] items, int cWeight, int maxWeight, int itemIndex){
        //超出最大重量返回，没有物品可装了返回
        if(cWeight >= totalWeight || itemIndex >= items.length ){
            if(cWeight <= totalWeight && cWeight > maxWeight){
                return cWeight;
            }
            return maxWeight;
        }
        //物品选择,添加或者不添加到背包
        int pkgWeight = 0;
        int noPkgWeight = knapsack(totalWeight, items, cWeight, maxWeight, itemIndex + 1);
        if(cWeight + items[itemIndex] <= totalWeight){
            pkgWeight = knapsack(totalWeight, items, cWeight + items[itemIndex], maxWeight, itemIndex + 1);
        }
        return pkgWeight > noPkgWeight ? pkgWeight : noPkgWeight;
    }

}
