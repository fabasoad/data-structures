package io.github.fabasoad.datastructures;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

abstract class ImmutableQueueBaseTest {

    abstract <T> Queue<T> createQueue();

    abstract <T> Queue<T> createQueue(final Collection<T> elements);

    @Test
    void testEnQueue() {
        final Collection<String> expected1 = asList("test1", "test3", "test2");
        final Collection<String> expected2 = new ArrayList<>(expected1);
        final String newElement = "test4";
        expected2.add(newElement);

        final Queue<String> queue1 = createQueue(expected1);
        final Queue<String> queue2 = queue1.enQueue(newElement);

        assertEquals(3, queue1.size());
        assertEquals(4, queue2.size());

        assertQueueHas(expected1, queue1);
        assertQueueHas(expected2, queue2);
    }

    private static <T> void assertQueueHas(final Collection<T> expected, Queue<T> queue) {
        while (queue.head() != null) {
            final T head = queue.head();
            assertTrue(expected.contains(head));
            queue = queue.deQueue();
        }
    }

    private static <T> void assertQueueDoesNotHave(final T element, Queue<T> queue) {
        while (queue.head() != null) {
            final T head = queue.head();
            assertNotEquals(element, head);
            queue = queue.deQueue();
        }
    }

    @Test
    void testDeQueuePositive() {
        final Collection<Integer> expected1 = asList(1, 3, 2);
        final Queue<Integer> queue1 = createQueue(expected1);
        final Queue<Integer> queue2 = queue1.deQueue();
        assertEquals(3, queue1.size());
        assertEquals(2, queue2.size());
        assertQueueHas(expected1, queue1);
        assertQueueDoesNotHave(1, queue2);
    }

    @Test
    void testDeQueueNegative() {
        final Queue<Integer> queue1 = createQueue();
        final Queue<Integer> queue2 = queue1.deQueue();
        assertTrue(queue1.isEmpty());
        assertTrue(queue2.isEmpty());
        assertNotEquals(queue1, queue2);
    }

    @Test
    void testHeadPositive() {
        final double expected = 1.2;
        final Queue<Double> queue1 = createQueue(asList(expected, 2.3, 2.01));
        final double actual = queue1.head();
        assertEquals(3, queue1.size());
        assertEquals(expected, actual);
    }

    @Test
    void testHeadNegative() {
        final Queue<Double> queue2 = createQueue();
        assertNull(queue2.head());
    }

    @Test
    void testIsEmpty() {
        final Queue<Long> queue1 = createQueue(asList(292191L, 292193L, 292192L));
        assertFalse(queue1.isEmpty());
        queue1.head();
        assertFalse(queue1.isEmpty());
        final Queue<Long> queue2 = createQueue();
        assertTrue(queue2.isEmpty());
    }

    @Test
    void testSize() {
        final Queue<Runnable> queue1 = createQueue(singletonList(() -> { }));
        assertEquals(1, queue1.size());
    }
}
