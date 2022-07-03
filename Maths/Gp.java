package Maths;

public class Gp {
    static double termOfGP(int A,int B,int N)
    {
        double a = A;
        double r = B/a;
        return a*Math.pow(r, N-1);
    }

    public static void main(String[] args) {
        System.out.println(termOfGP(84, 87, 3));
    }
    static int maximizeMoney(int N , int K) {
        int val = (N%2 ==0)?N/2: (N+1)/2;
        return val*K;
}
}
