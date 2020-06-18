package algorithm.leetcode.easy.最长公共前缀;

/**
 * Main 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串?""。
 *
 * 示例?1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例?2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母?a-z?。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年06月15日 17:41
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
        //构建Trie树
        TreeNode root = new TreeNode();
        //对数组中其他的字符串构建Trie树，当某个节点有大于1个孩子节点时返回。
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