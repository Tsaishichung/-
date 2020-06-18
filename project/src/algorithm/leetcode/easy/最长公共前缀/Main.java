package algorithm.leetcode.easy.�����ǰ׺;

/**
 * Main ��дһ�������������ַ��������е������ǰ׺��
 *
 * ��������ڹ���ǰ׺�����ؿ��ַ���?""��
 *
 * ʾ��?1:
 *
 * ����: ["flower","flow","flight"]
 * ���: "fl"
 * ʾ��?2:
 *
 * ����: ["dog","racecar","car"]
 * ���: ""
 * ����: ���벻���ڹ���ǰ׺��
 * ˵��:
 *
 * ��������ֻ����Сд��ĸ?a-z?��
 *
 * ��Դ�����ۣ�LeetCode��
 * ���ӣ�https://leetcode-cn.com/problems/longest-common-prefix
 * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��06��15�� 17:41
 */
public class Main {

    public static void main(String[] args) {
        //"flower","flow","flight"
        //"aaa","aa","aaa"
        //"aaa","aa","a"
        //"a","aa","aaa"
        //"","aa","a"
        //"",""
        //"aaa","bbb","ccc"
        //"aaa","bb","c"
        String[] strs = new String[]{"a","aa","aaa"};
        System.out.println(longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 0){
            return "";
        }
        if(strs.length == 1){
            return strs[0];
        }
        //����Trie��
        TreeNode root = new TreeNode();
        //���������������ַ�������Trie������ĳ���ڵ��д���1�����ӽڵ�ʱ���ء�
        int deep = 0;
        TreeNode targetChild = null;
        strLoop:for(int i = 0; i < strs.length; i++){
            if(strs[i] == null || "".equals(strs[i])){
                return "";
            }
            char[] charArr = strs[i].toCharArray();
            TreeNode currentNode = root;
            int charDeep = 0;
            charLoop:for(int j = 0; j < charArr.length; j++){
                TreeNode children;
                if(currentNode.children == null){
                    currentNode.children = new TreeNode[26];
                }
                if((children = currentNode.children[hash(charArr[j])]) == null){
                    children = new TreeNode(charArr[j], null, currentNode, 0);
                    currentNode.children[hash(charArr[j])] = children;
                    currentNode.childrenSize++;
                }
                if(currentNode.childrenSize > 1){
                    deep = charDeep;
                    targetChild = currentNode;
                    continue strLoop;
                }
                if(deep!=0 && charDeep == deep){
                    continue strLoop;
                }
                if(j == charArr.length-1){
                    charDeep++;
                    deep = charDeep;
                    targetChild = children;
                    continue strLoop;
                }
                currentNode = children;
                charDeep++;

            }
        }
        char[] result = new char[deep];
        for(int i = deep -1; i >= 0; i--){
            result[i] = targetChild.value;
            targetChild = targetChild.parent;
        }
        return new String(result);

    }

    private static int hash(char c){
        return c - 'a';
    }
}
class TreeNode{

    public char value;

    public int childrenSize;

    public TreeNode[] children;

    public TreeNode parent;

    public TreeNode(){}

    public TreeNode(char value, TreeNode[] children, TreeNode parent, int childrenSize){
        this.value = value;
        this.children = children;
        this.parent = parent;
        this.childrenSize = childrenSize;
    }

}