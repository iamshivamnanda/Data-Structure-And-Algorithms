package Stack;

public class Stack {
   
        int myStack[];
        int capacity;
        int top = -1;
        
        Stack(int n){
            this.myStack = new int[n];
            this.capacity = n;
        }

        void Push(int value){
            if(this.top == this.capacity){
                System.out.println("Stack Is Full");
            }
            this.top = this.top + 1;
            myStack[this.top] = value;
        }

        int Pop(){
            if(this.top == -1){
                return -1;
            }
            int value = myStack[this.top];
            this.top = this.top -1;
            return value;
            
        }
        int Peek(){
            if(this.isEmpty()){
                return -1;
            }
            return myStack[top];
        }

        boolean isEmpty(){
            return this.top == -1;
        }
    

    public static void main(String[] args) {
        Stack newStack = new Stack(5);
        newStack.Push(10);
        newStack.Push(30);
        newStack.Push(50);
        newStack.Push(80);
        System.out.println(newStack.Pop());
        System.out.println(newStack.Peek());
        
}
}
