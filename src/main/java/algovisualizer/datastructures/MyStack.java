package algovisualizer.datastructures;

import java.util.ArrayList;
import java.util.List;

public class MyStack<T> {
    private static class Node<T>{
        T data;
        Node<T> next;
        Node(T data, Node<T> next){
            this.data = data;
            this.next = next;
        }
    }

    private Node<T> top;

    public boolean isEmpty(){
        return top == null;
    }

    public void push(T value){
        top = new Node<>(value, top);
    }

    public T pop(){
        if(isEmpty()) return null;
        T val = top.data;
        top = top.next;
        return val;
    }

    @Override
    public String toString(){
        return getElements().toString();
    }

    public List<T> getElements(){
        List<T> elements = new ArrayList<>();

        Node<T> current = top;

        while(current != null){
            elements.add(current.data);
            current = current.next;
        }

        return elements;
    }
}
