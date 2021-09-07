package BitMagic;

public class FindFirstSetBit {
    public static int getFirstSetBit(int n){
        if(n == 0){
            return 0; 
        }
        int positon = 1;
        int m = 1;

        while((n & m) == 0){
            positon++;
            m = m<<1;
        }
        return positon;
            
    }

    public static void main(String[] args) {
        System.out.println(getFirstSetBit(18));
    }
}
