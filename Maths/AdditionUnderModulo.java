package Maths;


public class AdditionUnderModulo {
    public static long sumUnderModulo(long a, long b){
    //     BigInteger sum = new BigInteger(String.valueOf(a));
    //    sum =  sum.add(new BigInteger(String.valueOf(b)));
    long l = (long)(Math.pow(10, 9)+7);
    //     BigInteger BL = new BigInteger(String.valueOf(l));
    //     long rsum = sum.mod(BL).longValue();
    long A = a%l;
    long B = b%l;
    System.out.println(A);
    System.out.println(B);
    long rsum = (A +B)%l;
        return rsum;
        // code here
    } 

    static long multiplicationUnderModulo(long a, long b)
    {
        long l = (long)(Math.pow(10, 9)+7);
        long A = a%l;
    long B = b%l;
    System.out.println(A);
    System.out.println(B);
    long rsum = (A*B)%l;
        return rsum;
    }

    static int calmodInv(int a, int b) 
    { 
      a = a % b; 
      for (int x = 1; x < b; x++) 
      if ((a * x) % b == 1) 
        return x; 
      return -1; 
    } 


    public static void main(String[] args) {
        System.out.println(multiplicationUnderModulo(18L, 611L));
        System.out.println(multiplicationUnderModulo(1000000007L, 1000000007L));
    }
}
