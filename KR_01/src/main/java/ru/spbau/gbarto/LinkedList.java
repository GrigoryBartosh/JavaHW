package ru.spbau.gbarto;

public class LinkedList {
    public Node head;
    public Node tail;

    public Node getTail() {
        return tail;
    }
    public Node getHead() {
        return head;
    }

    public void add(Node n) {
        if (head == null) {
            head = n;
            tail = head;
        }
        else {
            tail.setNext(n);
            n.setPrev(tail);
            tail = n;
        }
    }

    public void remove(Node n) {
        Node l = n.getPrev();
        Node r = n.getNext();
        if (l != null) l.setNext(r);
        if (r != null) r.setPrev(l);
    }

    public void clear() {
        head = null;
        tail = null;
    }
}
