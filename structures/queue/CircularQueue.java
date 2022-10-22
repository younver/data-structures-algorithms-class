package structures.queue;

public class CircularQueue {
    int head = 0;
    int tail = 0;

    // tail to insert; head to discard; FIFO
    // boolean isEmpty : returns head == tail;
    // void enqueue : if full, throw exception; increment tail, set value;
    // dequeue : if empty, throw exception; increment head, return value;
    // isFull : (tail+1) % size() == head
}
