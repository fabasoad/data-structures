package io.github.fabasoad.datastructures;

import java.util.ArrayDeque;
import java.util.Collection;

import static java.util.Collections.emptyList;

public class ImmutableQueue1<T> implements Queue<T> {

    private final ArrayDeque<T> queue;

    public ImmutableQueue1() {
        this(emptyList());
    }

    public ImmutableQueue1(final Collection<T> elements) {
        queue = new ArrayDeque<>(elements);
    }

    @Override
    public Queue<T> enQueue(T t) {
        final ArrayDeque<T> cloned = queue.clone();
        cloned.add(t);
        return new ImmutableQueue1<T>(cloned);
    }

    @Override
    public Queue<T> deQueue() {
        final ArrayDeque<T> cloned = queue.clone();
        cloned.poll();
        return new ImmutableQueue1<T>(cloned);
    }

    @Override
    public T head() {
        return queue.peek();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public int size() {
        return queue.size();
    }
}
