package algorithms;

import java.util.concurrent.*;

public final class ParallelAlgorithms {
    private ParallelAlgorithms(){}
    public static long parallelSum(int[] values) {
        return java.util.Arrays.stream(values).parallel().asLongStream().sum();
    }
    public static long[] prefixSum(int[] values) {
        long[] out=new long[values.length];
        if(values.length==0)return out;
        int processors=Math.max(1,Runtime.getRuntime().availableProcessors());
        int chunks=Math.min(processors,values.length);
        long[] sums=new long[chunks];int chunkSize=(values.length+chunks-1)/chunks;
        ExecutorService pool=Executors.newFixedThreadPool(chunks);
        try {
            Future<?>[] futures=new Future<?>[chunks];
            for(int c=0;c<chunks;c++){
                final int id=c,start=c*chunkSize,end=Math.min(values.length,start+chunkSize);
                futures[c]=pool.submit(()->{long s=0;for(int i=start;i<end;i++){s+=values[i];out[i]=s;}sums[id]=s;});
            }
            for(Future<?> f:futures)f.get();
            long offset=0;
            for(int c=0;c<chunks;c++){int start=c*chunkSize,end=Math.min(values.length,start+chunkSize);for(int i=start;i<end;i++)out[i]+=offset;offset+=sums[c];}
            return out;
        } catch(Exception e){throw new IllegalStateException("Parallel processing failed.",e);}
        finally{pool.shutdown();}
    }
}
