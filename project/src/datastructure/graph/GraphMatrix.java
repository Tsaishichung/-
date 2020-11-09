package datastructure.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * GraphMatrix ͼ-�ڽӾ���
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��07��30�� 8:53
 */
public class GraphMatrix {

    public static void main(String[] args) {
        Graph graph = buildGraph();
        List<Integer> noInDegreeVertiexs = graph.getStartVertixIndex();
        boolean[] visited = new boolean[graph.getVertix().length];
        for(Integer vertix : noInDegreeVertiexs){
            //������ȱ���
            dfs(graph, vertix, visited);
            //������ȱ���
            //bfs(graph, vertix, visited);
        }
    }


    /**
     * GraphMatrix
     * @description ����ͼ
     * @param
     * @return
     * @author caizhichong
     * @date 2020/10/30
     * @version V1.0
     */
    private static Graph buildGraph(){
        /*char[] vertix = new char[]{'a','g','c','f','e','d','b'};
        Graph graph = new Graph(vertix);
        graph.addEdge('a', 'b');
        graph.addEdge('a', 'c');
        graph.addEdge('b', 'c');
        graph.addEdge('b', 'd');
        graph.addEdge('c', 'd');
        graph.addEdge('c', 'g');
        graph.addEdge('d', 'e');
        graph.addEdge('d', 'f');
        graph.addEdge('e', 'f');
        graph.addEdge('g', 'f');*/
        char[] vertix = new char[]{'a','g','c','f','e','d','b','i','h','j'};
        Graph graph = new Graph(vertix);
        graph.addEdge('a', 'b');
        graph.addEdge('a', 'c');
        graph.addEdge('b', 'g');
        graph.addEdge('g', 'f');
        graph.addEdge('g', 'h');
        graph.addEdge('g', 'i');
        graph.addEdge('d', 'c');
        graph.addEdge('d', 'e');
        graph.addEdge('e', 'i');
        graph.addEdge('c', 'g');
        graph.addEdge('b', 'f');
        return graph;
    }


    /**
     * GraphMatrix
     * @description ������ȱ���
     * @param graph ͼ�ṹ
     * @param vertixIndex ��ǰ���������±�
     * @param visit ���ʹ��Ľڵ�
     * @return
     * @author caizhichong
     * @date 2020/7/31
     * @version V1.0
     */
    private static void dfs(Graph graph, int vertixIndex, boolean[] visit){
        if(visit[vertixIndex]){
            return;
        }
        visit[vertixIndex] = true;
        System.out.println("�ڵ�:" + graph.getVertix()[vertixIndex]);
        for(int i = 0; i < graph.getMatrix()[vertixIndex].length; i++){
            if(graph.getMatrix()[vertixIndex][i] == 1){
                dfs(graph, i, visit);
            }
        }
    }


    /**
     * GraphMatrix
     * @description ������ȱ���
     * @param graph ͼ�ṹ
     * @param visit ���ʹ��Ľڵ�
     * @return
     * @author caizhichong
     * @date 2020/7/31
     * @version V1.0
     */
    private static void bfs(Graph graph, int vertixIndex, boolean[] visit) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertixIndex);
        Integer node;
        while((node = queue.poll()) != null){
            if(visit[node]){
                continue;
            }
            System.out.println("�ڵ㣺" + graph.getVertix()[node]);
            visit[node] = true;
            for(int i = 0; i < graph.getMatrix()[node].length; i++){
                if(graph.getMatrix()[node][i] == 1){
                    queue.add(i);
                }
            }
        }
    }

}


/**
 * GraphMatrix
 * @description ͼ���ݽṹ
 * @author caizhichong
 * @date 2020/10/30
 * @version V1.0
 */
class Graph{

    /**
     * ����
     * */
    private char [] vertix;

    /**
     * �û���¼ÿ��������±�λ�ã�����������ֻ��Ӣ����ĸ26�����㣩
     * */
    private int[] charIndex = new int[26];

    /**
     * �ڽӾ���
     * */
    private int[][] matrix;

    public Graph(char [] vertix){
        this.vertix = vertix;
        this.init();
    }

    private void init(){
        for(int i = 0; i < this.getVertix().length; i++){
            this.charIndex[this.hashIndex(this.getVertix()[i])] = i;
        }
        matrix = new int[vertix.length][vertix.length];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                matrix[i][j] = -1;
            }
        }
    }

    /**
     * ��ӱ�
     * */
    public void addEdge(char from, char to){
        int fromIndex = this.getCharIndex()[this.hashIndex(from)];
        int toIndex = this.getCharIndex()[this.hashIndex(to)];
        this.matrix[fromIndex][toIndex] = 1;
    }


    /**
     * Graph
     * ��д��ϣ��λ�����±�
     * @version V1.0
     */
    private int hashIndex(char vertix){
        return vertix - 'a';
    }

    public char[] getVertix() {
        return vertix;
    }

    public void setVertix(char[] vertix) {
        this.vertix = vertix;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int[] getCharIndex() {
        return charIndex;
    }

    public void setCharIndex(int[] charIndex) {
        this.charIndex = charIndex;
    }

    /**
     * Graph
     * @description  ��ȡ����һ��û����ȵĽڵ���Ϊ���(��ȡ��Ӧ�����±�)
     * @return
     * @author caizhichong
     * @date 2020/11/3
     * @version V1.0
     */
    public List<Integer> getStartVertixIndex(){
        List<Integer> noInDegreeVertix = new LinkedList<>();
        outer:for(int i = 0; i < this.getMatrix().length;i++){
            inner:for(int j = 0; j < this.getMatrix()[i].length; j++){
                if(this.getMatrix()[j][i] == 1){
                    continue outer;
                }
            }
            noInDegreeVertix.add(i);
        }
        return noInDegreeVertix;
    }

}
