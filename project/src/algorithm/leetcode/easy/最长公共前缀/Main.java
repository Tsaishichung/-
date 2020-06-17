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
        //"","aa","a"
        //"",""
        String[] strs = new String[]{"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 0){
            return "";
        }
        if(strs.length == 1){
            return strs[0];
        }
        //�ҵ���̵��ַ���
        String minLenStr = strs[0];
        int minLenStrIndex = 0;
        for(int i = 1; i < strs.length; i++){
            if(strs[i].length() < minLenStr.length()){
                minLenStr = strs[i];
                minLenStrIndex = i;
            }
        }
        //ͨ������ַ�������Trie��
        TreeNode root = new TreeNode();
        TreeNode currentNode = root;
        char[] minLenStrArr = minLenStr.toCharArray();
        for(int i = 0; i < minLenStrArr.length; i++){
            char value = minLenStrArr[i];
            if(currentNode.children == null){
                currentNode.children = new TreeNode[26];
            }
            TreeNode children = new TreeNode(value, null, currentNode);
            currentNode.children[hash(value)] = children;
            currentNode = children;
        }
        //���������������ַ�������Trie������ĳ���ڵ��д���1�����ӽڵ�ʱ���ء�
        int deep = 0;
        strLoop:for(int i = 0; i < strs.length; i++){
            if(i == minLenStrIndex){
                continue strLoop;
            }
            if(strs[i] == null || "".equals(strs[i])){
                return "";
            }
            char[] charArr = strs[i].toCharArray();
            currentNode = root;
            deep = 0;
            charLoop:for(int j = 0; j < charArr.length; j++){
                TreeNode children;
                if(currentNode.children == null || (children = currentNode.children[hash(charArr[j])]) == null){
                    continue strLoop;
                }
                currentNode = children;
                deep++;
            }
        }
        char[] result = new char[deep + 1];
        for(int i = deep; i >= 0; i--){
            result[i] = currentNode.value;
            currentNode = currentNode.parent;
        }
        return new String(result);

    }

    private static int hash(char c){
        return c - 'a';
    }
}
class TreeNode{

    public char value;

    public TreeNode[] children;

    public TreeNode parent;

    public TreeNode(){}

    public TreeNode(char value, TreeNode[] children, TreeNode parent){
        this.value = value;
        this.children = children;
        this.parent = parent;
    }

}