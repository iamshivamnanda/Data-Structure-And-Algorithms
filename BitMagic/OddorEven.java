package BitMagic;

public class OddorEven {
    static String oddEven(int N){
        if((N^1)==N+1){
            return "even";
        }else{
            return "odd"; 
        }
    }

    public static void main(String[] args) {
        System.out.println(oddEven(2));
    }
}
