public class Prime {
    public static boolean isprime(int n){
        if(n<=1){
            return false;
        }
        if(n<=3){
            return true;
        }
        if(n%2 == 0){
            return false;
        }
        double counterdiviion = Math.sqrt(n);
        for (double i = 2; i <= counterdiviion ; i++) {
            if(n%i==0){
                return false;
            }
        }
        return true;
    } 
    public static void checkPrime(int ...args){
        for(int i:args){
            if(isprime(i)){
                System.out.printf("%d ",i);
            }
        }
        System.out.print("\n");
    }
    public static void main(String[] args){
        checkPrime(5);
    }
}


