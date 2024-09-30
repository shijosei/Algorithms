import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom; // Import StdRandom instead of java.util.Random.

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size;

    @Override
    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator ();
    }

    // return an independent iterator over items in random order
    private class RandomizedQueueIterator  implements Iterator<Item> {
        private final Item[] items;
        private int current;

        public RandomizedQueueIterator() {
            items = (Item[]) new Object[size];

            System.arraycopy(queue, 0, items, 0, size);
            shuffle(items);
            current = 0;
        }

        @Override
        public boolean hasNext() {
            return current < items.length;
        }

        public void shuffle(Item[] array) {
            for (int i = array.length - 1; i > 0; i--) {
                int index = StdRandom.uniformInt(i + 1);
                Item temp = array[i];
                array[i] = array[index];
                array[index] = temp;
            }
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            return items[current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[2];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // resize the array
    private void resize(int newSize) {
        Item[] newQueue = (Item[]) new Object[newSize];

        if (size >= 0) System.arraycopy(queue, 0, newQueue, 0, size);
        queue = newQueue;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Cannot enqueue null item");

        if (size == queue.length) {
            resize(queue.length * 2); // double the array size if needed
        }
        queue[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");

        int randomIndex = StdRandom.uniformInt(size);
        Item item = queue[randomIndex];
        queue[randomIndex] = queue[size - 1]; // swap the random item with the last item
        queue[size - 1] = null; // avoid loitering
        size--;

        if (size > 0 && size == queue.length / 4) {
            resize(queue.length / 2);
        }

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");

        int randomIndex = StdRandom.uniformInt(size);
        return queue[randomIndex];
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        System.out.println("Sample: " + queue.sample());
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Size after dequeue: " + queue.size());

        for (int i : queue) {
            System.out.println(i);
        }
    }
}
