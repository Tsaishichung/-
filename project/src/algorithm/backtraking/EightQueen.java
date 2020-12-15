package algorithm.backtraking;

/**
 * EightQueen N�ʺ����⣨ֻ��ӡ��������������һ�����򣬲����������ȫ������
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��11��17�� 17:48
 */
public class EightQueen {


    public static void main(String[] args) {
        int queenNum = 8;
        int[] queens = new int[queenNum];
        boolean flag = setQueens(queens, 0);
        if(flag){
            printQueens(queens);
        }else{
            System.out.println("�޷���ȷ����8�ʺ󣬳�������");
        }
    }

    private static boolean setQueens(int[] queens, int row){
        //��������queen number��֤���Ѿ��������
        if(row >= queens.length){
            return true;
        }
        //�ӵ�һ�е���queen number�з������ӣ��ж϶Խ��ߺͺ��������ӣ������������һ�����������򷵻�
        for(int i = 0; i < queens.length; i ++){
            if(isQueenCanBeSet(queens, row, i)){
                queens[row] =  i;
                 if(setQueens(queens, row + 1)){
                    return true;
                 }
            }
        }
        return false;
    }
    private static boolean isQueenCanBeSet(int[] queens, int row, int column){
        //�����趨�Ǵ������µģ����Ե�ǰ�����µ�����ȫ������
        int leftUp = column - 1;
        int rightUp = column + 1;
        for(int i = row - 1; i >= 0; i--){
            boolean upFlag = queens[i] == column;
            boolean leftUpFlag = (leftUp >= 0 && leftUp < queens.length && queens[i] == leftUp);
            boolean rightUpFlag = (rightUp >= 0 && rightUp < queens.length && queens[i] == rightUp);
            if(upFlag || leftUpFlag || rightUpFlag){
                return false;
            }
            leftUp--;
            rightUp++;
        }
        return true;
    }
    private static void printQueens(int[] queens){
        for(int queen : queens){
            for(int i = 0; i < queens.length; i++){
                if(i == queen){
                    System.out.print("q ");
                }else{
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }
}
