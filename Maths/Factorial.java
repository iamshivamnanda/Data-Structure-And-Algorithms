package Maths;
import java.lang.Math;

public class Factorial {
    static long factorial(int N) {
        // Your code here
        long fact = 1;
        for (int i = 2; i <=N; i++) {
            fact *= i;
        }
        return fact;
    }

    static int numberofDigitsinFact(int N){
        if(N<0){
            return 0;
        }
        if(N<=1){
            return 1;
        }
        double digits= 0;
        for (int i = 2; i <= N; i++) {
            digits += Math.log10(i);
        }
        return (int)(Math.floor(digits) + 1);
    }

    static int numberofDigits(long digit){
        int count = 0;
        while (Math.floor(digit) !=0){
            count++;
            digit /= 10;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(factorial(3));
        System.out.println(numberofDigits(3));
    }
}
