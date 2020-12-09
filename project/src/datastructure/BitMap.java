package datastructure;

/**
 * BitMap 位图简单实现（布隆过滤器基础）
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年12月09日 9:47
 */
public class BitMap {

    public static void main(String[] args) {
        BitMapEntity bitMapEntity = buildBitMap();
        System.out.println("999999999是否存在:" + bitMapEntity.isExsit(999999999));
        System.out.println("999999998是否存在:" + bitMapEntity.isExsit(999999998));
        System.out.println("1是否存在:" + bitMapEntity.isExsit(1));
        System.out.println("333333是否存在:" + bitMapEntity.isExsit(333333));
        System.out.println("10是否存在:" + bitMapEntity.isExsit(10));
    }

    /**
     * BitMap 构建一个10亿大小的范围值，添加若干元素。然后判断某个数字在这个范围内是否存在。
     * @author caizhichong
     * @date 2020/12/9
     * @version V1.0
     */
    private static BitMapEntity buildBitMap(){
        BitMapEntity bitMapEntity = new BitMapEntity(1000000000);
        bitMapEntity.add(12);
        bitMapEntity.add(999999999);
        bitMapEntity.add(123456789);
        bitMapEntity.add(1);
        bitMapEntity.add(333333);
        bitMapEntity.add(10086);
        bitMapEntity.add(110111);
        bitMapEntity.add(4321);
        bitMapEntity.add(9);
        bitMapEntity.add(19);
        bitMapEntity.add(40);
        return bitMapEntity;
    }
}
class BitMapEntity{

    /**
     * 存储数组
     * */
    private int[] bitMap;

    /**
     * 最大值
     * */
    private int max;


    public BitMapEntity(int max){
        this.max = max;
        bitMap = new int[(int)Math.ceil(((double)max/32))];
    }

    public void add(int num){
        if(num > max){
            return;
        }
        int arrIndex = num / 32;
        int bitIndex = num % 32;
        bitMap[arrIndex] |= (1 << bitIndex);
    }

    public boolean isExsit(int num){
        if(num > max){
            return false;
        }
        int arrIndex = num / 32;
        int bitIndex = num % 32;
        return (bitMap[arrIndex] & (1 << bitIndex)) != 0;
    }

}
