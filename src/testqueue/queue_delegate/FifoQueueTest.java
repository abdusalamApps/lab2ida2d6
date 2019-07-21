package queue_delegate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import queue_singlelinkedlist.FifoQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FifoQueueTest {

    private Queue<Integer> myIntQueue;
    private Queue<String> myStringQueue;

    /**
     * Test if a newly created queue is empty.
     */
    @Test
    public final void testNewFifoQueue() {
        myIntQueue = new FifoQueue<>();
        assertTrue(myIntQueue.isEmpty());
        assertTrue(myIntQueue.size() == 0);
        myIntQueue = null;
    }

    /**
     * Test a single offer followed by a single peek.
     */
    @Test
    public final void testPeek() {
        myIntQueue = new FifoQueue<>();
        myIntQueue.offer(1);
        int i = myIntQueue.peek();
        assertEquals(1, i, "peek on queue of size 1");
        assertTrue(myIntQueue.size() == 1);
        myIntQueue = null;
    }

    /**
     * Test a single offer followed by a single poll.
     */
    @Test
    public final void testPoll() {
        myIntQueue = new FifoQueue<>();
        myIntQueue.offer(1);
        int i = myIntQueue.poll();
        assertEquals(1, i, "poll on queue of size 1");
        assertTrue(myIntQueue.size() == 0, "Wrong size after poll");
        myIntQueue = null;
    }

    /**
     * Test peek of empty queue.
     */
    @Test
    public final void testPeekOfEmpty() {
        myIntQueue = new FifoQueue<>();
        assertEquals(null, myIntQueue.peek(), "Front of empty queue not null");
        myIntQueue = null;
    }

    /**
     * Test poll of empty queue.
     */
    @Test
    public final void testPollOfEmpty() {
        myIntQueue = new FifoQueue<>();
        assertEquals(null, myIntQueue.poll(), "Poll of empty queue should return null");
        myIntQueue = null;
    }

    /**
     * Test that implementation works for a queue of strings.
     */
    @Test
    public final void testStringQueue() {
        myStringQueue = new FifoQueue<>();
        myStringQueue.offer("First");
        myStringQueue.offer("Second");
        myStringQueue.offer("Third");
        assertTrue(myStringQueue.size() == 3, "Wrong size of queue");
        assertEquals("First", myStringQueue.peek(), "peek on queue of strings");
        assertEquals("First", myStringQueue.poll(), "String First expected");
        assertEquals("Second", myStringQueue.poll(),"String Second expected");
        assertEquals( "Third", myStringQueue.poll(), "String Third expected");
        assertTrue(myStringQueue.isEmpty(), "Queue of strings should be empty");
        myStringQueue = null;
    }

    /**
     * Test that polling gives elements in right order.
     */
    @Test
    public final void testOrder() {
        myIntQueue = new FifoQueue<>();
        myIntQueue.offer(1);
        myIntQueue.offer(2);
        myIntQueue.offer(3);
        myIntQueue.offer(4);
        myIntQueue.offer(5);
        for (int i = 1; i <= 5; i++) {
            int k = myIntQueue.poll();
            assertEquals(i, k, "poll returns incorrect element");
        }
        assertTrue(myIntQueue.isEmpty(), "Queue not empty");
        myIntQueue = null;
    }

    /**
     * Test that polling all elements gives an empty queue.
     */
    @Test
    public final void testMakeQueueEmpty() {
        myIntQueue = new FifoQueue<>();
        myIntQueue.offer(1);
        myIntQueue.offer(2);
        myIntQueue.poll();
        myIntQueue.poll();
        assertTrue(myIntQueue.size() == 0, "Wrong size after poll");
        assertTrue(myIntQueue.isEmpty(), "Queue not empty after poll");
        myIntQueue.offer(3);
        myIntQueue.offer(4);
        assertTrue(myIntQueue.size() == 2, "Wrong size after offer");
        for (int i = 3; i <= 4; i++) {
            int k = myIntQueue.poll();
            assertEquals(i, k, "poll returns incorrect element");
        }
        assertTrue(myIntQueue.size() == 0, "Wrong size after poll");
        assertTrue(myIntQueue.isEmpty(), "Queue not empty after poll");
        myIntQueue = null;
    }

    /**
     * Test iterator on empty queue.
     */
    @Test
    public void testIteratorEmptyQueue() {
        myIntQueue = new FifoQueue<>();
        Iterator<Integer> itr = myIntQueue.iterator();
        assertFalse(itr.hasNext(), "Wrong result from next in empty queue");
        try {
            itr.next();
            fail("Should raise NoSuchElementException");
        } catch (NoSuchElementException e) {
            // successful test
        }
        myIntQueue = null;
    }

    /**
     * Test iterator on non empty queue.
     */
    @Test
    public void testIteratorNonEmptyQueue() {
        myIntQueue = new FifoQueue<>();
        int nbr = 5;
        for (int i = 1; i <= nbr; i++) {
            myIntQueue.offer(i);
        }
        Iterator<Integer> itr = myIntQueue.iterator();
        System.out.println(itr.hasNext());
        assertTrue(itr.hasNext(), "Wrong result from hasNext");
        for (int i = 1; i <= nbr; i++) {
            assertTrue(itr.hasNext(), "Wrong result from hasNext");
            assertEquals(Integer.valueOf(i), itr.next(), "Wrong result from next");
        }
        assertFalse(itr.hasNext(), "Wrong result from hasNext");
        try {
            itr.next();
            fail("Should raise NoSuchElementException");
        } catch (NoSuchElementException e) {
            // successful test
        }
        myIntQueue = null;
    }

    /**
     * Test iterator multiple times, to ensure that the iterator does not modify the
     * queue.
     */
    @Test
    public void testThreeIterators() {
        myIntQueue = new FifoQueue<>();
        int nbr = 5;
        for (int i = 1; i <= nbr; i++) {
            myIntQueue.offer(i);
        }

        // first, set up two iterators at the same time, and check that they both work

        Iterator<Integer> itr1 = myIntQueue.iterator();
        Iterator<Integer> itr2 = myIntQueue.iterator();

        for (int i = 1; i <= nbr; i++) {
            assertTrue(itr1.hasNext(), "Wrong result from itr1.hasNext");
            assertEquals(Integer.valueOf(i), itr1.next(), "Wrong result from itr1.next");
        }

        for (int i = 1; i <= nbr; i++) {
            assertTrue(itr2.hasNext(), "Wrong result from itr2.hasNext");
            assertEquals(Integer.valueOf(i), itr2.next(), "Wrong result from itr2.next");
        }

        // then, test a third iterator after the previous iterations

        Iterator<Integer> itr3 = myIntQueue.iterator();
        for (int i = 1; i <= nbr; i++) {
            assertTrue(itr3.hasNext(), "Wrong result from itr3.hasNext");
            assertEquals(Integer.valueOf(i), itr3.next(), "Wrong result from itr3.next");
        }
        myIntQueue = null;
    }
}