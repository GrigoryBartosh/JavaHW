package ru.spbau.gbarto;

import java.util.Comparator;
import java.util.Iterator;

/**
 * My realization of PriorityQueue.
 *
 * @param <E> given type of elements
 */
public class MyQueue<E> implements MyPriorityQueue<E>{
    private MyTreeSet<Node<E>> data;

    /**
     * Class Node storing the given elements and its parameters.
     *
     * @param <E> given type of elements
     */
    private static class Node<E>{
        private static int number = 0;

        private E element;
        private int time;

        private Node(E element) {
            this.element = element;
            this.time = number++;
        }
    }

    /**
     * Iterator for this class
     */
    private class MyIterator implements Iterator<E> {
        private Iterator<Node<E>> iterator;


        private MyIterator() {
            iterator = data.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public E next() {
            return iterator.next().element;
        }
    }

    /**
     * Constructor without parameters.
     * The natural order is used.
     */
    public MyQueue() {
        data = new MyTreeSet<>((a, b) -> {
            int cmp = ((Comparable<? super E>)a.element).compareTo(b.element);
            if (cmp != 0) {
                return cmp;
            }

            return a.time - b.time;
        });
    }

    /**
     * Constructor without parameters.
     * The order of comparator is used.
     *
     * @param comparator of elements
     */
    public MyQueue(Comparator<? super E> comparator) {
        data = new MyTreeSet<>((a, b) -> {
            int cmp = comparator.compare(a.element, b.element);
            if (cmp != 0) {
                return cmp;
            }

            return a.time - b.time;
        });
    }

    @Override
    public void add(E e) {
        data.add(new Node<E>(e));
    }

    @Override
    public E poll() {
        Node<E> e = data.first();
        data.remove(e);
        return e.element;
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }
}
