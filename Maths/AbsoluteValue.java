package Maths;

class Absolute{
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
}

public class AbsoluteValue {
    

    public static void main(String[] args) {
        System.out.println(Absolute.absolute(-9));
    }
}
