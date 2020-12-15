package algorithm.backtraking;

/**
 * EightQueen N皇后问题（只打印满足条件的其中一个排序，不包含满足的全部排序）
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年11月17日 17:48
 */
public class EightQueen {


    public static void main(String[] args) {
        int queenNum = 8;
        int[] queens = new int[queenNum];
        boolean flag = setQueens(queens, 0);
        if(flag){
            printQueens(queens);
        }else{
            System.out.println("无法正确放置8皇后，程序有误");
        }
    }

    private static boolean setQueens(int[] queens, int row){
        //行数大于queen number，证明已经排序完毕
        if(row >= queens.length){
            return true;
        }
        //从第一列到第queen number列放置棋子，判断对角线和横纵有棋子，满足则进行下一步，不满足则返回
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
        //由于设定是从上至下的，所以当前行以下的行列全部忽略
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
