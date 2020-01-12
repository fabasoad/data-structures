package io.github.fabasoad.datastructures;

import java.util.Collection;

public class ImmutableQueue1Test extends ImmutableQueueBaseTest {

    @Override
    <T> Queue<T> createQueue() {
        return new ImmutableQueue1<>();
    }

    @Override
    <T> Queue<T> createQueue(Collection<T> elements) {
        return new ImmutableQueue1<>(elements);
    }
}
