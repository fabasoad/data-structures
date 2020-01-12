package io.github.fabasoad.datastructures;

public interface Queue<T> {
    Queue<T> enQueue(T t);
    // Removes the element at the beginning of the immutable queue, and returns the new queue.
    Queue<T> deQueue();
    T head();
    boolean isEmpty();
    int size();
}