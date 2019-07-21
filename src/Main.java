import queue_singlelinkedlist.FifoQueue;

public class Main {
    public static void main(String[] args) {
        FifoQueue fifoQueue = new FifoQueue();
        fifoQueue.offer("Hello");
        fifoQueue.offer(253);
        System.out.println(fifoQueue.size());

        FifoQueue fifoQueue1 = new FifoQueue();
        fifoQueue1.offer("Hello3");
        fifoQueue1.offer(2533);

        fifoQueue.append(fifoQueue1);
        System.out.println(fifoQueue.size());
    }

}

