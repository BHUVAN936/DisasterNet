package algorithms;

import java.util.*;

public class FlowNetwork {
    private final int n;
    private final int[][] capacity;
    public FlowNetwork(int n) {
        if (n < 2) throw new IllegalArgumentException("At least two nodes are required.");
        this.n=n; this.capacity=new int[n][n];
    }
    public void addEdge(int u,int v,int c) {
        if(u<0||u>=n||v<0||v>=n||c<0) throw new IllegalArgumentException("Invalid edge.");
        capacity[u][v]+=c;
    }
    public int maxFlow(int source,int sink) {
        if(source<0||source>=n||sink<0||sink>=n||source==sink) throw new IllegalArgumentException("Invalid source/sink.");
        int[][] residual=new int[n][n];
        for(int i=0;i<n;i++) residual[i]=capacity[i].clone();
        int total=0; int[] parent=new int[n];
        while(bfs(residual,source,sink,parent)) {
            int add=Integer.MAX_VALUE;
            for(int v=sink;v!=source;v=parent[v]) add=Math.min(add,residual[parent[v]][v]);
            for(int v=sink;v!=source;v=parent[v]) { int u=parent[v]; residual[u][v]-=add; residual[v][u]+=add; }
            total+=add;
        }
        return total;
    }
    private boolean bfs(int[][] r,int s,int t,int[] p) {
        Arrays.fill(p,-1); p[s]=s; Queue<Integer> q=new ArrayDeque<>(); q.add(s);
        while(!q.isEmpty()) { int u=q.remove(); for(int v=0;v<n;v++) if(p[v]==-1&&r[u][v]>0){p[v]=u;if(v==t)return true;q.add(v);} }
        return false;
    }
    public boolean[][] residualReachability(int source) {
        boolean[][] seen=new boolean[n][n]; // row 0 is the reachable-node vector for convenient output
        boolean[] visited=new boolean[n]; Queue<Integer> q=new ArrayDeque<>(); q.add(source);visited[source]=true;
        while(!q.isEmpty()){int u=q.remove();for(int v=0;v<n;v++)if(!visited[v]&&capacity[u][v]>0){visited[v]=true;q.add(v);}}
        seen[0]=visited; return seen;
    }
    public static int demoMaxFlow() {
        FlowNetwork g=new FlowNetwork(6);
        g.addEdge(0,1,16);g.addEdge(0,2,13);g.addEdge(1,2,10);g.addEdge(2,1,4);
        g.addEdge(1,3,12);g.addEdge(3,2,9);g.addEdge(2,4,14);g.addEdge(4,3,7);
        g.addEdge(3,5,20);g.addEdge(4,5,4);
        return g.maxFlow(0,5);
    }
}
