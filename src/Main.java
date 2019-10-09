import queue_singlelinkedlist.FifoQueue;

public class Main {
    public static void main(String[] args) {
        FifoQueue fifoQueue = new FifoQueue();
        fifoQueue.offer("Hello");
        fifoQueue.offer(253);
        System.out.println(fifoQueue.size());

        FifoQueue fifoQueue1 = new FifoQueue();

        fifoQueue.append(fifoQueue1);
        System.out.println(fifoQueue.size());
    }

}

