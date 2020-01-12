package io.github.fabasoad.datastructures;

import java.util.Collection;

import static java.lang.System.arraycopy;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class ImmutableQueue2<T> implements Queue<T> {

    private final T[] queue;

    public ImmutableQueue2() {
        this(emptyList());
    }

    @SuppressWarnings("unchecked")
    public ImmutableQueue2(final Collection<T> elements) {
        queue = (T[]) elements.toArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Queue<T> enQueue(T t) {
        final T[] cloned = (T[]) new Object[queue.length + 1];
        arraycopy(queue, 0, cloned, 0, queue.length);
        cloned[cloned.length - 1] = t;
        return new ImmutableQueue2<>(asList(cloned));
    }

    @Override
    @SuppressWarnings("unchecked")
    public Queue<T> deQueue() {
        if (isEmpty()) {
            return new ImmutableQueue2<>();
        }
        final T[] cloned = (T[]) new Object[queue.length - 1];
        arraycopy(queue, 1, cloned, 0, queue.length - 1);
        return new ImmutableQueue2<>(asList(cloned));
    }

    @Override
    public T head() {
        return isEmpty() ? null : queue[0];
    }

    @Override
    public boolean isEmpty() {
        return queue.length == 0;
    }

    @Override
    public int size() {
        return queue.length;
    }
}
