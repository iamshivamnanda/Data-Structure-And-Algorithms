package Queue;

public class Queue {
    int[]  myQueue;
    int capacity;
    int front = -1,rear = -1;
    Queue(int n){
        this.myQueue = new int[n];
        this.capacity = n;
    }

    void enqueue(int value){
        if(this.rear == this.capacity -1){
            System.out.println("Queue is full");
            return;
        }

        if(this.isEmpty()){
            this.front = 0;
            this.myQueue[this.front] = value;
            this.rear++;
            return;
        }else{
            this.rear++;
            myQueue[this.rear]= value;
            return;
        }
    }

    int dequeue(){
        if(isEmpty()){
            return -1;
        }else{
            int value = this.myQueue[this.front];
            if (this.front >= this.rear) {
                this.front = -1;
                this.rear = -1;
              } else{
                  this.front++;
              }
            return value;
        }
    }
    void printQueue(){
        if(isEmpty()){
            System.out.println("Queue is Empty");
            return;
        }
        for (int i = this.front;i<=this.rear;i++) {
            System.out.print(this.myQueue[i] + " ");
        }
        System.out.println("");
    }

    boolean isEmpty(){
        return this.front == -1;
    }


    public static void main(String[] args) {
        Queue myQueue = new Queue(5);
        myQueue.enqueue(1);
        myQueue.enqueue(2);
        myQueue.enqueue(3);
        System.out.println(myQueue.isEmpty());
        // System.out.println(myQueue.dequeue());
        myQueue.enqueue(4);
        myQueue.enqueue(5);
        myQueue.printQueue();
        myQueue.enqueue(5);
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue.dequeue());
        myQueue.printQueue();

        myQueue.enqueue(5);
        myQueue.enqueue(5);
        myQueue.enqueue(5);

        myQueue.printQueue();





    }

}
