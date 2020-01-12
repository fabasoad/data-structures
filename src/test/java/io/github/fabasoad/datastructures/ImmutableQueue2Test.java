package io.github.fabasoad.datastructures;

import java.util.Collection;

public class ImmutableQueue2Test extends ImmutableQueueBaseTest {

    @Override
    <T> Queue<T> createQueue() {
        return new ImmutableQueue2<>();
    }

    @Override
    <T> Queue<T> createQueue(Collection<T> elements) {
        return new ImmutableQueue2<>(elements);
    }
}
