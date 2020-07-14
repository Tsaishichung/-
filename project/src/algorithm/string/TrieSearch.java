package algorithm.string;

/**
 * TrieSearch Trie树字符串匹配
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年07月14日 18:04
 */
public class TrieSearch {

    public static void main(String[] args) {
        String[] trieStrs = new String[]{"abcd","bcd","cdef","ab"};
        String target = "abc";
        Trie trie = build(trieStrs);
        boolean matched = match(target.toCharArray(), trie);
        System.out.println(matched);
    }


    /**
     * TrieSearch
     * @description 匹配字符串
     * @param target 模式串
     * @param trie trie树
     * @return
     * @author caizhichong
     * @date 2020/7/14
     * @version V1.0
     */
    private static boolean match(char[] target, Trie trie){
        int matchIndex = 0;
        for(char c : target){
            int index = hashIndex(c);
            Trie current = trie.children[index];
            if(current == null || current.value != c){
                return false;
            }else if(current.isEndChar && (target.length - 1 == matchIndex)){
                return true;
            }
            matchIndex++;
            trie = current;
        }
        return false;
    }


    /**
     * TrieSearch
     * @description 构建Trie树
     * @param source 源数据
     * @return
     * @author caizhichong
     * @date 2020/7/14
     * @version V1.0
     */
    private static Trie build(String[] source){
        Trie root = new Trie();
        for(String str : source){
            int strIndex = 0;
            Trie current = root;
            char[] chars = str.toCharArray();
            for(char c : chars){
                int index = hashIndex(c);
                Trie child = current.children[index];
                if(child == null){
                    child = new Trie(c, ++strIndex == str.length(), current);
                    current.children[index] = child;
                }else{
                    if(++strIndex == str.length()){
                        child.isEndChar = true;
                        current.children[index] = child;
                    }
                }
                current = child;
            }
        }
        return root;
    }

    /**
     * TrieSearch
     * @description 获取字符下标位置
     * @param c 目标字符
     * @return
     * @author caizhichong
     * @date 2020/7/14
     * @version V1.0
     */
    private static int hashIndex(char c){
        return c - 'a';
    }
}

/**
 * TrieSearch
 * @description trie树
 * @author caizhichong
 * @date 2020/7/14
 * @version V1.0
 */
class Trie{

    /**字符值*/
    char value;

    /**是否结束字符*/
    boolean isEndChar;

    /**孩子节点*/
    Trie[] children = new Trie[26];

    /**父节点*/
    Trie parent;

    public Trie() {}

    public Trie(char value, boolean isEndChar, Trie parent) {
        this.value = value;
        this.isEndChar = isEndChar;
        this.parent = parent;
    }
}
