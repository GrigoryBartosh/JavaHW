package ru.spbau.gbarto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueTest {
    MyQueue<Integer> queue;
    MyQueue<Integer> queueCmp;
    MyQueue<Node> queueNode;

    private class Node implements Comparable<Node>{
        private Integer a;
        private Integer b;

        private Node(Integer a, Integer b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Node other) {
            return a - other.a;
        }
    }

    @BeforeEach
    void create() {
        queue = new MyQueue<Integer>();
        queueCmp = new MyQueue<Integer>((a, b) -> b - a);
        queueNode = new MyQueue<Node>();
    }

    @Test
    void add() {
        queue.add(5);
        queue.add(4);
        queue.add(3);
        queue.add(2);
        queue.add(1);

        assertEquals((Integer) 1, queue.poll());
        assertEquals((Integer) 2, queue.poll());
        assertEquals((Integer) 3, queue.poll());
        assertEquals((Integer) 4, queue.poll());
        assertEquals((Integer) 5, queue.poll());
    }

    @Test
    void addCmp() {
        queueCmp.add(1);
        queueCmp.add(2);
        queueCmp.add(3);
        queueCmp.add(4);
        queueCmp.add(5);

        assertEquals((Integer) 5, queueCmp.poll());
        assertEquals((Integer) 4, queueCmp.poll());
        assertEquals((Integer) 3, queueCmp.poll());
        assertEquals((Integer) 2, queueCmp.poll());
        assertEquals((Integer) 1, queueCmp.poll());
    }

    @Test
    void size() {
        queue.add(5);
        queue.add(4);
        queue.add(3);
        queue.add(2);
        queue.add(1);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        assertEquals(10, queue.size());
    }

    @Test
    void clear() {
        queue.add(5);
        queue.add(4);
        queue.add(3);
        queue.add(2);
        queue.add(1);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        queue.clear();

        assertEquals(0, queue.size());
    }

    @Test
    void iterator() {
        queue.add(5);
        queue.add(4);
        queue.add(3);
        queue.add(2);
        queue.add(1);

        int i = 1;
        for (Integer x : queue) {
            assertEquals((Integer) i, x);
            i++;
        }
    }

    @Test
    void testOrder() {
        queueNode.add(new Node(5, 3));
        queueNode.add(new Node(4, 0));
        queueNode.add(new Node(5, 1));
        queueNode.add(new Node(6, 0));
        queueNode.add(new Node(5, 2));

        assertEquals((Integer) 0, queueNode.poll().b);
        assertEquals((Integer) 3, queueNode.poll().b);
        assertEquals((Integer) 1, queueNode.poll().b);
        assertEquals((Integer) 2, queueNode.poll().b);
        assertEquals((Integer) 0, queueNode.poll().b);
    }
}