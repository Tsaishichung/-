package algorithm.backtraking;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Steps ��׶ξ��ߣ�����ʵ��,���ֻ��һ��д����
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��12��17�� 10:24
 */
public class Steps {

    public static void main(String[] args) {
        List<List<Integer>> resultList = new ArrayList<>();
        //������ǰ��һ��n�׵�¥��(n>=100��n<500)����һ��ֻ����1�׻�3�ס���С�ױ����⣩
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
     * @param mem ����¼����֦�������޷�ͳ�Ƴ�����·��������resultList���֦�������⣬ȡ����֦�����ͳ�Ƴ�����·������Ч�ʵͣ�
     * @param steps �ܹ�����
     * @param cSteps ��ǰ����
     * @param cWays ���з���
     * @param resultList ���н����
     * @param result ��ǰ�����
     * @param cIndex ���������Ԫ���±�
     * @return
     * @author caizhichong
     * @date 2020/12/28
     * @version V1.0
     */
    private static BigInteger fibonacci(BigInteger[] mem, int steps, int cSteps, BigInteger cWays, List<List<Integer>> resultList, List<Integer> result, int cIndex){
        if(cSteps <= steps && mem[cSteps] != null){
            return mem[cSteps];
        }
        //���ڵ���ָ��¥������ֹͣ���պõ���¥����������������+1����
        if(cSteps >= steps){
            if(cSteps == steps){
                resultList.add(new ArrayList<>(result.subList(0, cIndex)));
                return cWays.add(BigInteger.valueOf(1L));
            }
            return cWays;
        }
        BigInteger wayHalf1 = BigInteger.ZERO;
        BigInteger WayHalf2 = BigInteger.ZERO;
        //��һ��
        if(cSteps + 1 <= steps){
            if(result.size() < cIndex + 1){
                result.add(cIndex,1);
            }else{
                result.set(cIndex,1);
            }
            wayHalf1 = fibonacci(mem, steps, cSteps + 1, cWays, resultList, result, cIndex + 1);
        }
        //������
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
