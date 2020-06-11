package algorithm.string;

/**
 * BF ×Ö·û´®Æ¥ÅäÆÓËØËã·¨£¨Brute-Force£©,±©Á¦Æ¥Åä
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020Äê06ÔÂ10ÈÕ 14:31
 */
public class BF {

    public static void main(String[] args) {
        String source = "cbacbad";
        String target = "acb";
        int pos = search(source.toCharArray(), target.toCharArray());
        System.out.println(pos);
    }


    /**
     * BF
     * @description BFÆÓËØËã·¨£¬±©Á¦Æ¥Åä
     * @param sourceArr Ô´×Ö·û´®
     * @param targetArr Ä¿±ê×Ö·û´®
     * @return
     * @author caizhichong
     * @date 2020/6/10
     * @version V1.0
     */
    private static int search(char[] sourceArr, char[] targetArr){
        if(targetArr.length == 0 || targetArr.length > sourceArr.length){
            return -1;
        }
        int searchIndex = 0;
        shift:while(searchIndex <= sourceArr.length - targetArr.length){
            int sameIndex = 0;
            fit:while (sameIndex < targetArr.length){
                if(sourceArr[searchIndex + sameIndex] == targetArr[sameIndex]){
                    sameIndex++;
                }else{
                    searchIndex++;
                    continue shift;
                }
            }
            return searchIndex;
        }

        return -1;
    }
}
