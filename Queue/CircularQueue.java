package Queue;

public class CircularQueue {
    int[]  myQueue;
    int capacity;
    int front = -1,rear = -1;
    CircularQueue(int n){
        this.myQueue = new int[n];
        this.capacity = n;
    }

    void enqueue(int value){
        if(this.isFull()){
            System.out.println("Queue is full");
            return;
        }

        if(this.isEmpty()){
            this.front = 0;
            this.myQueue[this.front] = value;
            this.rear = (this.rear +1)%this.capacity;
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
                this.front = (this.front +1)%this.capacity;
            }
            return value;
        }
    }
    void printQueue(){
        if(isEmpty()){
            System.out.println("Queue is Empty");
            return;
        }
        int i;
        System.out.println("Items -> ");
        for ( i = front; i != rear; i = (i + 1) % this.capacity)
          System.out.print(this.myQueue[i] + " ");
        System.out.println(this.myQueue[i]);
       
    }

    boolean isEmpty(){
        return this.front == -1;
    }

    boolean isFull(){
        if(this.front == 0 && rear ==  this.capacity -1){
            return true;
        }
        if(this.front == rear + 1){
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        CircularQueue myQueue = new CircularQueue(5);
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
