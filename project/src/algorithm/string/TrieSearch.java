package algorithm.string;

/**
 * TrieSearch Trie���ַ���ƥ��
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��07��14�� 18:04
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
     * @description ƥ���ַ���
     * @param target ģʽ��
     * @param trie trie��
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
     * @description ����Trie��
     * @param source Դ����
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
     * @description ��ȡ�ַ��±�λ��
     * @param c Ŀ���ַ�
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
 * @description trie��
 * @author caizhichong
 * @date 2020/7/14
 * @version V1.0
 */
class Trie{

    /**�ַ�ֵ*/
    char value;

    /**�Ƿ�����ַ�*/
    boolean isEndChar;

    /**���ӽڵ�*/
    Trie[] children = new Trie[26];

    /**���ڵ�*/
    Trie parent;

    public Trie() {}

    public Trie(char value, boolean isEndChar, Trie parent) {
        this.value = value;
        this.isEndChar = isEndChar;
        this.parent = parent;
    }
}
