package algorithms;

import java.math.BigInteger;
import java.util.Random;

public final class RandomizedAlgorithms {
    private static final Random RANDOM=new Random();
    private RandomizedAlgorithms(){}
    public static void quickSort(int[] a){quickSort(a,0,a.length-1);}
    private static void quickSort(int[] a,int lo,int hi){
        if(lo>=hi)return;
        int pivotIndex=lo+RANDOM.nextInt(hi-lo+1), pivot=a[pivotIndex];
        swap(a,pivotIndex,hi); int store=lo;
        for(int i=lo;i<hi;i++)if(a[i]<pivot)swap(a,store++,i);
        swap(a,store,hi);quickSort(a,lo,store-1);quickSort(a,store+1,hi);
    }
    private static void swap(int[]a,int i,int j){int t=a[i];a[i]=a[j];a[j]=t;}
    public static boolean probablyPrime(long n,int rounds){
        if(n<2)return false;if(n==2||n==3)return true;if(n%2==0)return false;
        BigInteger N=BigInteger.valueOf(n), d=BigInteger.valueOf(n-1);int s=0;
        while(!d.testBit(0)){d=d.shiftRight(1);s++;}
        BigInteger nMinusOne=N.subtract(BigInteger.ONE);
        for(int i=0;i<Math.max(1,rounds);i++){
            BigInteger a=BigInteger.valueOf(2+Math.abs(RANDOM.nextLong())%(n-3));
            BigInteger x=a.modPow(d,N);
            if(x.equals(BigInteger.ONE)||x.equals(nMinusOne))continue;
            boolean witness=true;
            for(int r=1;r<s;r++){x=x.multiply(x).mod(N);if(x.equals(nMinusOne)){witness=false;break;}}
            if(witness)return false;
        }
        return true;
    }
    public static int[] reservoirSample(int[] stream,int k){
        if(k<0||k>stream.length)throw new IllegalArgumentException("Sample size must be between zero and stream length.");
        int[] sample=new int[k];for(int i=0;i<k;i++)sample[i]=stream[i];
        for(int i=k;i<stream.length;i++){int j=RANDOM.nextInt(i+1);if(j<k)sample[j]=stream[i];}
        return sample;
    }
}
