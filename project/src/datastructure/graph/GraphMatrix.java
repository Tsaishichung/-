package datastructure.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * GraphMatrix 图-邻接矩阵
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年07月30日 8:53
 */
public class GraphMatrix {

    public static void main(String[] args) {
        Graph graph = buildGraph();
        List<Integer> noInDegreeVertiexs = graph.getStartVertixIndex();
        boolean[] visited = new boolean[graph.getVertix().length];
        for(Integer vertix : noInDegreeVertiexs){
            //深度优先遍历
            dfs(graph, vertix, visited);
            //广度优先遍历
            //bfs(graph, vertix, visited);
        }
    }


    /**
     * GraphMatrix
     * @description 构建图
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
     * @description 深度优先遍历
     * @param graph 图结构
     * @param vertixIndex 当前遍历顶点下标
     * @param visit 访问过的节点
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
        System.out.println("节点:" + graph.getVertix()[vertixIndex]);
        for(int i = 0; i < graph.getMatrix()[vertixIndex].length; i++){
            if(graph.getMatrix()[vertixIndex][i] == 1){
                dfs(graph, i, visit);
            }
        }
    }


    /**
     * GraphMatrix
     * @description 广度优先遍历
     * @param graph 图结构
     * @param visit 访问过的节点
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
            System.out.println("节点：" + graph.getVertix()[node]);
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
 * @description 图数据结构
 * @author caizhichong
 * @date 2020/10/30
 * @version V1.0
 */
class Graph{

    /**
     * 顶点
     * */
    private char [] vertix;

    /**
     * 用户记录每个顶点的下标位置（这里假设最多只有英文字母26个顶点）
     * */
    private int[] charIndex = new int[26];

    /**
     * 邻接矩阵
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
     * 添加边
     * */
    public void addEdge(char from, char to){
        int fromIndex = this.getCharIndex()[this.hashIndex(from)];
        int toIndex = this.getCharIndex()[this.hashIndex(to)];
        this.matrix[fromIndex][toIndex] = 1;
    }


    /**
     * Graph
     * 简写哈希定位顶点下标
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
     * @description  获取任意一个没有入度的节点作为起点(获取对应顶点下标)
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
