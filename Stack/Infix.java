package Stack;
import java.lang.reflect.Array;
import java.util.*;

public class Infix {
    public static boolean isOperator(char ch){
        char cha[] = {'(',')','+','-','*','/','^'};
        for (int i = 0; i < cha.length; i++) {
            if(cha[i] == ch){
                return true;
            }
        }
        return false;}
    public static int presedene(char ch){
        if(ch =='+' || ch =='-'){
            return 1;
        }else if(ch =='*' || ch =='/'){
            return 2;
        }
        else if(ch =='^' ){
            return 3;
        }
        return -1;
    }
    public static String infixToPostfix(String exp) 
	{
		StringBuilder sBuilder = new StringBuilder();
        ArrayDeque<Character> stack = new ArrayDeque<Character>();
        int i = 0;
        while(i<exp.length()){
            if(!isOperator(exp.charAt(i))){
                sBuilder.append(exp.charAt(i));
                i++;
            }else{
                if(exp.charAt(i) == '('){
                    stack.push(exp.charAt(i));
                    i++;
                }else if (exp.charAt(i) == ')'){
                    while (stack.isEmpty() == false && stack.peek() !='(') {
                        char ch = stack.pop();
                        sBuilder.append(ch);
                    }
                    if(stack.peek() == '('){
                        stack.pop();
                    }
                    i++;
                }else{
                    if(stack.isEmpty()){stack.push(exp.charAt(i));i++;}
                    else if(presedene(exp.charAt(i)) <= presedene(stack.peek())){
                        while (stack.isEmpty() == false && presedene(exp.charAt(i)) <= presedene(stack.peek())) {
                            sBuilder.append(stack.pop());
                        }
                        stack.push(exp.charAt(i));
                        i++;
                    }else{
                        stack.push(exp.charAt(i));
                        i++;
                    }
                }
            }
        }
        while (stack.isEmpty() == false) {
            sBuilder.append(stack.pop());
        }
        return sBuilder.toString();
	} 
    public static int evaluatePostFix(String S)
    {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();
        for (int i = 0; i < S.length(); i++) {
            if(isOperator(S.charAt(i))){
                
                int n1 = arrayDeque.pop();
                int n2 = arrayDeque.pop();
                if(S.charAt(i) == '*')
                {
                    arrayDeque.push(n1*n2);
                }
                else if(S.charAt(i)== '/')
                {
                    arrayDeque.push(n2/n1);
                }
                else if(S.charAt(i) == '+')
                {
                    arrayDeque.push(n1+n2);
                }
                else if(S.charAt(i) == '-')
                {
                    arrayDeque.push(n2-n1);
                }
            }else{
                arrayDeque.push(S.charAt(i)-'0');
            }
        }
        return arrayDeque.peek();
    }
    public static void main(String[] args) {
        System.out.println(infixToPostfix("a+b*(c^d-e)^(f+g*h)-i"));
    }
}
