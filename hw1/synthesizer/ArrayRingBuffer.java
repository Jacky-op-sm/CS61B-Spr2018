// Todo: Make sure to make this class a part of the synthesizer package
package synthesizer;
import java.util.Iterator;

//Todo: Make sure to make this class and all of its methods public
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // Todo: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.capacity = capacity;
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    private int update(int i) {
        if (i == capacity) {
            return 0;
        }
        return i;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        // Todo: Enqueue the item. Don't forget to increase fillCount and update last.
        rb[last] = x;
        fillCount += 1;
        last += 1;
        last = update(last);
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        // Todo: Dequeue the first item. Don't forget to decrease fillCount and update
        T item = rb[first];
        first += 1;
        fillCount -= 1;
        first = update(first);
        return item;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("No Peek!");
        }
        // Todo: Return the first item. None of your instance variables should change.
        return rb[first];
    }

    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        int start;

        ArrayRingBufferIterator() {
            start = first;
        }

        public boolean hasNext() {
            return start <= last;
        }

        public T next() {
            T returnItem = rb[start];
            start += 1;
            return returnItem;
        }
    }
    // Todo: When you get to part 5, implement the needed code to support iteration.
}
