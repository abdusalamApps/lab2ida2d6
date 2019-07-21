import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import queue_singlelinkedlist.FifoQueue;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestAppendFifoQueue {

    @Test
    public final void testAppenTwoEmptyQueues() {
        FifoQueue<String> queue = new FifoQueue();
    }
}
