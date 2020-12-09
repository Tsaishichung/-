package datastructure;

/**
 * BitMap λͼ��ʵ�֣���¡������������
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��12��09�� 9:47
 */
public class BitMap {

    public static void main(String[] args) {
        BitMapEntity bitMapEntity = buildBitMap();
        System.out.println("999999999�Ƿ����:" + bitMapEntity.isExsit(999999999));
        System.out.println("999999998�Ƿ����:" + bitMapEntity.isExsit(999999998));
        System.out.println("1�Ƿ����:" + bitMapEntity.isExsit(1));
        System.out.println("333333�Ƿ����:" + bitMapEntity.isExsit(333333));
        System.out.println("10�Ƿ����:" + bitMapEntity.isExsit(10));
    }

    /**
     * BitMap ����һ��10�ڴ�С�ķ�Χֵ���������Ԫ�ء�Ȼ���ж�ĳ�������������Χ���Ƿ���ڡ�
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
     * �洢����
     * */
    private int[] bitMap;

    /**
     * ���ֵ
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
