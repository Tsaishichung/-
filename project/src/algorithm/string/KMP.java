package algorithm.string;

/**
 * KMP Knuth-Morris-Pratt 字符串匹配算法，与BM算法思想类似。
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年06月10日 16:45
 */
public class KMP {

    public static void main(String[] args) {
        String source = "aabbabxabcabxabxc";
        String target = "abxabcabxabx";
        int pos = kmp(source.toCharArray(), target.toCharArray());
        System.out.println(pos);
    }

    private static int kmp(char[] chars, char[] target){
        int[] nexts = nexts(target);
        //字符串滑动
        int shiftIndex = 0;
        shift:while(shiftIndex <=  chars.length - target.length){
            //模式串匹配
            fit:for(int j = 0; j < target.length; j++){
                if(chars[shiftIndex + j] == target[j]){
                    continue fit;
                }
                //当前下标位置为j的字符为坏字符。最大移动长度为j-next[j]，即最大移动长度为j+1，
                // 最小移动长度取决于nexts[j]的值，即好前缀能不能找到对应的可匹配后缀
                shiftIndex += (j - nexts[j]);
                continue shift;
            }
            return shiftIndex;
        }
        return shiftIndex;
    }
    /**
     * KMP
     * @description 构建好前缀的nexts数组，里面包含了长度为1~模式串长度-1区间的好前缀，
     *              分别存储在nexts数组中下标为i的元素中。
     * @param target 模式串
     * @return
     * @author caizhichong
     * @date 2020/6/24
     * @version V1.0
     */
    private static int[] nexts(char[] target){
        int[] nexts = new int[target.length];
        nexts[0] = -1;
        int k = -1;
        for(int i = 1; i < target.length; i++){
            while(k != -1 && target[k +1] != target[i]){
                k = nexts[k];
            }
            if(target[k+1] == target[i]){
                k++;
            }
            nexts[i] = k;
        }
        return nexts;
    }
}
