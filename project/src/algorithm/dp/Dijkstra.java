package algorithm.dp;


import java.util.*;

/**
 * Dijkstra 单源最短路径算法
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年11月12日 15:08
 */
public class Dijkstra {

    public static void main(String[] args) {
        Graph graph = buildGraph();
        dijkstra(graph, 'a', 'h');
    }


    /**
     * Dijkstra dijkstra 可以基于图的深度优先和广度优先遍历遍历所有节点实现，但这样成本会很高，所以它使用优先级队列
     * 使得每次处理的节点都是权值最小的节点，以此类推直到找到目标节点，输出目标节点的最小路径。
     * 所有顶点路径默认值为Integer最大值。
     * @param graph 构建的图
     * @param from 起始节点名称
     * @param to 终止节点名称
     * @return
     * @date 2020/12/7
     * @version V1.0
     */
    private static void dijkstra(Graph graph, char from, char to){
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        Vertex fromVertext = graph.getVertext(from);
        fromVertext.setDist(0);
        priorityQueue.add(fromVertext);
        Vertex currentVertext;
        while((currentVertext = priorityQueue.poll()) != null){
            if(currentVertext.getValue() == to){
                System.out.println("最短路径为：" + currentVertext.getDist());
                printPath(currentVertext);
                return;
            }
            for(int i = 0; i < currentVertext.getEdges().size(); i++){
                //获得当前顶点到另外一条顶点加上权值的路径值
                //防止Integer值溢出。
                int fromValue = currentVertext.getDist();
                if(fromValue != Integer.MAX_VALUE){
                    fromValue = (Integer.MAX_VALUE - currentVertext.getDist() - currentVertext.getEdges().get(i).getWeight()) > 0 ? currentVertext.getDist() + currentVertext.getEdges().get(i).getWeight() : Integer.MAX_VALUE;
                }
                //获取当前顶点的已经更新的路径值，默认是Integer.MAX
                Vertex toVertex = graph.getVertices()[currentVertext.getEdges().get(i).getToIndex()];
                //当前节点连接到目标节点的权值小于当前节点的权值，后续操作才有意义，否则当前路径已经不是最小了。没有操作的必要
                if(fromValue < toVertex.getDist()){
                    toVertex.setDist(fromValue);
                    toVertex.setPrev(currentVertext);
                    //判断节点是否已经更新过了，为了方便处理优先级队列是更新还是插入，同一队列同一节点不能出现两次。
                    if(!toVertex.isVisited()){
                        //新的节点，直接加入优先级队列（即路径值为Integer.MAX的情况）
                        toVertex.setVisited(true);
                        priorityQueue.add(toVertex);
                    }
                }
            }
            //这里因为有更新队列的值，队列并不会堆化处理，可以自己写一个小顶堆，更新值也能触发堆化。
            //我们这里利用优先级队列的构造函数强行堆化(只能通过Vertex实现Comparable进行排序，不能通过构造函数自定义Compartor)
            priorityQueue = new PriorityQueue<>(Arrays.asList(priorityQueue.toArray(new Vertex[0])));
        }
    }

    private static void printPath(Vertex vertex){
        if(vertex == null){
            return;
        }
        System.out.print("<-" + vertex.getValue());
        printPath(vertex.getPrev());
    }



    private static Graph buildGraph(){
        char[] vertex = new char[]{'a','g','c','f','e','d','b','i','h','j'};
        Graph graph = new Graph(vertex);
        /*graph.addEdge('a', 'b', 8);
        graph.addEdge('a', 'c', 6);
        graph.addEdge('g', 'f', 7);
        graph.addEdge('g', 'h', 11);
        graph.addEdge('g', 'i', 1);
        graph.addEdge('d', 'c', 24);
        graph.addEdge('d', 'e', 19);
        graph.addEdge('e', 'i', 11);
        graph.addEdge('c', 'g', 18);
        graph.addEdge('b', 'f', 14);
        graph.addEdge('b', 'g', 12);*/
        graph.addEdge('a','g', 8);
        graph.addEdge('f','a', 5);
        graph.addEdge('a','c', 6);
        graph.addEdge('c','f', 17);
        graph.addEdge('c','b', 18);
        graph.addEdge('f','b', 34);
        graph.addEdge('g','c', 9);
        graph.addEdge('g','e', 24);
        graph.addEdge('c','d', 21);
        graph.addEdge('c','e', 7);
        graph.addEdge('b','j', 19);
        graph.addEdge('b','h', 11);
        graph.addEdge('b','d', 25);
        graph.addEdge('d','j', 17);
        graph.addEdge('d','h', 22);
        graph.addEdge('d','i', 8);
        graph.addEdge('e','d', 9);
        graph.addEdge('e','i', 11);
        graph.addEdge('i','h', 1);
        return graph;
    }
}

/**
 * Dijkstra
 * @description 图
 * @author caizhichong
 * @date 2020/11/17
 * @version V1.0
 */
class Graph{

    /**
     * 顶点集合
     * */
    private Vertex[] vertices;

    /**
     * 顶点下标（根据定制值哈希定位下标）
     * */
    private int[] verticesIndexs;


    public Graph(char[] vertices){
        super();
        this.init(vertices);
    }

    private void init(char[] vertices){
        this.vertices = new Vertex[vertices.length];
        this.verticesIndexs = new int[vertices.length];
        for(int i = 0; i < vertices.length; i++){
            this.vertices[i] = new Vertex(vertices[i]);
            this.verticesIndexs[this.hashIndex(vertices[i])] = i;
        }
    }

    private int hashIndex(char vertex){
        return vertex - 'a';
    }

    /**
     * Graph
     * @description  添加边
     * @date 2020/11/23
     * @version V1.0
     */
    public void addEdge(char from, char to, int weight){
        int vertexFromIndex = this.getVertexIndex(from);
        int vertexToIndex = this.getVertexIndex(to);
        Vertex vertexFrom = this.vertices[vertexFromIndex];
        Vertex vertexTo = this.vertices[vertexToIndex];
        Edge fromTo = new Edge(vertexFromIndex, vertexToIndex, weight);
        Edge toFrom = new Edge(vertexToIndex, vertexFromIndex, weight);
        vertexFrom.addEdge(fromTo);
        vertexTo.addReverseEdge(toFrom);
    }


    /**
     * Graph
     * @description 通过顶点值获取顶点在顶点数组的下标
     * @param vertex 顶点值
     * @return
     * @author caizhichong
     * @date 2020/11/17
     * @version V1.0
     */
    public int getVertexIndex(char vertex){
        return this.verticesIndexs[this.hashIndex(vertex)];
    }

    /**
     * Graph
     * @description 通过顶点值获取顶点对象
     * @param vertex 顶点
     * @return
     * @author caizhichong
     * @date 2020/11/17
     * @version V1.0
     */
    public Vertex getVertext(char vertex){
        return this.vertices[this.getVertexIndex(vertex)];
    }


    public Vertex[] getVertices() {
        return vertices;
    }

    public void setVertices(Vertex[] vertices) {
        this.vertices = vertices;
    }

    public int[] getVerticesIndexs() {
        return verticesIndexs;
    }

    public void setVerticesIndexs(int[] verticesIndexs) {
        this.verticesIndexs = verticesIndexs;
    }
}

/**
 * Dijkstra 顶点
 * @author caizhichong
 * @date 2020/11/17
 * @version V1.0
 */
class Vertex implements Comparable<Vertex>{

    /**
     * 顶点值
     * */
    private char value;

    /**
     * 连接的边
     * */
    private List<Edge> edges;

    /**
     * 到当前顶点的距离
     * */
    private int dist;

    /**
     * 是否访问过滤
     * */
    private boolean isVisited;

    /**
     * 最短路径前驱节点
     * */
    private Vertex prev;

    /**
     * 被连接的边
     * */
    private List<Edge> reverseEdges;

    public Vertex(char value) {
        this.value = value;
        this.dist = Integer.MAX_VALUE;
        this.edges = new ArrayList<>();
    }

    public void addEdge(Edge edge){
        this.edges.add(edge);
    }

    public void addReverseEdge(Edge edge){
        if(this.reverseEdges == null){
            this.reverseEdges = new ArrayList<>();
        }
        this.reverseEdges.add(edge);
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public List<Edge> getReverseEdges() {
        return reverseEdges;
    }

    public void setReverseEdges(List<Edge> reverseEdges) {
        this.reverseEdges = reverseEdges;
    }

    public Integer getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public Vertex getPrev() {
        return prev;
    }

    public void setPrev(Vertex prev) {
        this.prev = prev;
    }

    @Override public int compareTo(Vertex o) {
        return this.getDist().compareTo(o.getDist());
    }
}

/**
 * Dijkstra 边
 * @author caizhichong
 * @date 2020/11/17
 * @version V1.0
 */
class Edge{


    /**
     * 被连接节点下标
     * */
    private int fromIndex;

    /**
     * 连接节点下标
     * */
    private int toIndex;

    /**
     * 路径权重
     * */
    private int weight;

    public Edge(int fromIndex, int toIndex, int weight) {
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.weight = weight;
    }

    public int getFromIndex() {
        return fromIndex;
    }

    public void setFromIndex(int fromIndex) {
        this.fromIndex = fromIndex;
    }

    public int getToIndex() {
        return toIndex;
    }

    public void setToIndex(int toIndex) {
        this.toIndex = toIndex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

