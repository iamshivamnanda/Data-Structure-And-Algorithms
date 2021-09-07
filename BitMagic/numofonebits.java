package BitMagic;

public class numofonebits {
    static int setBits(int N) {
        if(N ==0){
            return 0;
        }
       int numofonebits = 0;
        while(N>0){
           if( (N & 1) !=0){
               numofonebits++;
           }
          N>>=1;
       }
       return numofonebits;
    }

    public static void main(String[] args) {
        System.out.println(setBits(6));
    }
}
