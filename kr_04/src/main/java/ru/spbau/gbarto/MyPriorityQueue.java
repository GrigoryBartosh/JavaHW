package ru.spbau.gbarto;

import java.util.Iterator;

/**
 * Interface of my realization of PriorityQueue.
 *
 * @param <E> given type of elements
 */
public interface MyPriorityQueue<E> extends Iterable<E> {

    /**
     * Adds given element to Queue.
     *
     * @param e given element
     */
    void add(E e);

    /**
     * Returns the element with the highest priority and removes it from the Queue.
     *
     * @return the element with the highest priority and removes it from the Queue
     */
    E poll();

    /**
     * Returns size of the Queue.
     *
     * @return size of the Queue
     */
    int size();

    /**
     * Clears the Queue;
     */
    void clear();

    /**
     * Returns an iterator, bypassing elements in order of priority.
     *
     * @return an iterator, bypassing elements in order of priority
     */
    Iterator<E> iterator();
}
