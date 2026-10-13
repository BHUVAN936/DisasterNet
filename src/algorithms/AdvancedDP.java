package algorithms;

import java.util.Arrays;

public final class AdvancedDP {
    private AdvancedDP(){}
    public static int globalAlignment(String a,String b,int match,int mismatch,int gap){
        int[][] dp=new int[a.length()+1][b.length()+1];
        for(int i=1;i<=a.length();i++)dp[i][0]=dp[i-1][0]+gap;
        for(int j=1;j<=b.length();j++)dp[0][j]=dp[0][j-1]+gap;
        for(int i=1;i<=a.length();i++)for(int j=1;j<=b.length();j++)
            dp[i][j]=Math.max(dp[i-1][j-1]+(a.charAt(i-1)==b.charAt(j-1)?match:mismatch),
                Math.max(dp[i-1][j]+gap,dp[i][j-1]+gap));
        return dp[a.length()][b.length()];
    }
    public static long subsetSumCount(int[] a,int target){
        if(target<0)throw new IllegalArgumentException("Target cannot be negative.");
        long[] dp=new long[target+1];dp[0]=1;
        for(int x:a){if(x<0)throw new IllegalArgumentException("Values must be non-negative.");for(int s=target;s>=x;s--)dp[s]+=dp[s-x];}
        return dp[target];
    }
    public static long tsp(int[][] d){
        int n=d.length;if(n==0)return 0;if(n>20)throw new IllegalArgumentException("This demonstration supports at most 20 locations.");
        for(int[] row:d)if(row.length!=n)throw new IllegalArgumentException("Distance matrix must be square.");
        int states=1<<n;long INF=Long.MAX_VALUE/4;long[][] dp=new long[states][n];
        for(long[] row:dp)Arrays.fill(row,INF);dp[1][0]=0;
        for(int mask=1;mask<states;mask++){if((mask&1)==0)continue;for(int u=0;u<n;u++)if(dp[mask][u]<INF)
            for(int v=0;v<n;v++)if((mask&(1<<v))==0){int next=mask|(1<<v);dp[next][v]=Math.min(dp[next][v],dp[mask][u]+d[u][v]);}}
        long best=INF;for(int u=1;u<n;u++)best=Math.min(best,dp[states-1][u]+d[u][0]);return n==1?0:best;
    }
    public static int treeDiameter(int n,int[][] edges){
        if(n<=0)return 0;int[] head=new int[n];Arrays.fill(head,-1);int m=edges.length;
        int[] to=new int[2*m],next=new int[2*m];int e=0;
        for(int[] edge:edges){int u=edge[0],v=edge[1];to[e]=v;next[e]=head[u];head[u]=e++;to[e]=u;next[e]=head[v];head[v]=e++;}
        int[] first=bfsFarthest(0,n,head,to,next);int[] second=bfsFarthest(first[0],n,head,to,next);return second[1];
    }
    private static int[] bfsFarthest(int s,int n,int[] head,int[] to,int[] next){
        int[] dist=new int[n];Arrays.fill(dist,-1);int[] q=new int[n];int l=0,r=0;q[r++]=s;dist[s]=0;int far=s;
        while(l<r){int u=q[l++];if(dist[u]>dist[far])far=u;for(int e=head[u];e!=-1;e=next[e])if(dist[to[e]]==-1){dist[to[e]]=dist[u]+1;q[r++]=to[e];}}
        return new int[]{far,dist[far]};
    }
}
