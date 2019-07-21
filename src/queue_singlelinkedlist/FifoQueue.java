package queue_singlelinkedlist;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {

    private QueueNode<E> last;
    private int size;

    public FifoQueue() {
        super();
        last = null;
        size = 0;
    }

    /**
     * Inserts the specified element into this queue, if possible
     * post:	The specified element is added to the rear of this queue
     *
     * @param e the element to insert
     * @return true if it was possible to add the element
     * to this queue, else false
     */
    public boolean offer(E e) {
        QueueNode<E> temp;
        if (isEmpty()) {
            temp = new QueueNode<>(e);
            last = temp;
            last.next = last;
        } else {
            temp = new QueueNode<>(e);
            temp.next = last.next;
            last.next = temp;
            last = temp;
        }
        size++;
        return true;
    }

    /**
     * Returns the number of elements in this queue
     *
     * @return the number of elements in this queue
     */
    public int size() {
        return size;
    }

    /**
     * Retrieves, but does not remove, the head of this queue,
     * returning null if this queue is empty
     *
     * @return the head element of this queue, or null
     * if this queue is empty
     */
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return last.next.element;
    }

    /**
     * Retrieves and removes the head of this queue,
     * or null if this queue is empty.
     * post:	the head of the queue is removed if it was not empty
     *
     * @return the head of this queue, or null if the queue is empty
     */
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        QueueNode<E> out = last.next;
        last.next = out.next;
        size--;
        return out.element;
    }

    /**
     * Returns an iterator over the elements in this queue
     *
     * @return an iterator over the elements in this queue
     */
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    public void append(FifoQueue<E> q) {
        if (q.last == last) throw new IllegalArgumentException();
        if (isEmpty()) {
            last = q.last;
            size += q.size;
        } else if (!q.isEmpty()) {
            QueueNode<E> temp = last.next;
            last.next = q.last.next;
            q.last.next = temp;
            last = q.last;
            size += q.size;
        }
        q.last = null;
        q.size = 0;
    }

    private static class QueueNode<E> {
        E element;
        QueueNode<E> next;

        private QueueNode(E x) {
            element = x;
            next = null;
        }
    }

    private class QueueIterator implements Iterator<E> {

        private QueueNode<E> pos;
        private int count;

        private QueueIterator() {
            if (last == null) {
                pos = null;
            } else {
                pos = last.next;
            }
            count = 0;
        }

        public boolean hasNext() {
            if (pos == null) {
                return false;
            }

            return count < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                QueueNode<E> temp = pos;
                pos = temp.next;
                count++;
                return temp.element;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
