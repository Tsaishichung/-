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
        List<Integer> noInDegreeVertiexs = graph.getStartVertexIndex();
        boolean[] visited = new boolean[graph.getVertex().length];
        for(Integer vertex : noInDegreeVertiexs){
            //������ȱ���
            //dfs(graph, vertex, visited);
            //������ȱ���
            bfs(graph, vertex, visited);
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
        /*char[] vertex = new char[]{'a','g','c','f','e','d','b'};
        Graph graph = new Graph(vertex);
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
        char[] vertex = new char[]{'a','g','c','f','e','d','b','i','h','j'};
        Graph graph = new Graph(vertex);
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
     * @param vertexIndex ��ǰ���������±�
     * @param visit ���ʹ��Ľڵ�
     * @return
     * @author caizhichong
     * @date 2020/7/31
     * @version V1.0
     */
    private static void dfs(Graph graph, int vertexIndex, boolean[] visit){
        if(visit[vertexIndex]){
            return;
        }
        visit[vertexIndex] = true;
        System.out.println("�ڵ�:" + graph.getVertex()[vertexIndex]);
        for(int i = 0; i < graph.getMatrix()[vertexIndex].length; i++){
            if(graph.getMatrix()[vertexIndex][i] == 1){
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
    private static void bfs(Graph graph, int vertexIndex, boolean[] visit) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertexIndex);
        Integer node;
        while((node = queue.poll()) != null){
            if(visit[node]){
                continue;
            }
            System.out.println("�ڵ㣺" + graph.getVertex()[node]);
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
    private char [] vertex;

    /**
     * �û���¼ÿ��������±�λ�ã�����������ֻ��Ӣ����ĸ26�����㣩
     * */
    private int[] charIndex = new int[26];

    /**
     * �ڽӾ���
     * */
    private int[][] matrix;

    public Graph(char [] vertex){
        this.vertex = vertex;
        this.init();
    }

    private void init(){
        for(int i = 0; i < this.getVertex().length; i++){
            this.charIndex[this.hashIndex(this.getVertex()[i])] = i;
        }
        matrix = new int[vertex.length][vertex.length];
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
    private int hashIndex(char vertex){
        return vertex - 'a';
    }

    public char[] getVertex() {
        return vertex;
    }

    public void setVertex(char[] vertex) {
        this.vertex = vertex;
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
    public List<Integer> getStartVertexIndex(){
        List<Integer> noInDegreeVertex = new LinkedList<>();
        outer:for(int i = 0; i < this.getMatrix().length;i++){
            inner:for(int j = 0; j < this.getMatrix()[i].length; j++){
                if(this.getMatrix()[j][i] == 1){
                    continue outer;
                }
            }
            noInDegreeVertex.add(i);
        }
        return noInDegreeVertex;
    }

}
