package Maths;

public class AbsoluteValue {
    static int absolute(int I) {
       
        if(I == 0){
            return I;
        }
        if(I>0){
            return I;
        }
        else{
            return ( I * -1);
        }
    }

    public static void main(String[] args) {
        System.out.println(absolute(-9));
    }
}
