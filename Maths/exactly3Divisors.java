package Maths;


public class exactly3Divisors {

    static int exactly3Divisor(int N) {
        int count = 0, flag = 0;
        for (int i = 2; i * i <= N; i++) {
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                count++;
            }
            flag = 0;
        }
        return count;

    }

    public static void main(String[] args) {
        System.out.println(exactly3Divisor(25));
    }
}
