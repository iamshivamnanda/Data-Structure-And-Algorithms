package Stack;

public class CharStack {
    static class Stack {
   
        char myStack[];
        int capacity;
        int top = -1;
        
        Stack(int n){
            this.myStack = new char[n];
            this.capacity = n;
        }

        void Push(char value){
            if(this.top == this.capacity){
                System.out.println("Stack Is Full");
            }
            this.top = this.top + 1;
            myStack[this.top] = value;
        }

        char Pop(){
            char invalid = 'i';
            if(this.top == -1){
                return invalid ;
            }
            char value = myStack[this.top];
            this.top = this.top -1;
            return value;
            
        }
        char Peek(){
            if(this.isEmpty()){
                return 'i';
            }
            return myStack[top];
        }

        boolean isEmpty(){
            return this.top == -1;
        }
    
        void printStack(){
            if(isEmpty()){
                System.out.println("Empty Stack");
                return ;
            }
            for (int i = 0; i <= top; i++) {
                System.out.print(this.myStack[i]);
            }
            System.out.println("");
        }
  
}  
public static void main(String[] args) {
    String mString = "Shivam";
    char mString2[] = mString.toCharArray();
    Stack newStack = new Stack(mString.length());
    for (char string : mString2) {
        newStack.Push(string);
    }


    newStack.printStack();
   
    for (char string : mString2) {
        System.out.print(
            newStack.Pop()
        );
    }
    
    
} 
}
