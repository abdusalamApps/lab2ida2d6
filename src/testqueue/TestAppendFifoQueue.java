import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import queue_singlelinkedlist.FifoQueue;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestAppendFifoQueue {

    private FifoQueue<Integer> queue;
    private FifoQueue<Integer> queue1;

    @Test
    public final void testAppendTwoEmptyQueues() {
        queue = new FifoQueue<>();
        queue1 = new FifoQueue<>();
        assertEquals(0, queue.size());
        assertEquals(null, queue.peek());
        assertEquals(0, queue1.size());
        queue = null;
        queue1 = null;
    }

    @Test
    public final void testAppendEmptyToNonEmpty() {
        queue = new FifoQueue<>();
        queue1 = new FifoQueue<>();
        queue.offer(2);
        queue.offer(3);
        queue.append(queue1);
        assertEquals(2, queue.size());
        assertEquals(0, queue1.size());
        assertEquals(2, (int) queue.poll());
        assertEquals(3, (int) queue.poll());
        queue = null;
        queue1 = null;
    }

    @Test
    public final void testAppendNonEmptyToEmpty() {
        queue = new FifoQueue<>();
        queue1 = new FifoQueue<>();
        queue1.offer(1);
        queue1.offer(2);
        queue.append(queue1);
        assertEquals(0, queue1.size());
        assertEquals(2, queue.size());
        assertEquals(1, (int) queue.poll());
        assertEquals(2, (int) queue.poll());
        queue = null;
        queue1 = null;
    }

    @Test
    public final void testAppendNonEmptyToNonEmpty() {
        queue = new FifoQueue<>();
        queue1 = new FifoQueue<>();
        queue.offer(1);
        queue1.offer(2);
        queue.append(queue1);
        assertEquals(2, queue.size());
        assertEquals(0, queue1.size());
        assertEquals(1, (int) queue.poll());
        assertEquals(2, (int) queue.poll());
        queue = null;
        queue1 = null;
    }

    @Test
    public final void testAppendQueueToItself() {
        queue = new FifoQueue<>();
        queue.offer(93);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            queue.append(queue);
        });
        queue = null;
    }
}
