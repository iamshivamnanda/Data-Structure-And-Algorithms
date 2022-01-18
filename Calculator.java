import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** The Calculator class provides methods to get addition and subtraction of given 2 numbers.*/  
public class Calculator {  
    /** The add() method returns addition of given numbers.*/  
    public static int add(int a, int b){return a+b;}  
    /** The sub() method returns subtraction of given numbers.*/  
    public static int sub(int a, int b){return a-b;}  

    public static void main(String[] args) {
        BufferedReader  bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println(bf.readLine());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    }  