 package BitMagic;

public class SetKthBit {
    static int setKthBit(int N,int K){
        int one = 1<<K;
        return N | one;
    }
    public static void main(String[] args) {
        System.out.println(setKthBit(15, 3));
    }
}
