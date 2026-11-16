package algovisualizer.datastructures;

public class MyQueue<T> {
    private static class Node<T>{
        T data;
        Node<T> next;
        Node(T data){
            this.data = data;
        }
    }

    private Node<T> head;
    private Node<T> tail;

    public boolean isEmpty(){
        return head == null;
    }

    public void enqueue(T value){
        Node<T> n = new Node<>(value);

        if(tail != null){
            tail.next = n;
        }

        tail = n;

        if(head == null){
            head = n;
        }
    }

    public T dequeue(){
        if (isEmpty()) return null;
        T val = head.data;
        head = head.next;
        if(head == null) tail = null;
        return val;
    }

    @Override
    public String toString() {
        return "MyQueue{" +
                "head=" + head.data +
                ", tail=" + tail.data +
                '}';
    }
}
