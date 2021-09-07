package Maths;

public class IsPrime {
    static int isPrime(int N){
        if(N == 1 || N == 0 ){
            return 0;
        }
        for (int i = 2; i <= Math.sqrt(N); i++) {
            if(N % i == 0 ){
                return 0;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        System.out.println(isPrime(25));
    }
}
