package algorithms;

import java.util.*;

public final class Approximation {
    private Approximation() {}
    public static Set<Integer> vertexCover(int vertices, int[][] edges) {
        boolean[] covered=new boolean[edges.length];
        Set<Integer> chosen=new LinkedHashSet<>();
        for(int i=0;i<edges.length;i++) {
            int u=edges[i][0],v=edges[i][1];
            if(u<0||u>=vertices||v<0||v>=vertices) throw new IllegalArgumentException("Invalid vertex.");
            if(!chosen.contains(u)&&!chosen.contains(v)){chosen.add(u);chosen.add(v);}
        }
        return chosen;
    }
    public static String guarantee() { return "The greedy matching-based cover is at most twice the size of an optimal minimum vertex cover."; }
}
