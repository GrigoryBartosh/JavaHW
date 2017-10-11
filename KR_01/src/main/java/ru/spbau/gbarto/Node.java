package ru.spbau.gbarto;

public class Node {
    private Node prev = null;
    private Node next = null;
    private Pair data;

    public Node(Pair data) {
        this.data = data;
    }

    public Node getPrev() {
        return prev;
    }
    public Node getNext() {
        return next;
    }

    public void setPrev(Node n) {
        prev = n;
    }
    public void setNext(Node n) {
        next = n;
    }

    public Pair getData() {
        return data;
    }
}