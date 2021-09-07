package Queue;

public class Dequeue {
    int[] myDequeue;
    int front;
    int rear;
    int size;

    Dequeue(int n){
        this.myDequeue = new int[n];
        this.size = n;
        this.front = -1;
        this.rear = 0;
    }

    boolean isFull(){
        return ((front == 0 && rear == size -1) || (front == rear +1));
    }

    boolean isEmpty(){
        return (front == -1);
    }
    void insertAtFront(int value){
        if(isFull()){
            System.out.println("Overflow");
            return;
        }
        if(front == -1){
            front = 0;
            rear = 0;
        }
        else if(front == 0){
            front = size -1;
        }else{
            front = front - 1;
        }
        myDequeue[front] = value;
    }

    void insertAtRear(int value){
        if(isFull()){
            System.out.println("OverFlow");
            return;
        }
        if(front == -1){
            front = 0;
            rear = 0;
        }else if(rear == size - 1){
            rear = 0;
        }
        else{
            rear = rear +1;
        }
        myDequeue[rear] = value;
    }

    void deleteAtFront(){
        if(isEmpty()){
            System.out.println("UnderFlow");
            return;
        }
        if(front == rear){
            front = -1;
            rear = -1;
        }else if(front == size -1){
            front = 0;
        }else{
            front++;
        }
    }

    void deleteAtRear(){
        if(isEmpty()){
            System.out.println("UnderFlow");
            return;
        }
        if(front == rear){
            front = -1;
            rear = -1;
        }else if(rear == 0){
            rear = size -1;
        }
        else{
            rear--;
        }
   }


    int getFront(){
        if(isEmpty()){
            System.out.println("UnderFlow");
            return -1;
        }
        return myDequeue[front];
    }
    int getRear(){
        if(isEmpty()){
            System.out.println("UnderFlow");
            return -1;
        }
        return myDequeue[rear];
    }

    public static void main(String[] args) {
        Dequeue mDequeue = new Dequeue(5);
        mDequeue.insertAtFront(10);
        mDequeue.insertAtFront(20);
        mDequeue.insertAtFront(40);
        mDequeue.insertAtFront(50);

        // mDequeue.insertAtRear(15);        


        System.out.println(mDequeue.getFront());
        System.out.println(mDequeue.getRear());

        mDequeue.deleteAtFront();
        mDequeue.deleteAtRear();

        
        System.out.println(mDequeue.getFront());
        System.out.println(mDequeue.getRear());
    }
}
