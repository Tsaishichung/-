package algorithm.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * TopoSort 拓扑排序
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年11月10日 9:09
 */
public class TopoSort {

    public static void main(String[] args) {
        Graph graph = buildGraph();
        int[] visited = new int[graph.getNodes().length];
        //首先找到邻接表中没有入度的节点作为深度优先遍历的起始节点进行拓扑排序
        List<Integer> reverseStartVertex = graph.getStartNodes();
        for(Integer vertexIndex : reverseStartVertex){
            //dfs topo sort
            dfsTopoSort(graph, vertexIndex, visited);
        }
        //kahn topo sort
        //kahnTopoSort(graph);
    }

    
    /**
     * TopoSort
     * @description kahn 拓扑排序（可以验证是否存在环）
     * @param graph 图结构
     * @return
     * @author caizhichong
     * @date 2020/11/10
     * @version V1.0
     */
    private static void kahnTopoSort(Graph graph){
        Queue<Integer> vertexQuue = new LinkedList<>();
        //添加所有入度为0的顶点进入队列
        for(int i = 0; i < graph.getNodeInDegree().length; i++){
            if(graph.getNodeInDegree()[i] == 0){
                //此处的下标i对应的是
                vertexQuue.add(i);
            }
        }
        Integer vertexIndex;
        while((vertexIndex = vertexQuue.poll()) != null){
            System.out.println("节点:" + graph.getNodes()[vertexIndex].getValue());
            Vertex nextVertex = graph.getNodes()[vertexIndex].getNext();
            while (nextVertex != null){
                int nextVertexIndex = graph.getVertexIndex(nextVertex.getValue());
                graph.getNodeInDegree()[nextVertexIndex]--;
                if(graph.getNodeInDegree()[nextVertexIndex] == 0){
                    vertexQuue.add(nextVertexIndex);
                }
                nextVertex = nextVertex.getNext();
            }
        }
    }


    /**
     * TopoSort
     * @description 基于逆邻接表的深度优先拓扑排序(基于有向无环图，无法验证是否存在环)
     * @param graph 图结构
     * @param nodeIndex 当前节点下标
     * @param visited 访问的节点数组
     * @return
     * @author caizhichong
     * @date 2020/11/10
     * @version V1.0
     */
    private static void dfsTopoSort(Graph graph, int nodeIndex, int[] visited){
        if(visited[nodeIndex] == 1){
            return;
        }
        visited[nodeIndex]++;
        Vertex currentVerext = graph.getReverseNodes()[nodeIndex].getNext();
        while(currentVerext != null){
            dfsTopoSort(graph, graph.getVertexIndex(currentVerext.getValue()), visited);
            currentVerext = currentVerext.getNext();
        }
        System.out.println("节点：" + graph.getReverseNodes()[nodeIndex].getValue());
    }



    private static Graph buildGraph(){
        char[] vertex = new char[]{'a','d','g','f','c','e','b','h','i'};
        Graph graph = new Graph(vertex);
        graph.addEdge('a','g');
        graph.addEdge('a','d');
        graph.addEdge('c','d');
        graph.addEdge('g','f');
        graph.addEdge('e','d');
        graph.addEdge('f','b');
        graph.addEdge('f','h');
        graph.addEdge('e','f');
        graph.addEdge('e','h');
        graph.addEdge('e','i');
        //graph.addEdge('h','e');
        graph.addEdge('h','i');
        return graph;
    }


}


/**
 * TopoSort
 * @description 图
 * @author caizhichong
 * @date 2020/11/10
 * @version V1.0
 */
class Graph {

    /**
     * 顶点集合（邻接表）
     * */
    private Vertex[] nodes;

    /**
     * 顶点集合（逆邻接表）
     * */
    private Vertex[] reverseNodes;

    /**
     * 顶点下标位置(假设只能存在不重复的26英文字母节点)
     * */
    private int[] nodeIndex = new int[26];

    /**
     * 顶点入度
     * */
    private int[] nodeInDegree;


    public Graph(char[] nodes){
        super();
        this.buildNodes(nodes);
    }

    private void buildNodes(char[] nodes){
        this.nodes = new Vertex[nodes.length];
        this.reverseNodes = new Vertex[nodes.length];
        this.nodeInDegree = new int[nodes.length];
        for(int i = 0; i < nodes.length; i++){
            this.nodes[i] = new Vertex(nodes[i], null);
            this.reverseNodes[i] = new Vertex(nodes[i], null);
            this.nodeIndex[this.hashIndex(nodes[i])] = i;
        }
    }

    /**
     * Graph
     * @description 添加两个顶点的边
     * @param from 起始
     * @param to 结束
     * @return
     * @author caizhichong
     * @date 2020/11/10
     * @version V1.0
     */
    public void addEdge(char from, char to){
        Vertex fromVertex = this.getVertex(from);
        Vertex fromHead = fromVertex.getNext();
        Vertex newFromHead = new Vertex(to, null);
        newFromHead.setNext(fromHead);
        fromVertex.setNext(newFromHead);
        Vertex toVertex = this.getReverseVertex(to);
        Vertex toHead = toVertex.getNext();
        Vertex newToHead = new Vertex(from, null);
        newToHead.setNext(toHead);
        toVertex.setNext(newToHead);
        //添加to节点的入度
        this.getNodeInDegree()[this.getVertexIndex(to)]++;
    }

    /**
     * Graph
     * @description 通过节点字符获取节点在节点数组的下标
     * @param ch 字符
     * @return
     * @author caizhichong
     * @date 2020/11/10
     * @version V1.0
     */
    public int getVertexIndex(char ch){
        return this.getNodeIndex()[this.hashIndex(ch)];
    }


    /**
     * Graph
     * @description 通过节点字符获取到邻接表节点
     * @param ch 字符
     * @return
     * @author caizhichong
     * @date 2020/11/10
     * @version V1.0
     */
    public Vertex getVertex(char ch){
        return this.nodes[this.getVertexIndex(ch)];
    }


    /**
     * Graph
     * @description 获取邻接表所有没有入度的顶点
     * @return
     * @author caizhichong
     * @date 2020/11/12
     * @version V1.0
     */
    public List<Integer> getStartNodes(){
        List<Integer> nodeList = new LinkedList<>();
        for(int i = 0; i < this.getNodes().length; i++){
            if(this.getNodes()[i].getNext() == null){
                nodeList.add(i);
            }
        }
        return nodeList;
    }



    /**
     * Graph
     * @description 获取所有没有入度的顶点下标
     * @return
     * @author caizhichong
     * @date 2020/11/10
     * @version V1.0
     */
    public List<Integer> getHasNoIndegreeVertexIndex(){
        List<Integer> vertexIndexList = new LinkedList<>();
        for(int i = 0; i < this.getNodeInDegree().length; i++){
            if(this.getNodeInDegree()[i] == 0){
                vertexIndexList.add(i);
            }
        }
        return vertexIndexList;
    }


    /**
     * Graph
     * @description 通过节点字符获取到逆邻接表节点
     * @param ch 字符
     * @return
     * @author caizhichong
     * @date 2020/11/10
     * @version V1.0
     */
    public Vertex getReverseVertex(char ch){
        return this.reverseNodes[this.getVertexIndex(ch)];
    }


    /**
     * Graph
     * @description 26个英文字母的哈希下标
     * @param ch 字符
     * @return
     * @author caizhichong
     * @date 2020/11/10
     * @version V1.0
     */
    private int hashIndex(char ch){
        return ch - 'a';
    }


    public Vertex[] getNodes() {
        return nodes;
    }

    public void setNodes(Vertex[] nodes) {
        this.nodes = nodes;
    }

    public Vertex[] getReverseNodes() {
        return reverseNodes;
    }

    public void setReverseNodes(Vertex[] reverseNodes) {
        this.reverseNodes = reverseNodes;
    }

    public int[] getNodeIndex() {
        return nodeIndex;
    }

    public void setNodeIndex(int[] nodeIndex) {
        this.nodeIndex = nodeIndex;
    }

    public int[] getNodeInDegree() {
        return nodeInDegree;
    }

    public void setNodeInDegree(int[] nodeInDegree) {
        this.nodeInDegree = nodeInDegree;
    }
}

/**
 * TopoSort
 * @description 图的顶点
 * @author caizhichong
 * @date 2020/11/10
 * @version V1.0
 */
class Vertex {

    /**
     * 顶点值
     * */
    private char value;

    /**
     * 邻接表后继节点
     * */
    private Vertex next;

    public Vertex(char value, Vertex next) {
        this.value = value;
        this.next = next;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public Vertex getNext() {
        return next;
    }

    public void setNext(Vertex next) {
        this.next = next;
    }
}

