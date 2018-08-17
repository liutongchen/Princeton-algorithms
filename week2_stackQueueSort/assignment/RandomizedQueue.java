/**
 * Created by Liutong Chen on 08/17/2018
 */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int actualSize;
    private Item[] arr;

    private void resize(int capacity) {
        Item[] newArr = (Item[]) new Object[capacity];
        for (int i = 0; i < actualSize; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    private void autoEnlarge() {
        int arrLen = arr.length;
        if (actualSize == arrLen) {
            resize(arrLen * 2);
        }
    }

    private void autoShrink() {
        int arrLen = arr.length;
        if (actualSize > 0 && actualSize == arrLen / 4) {
            resize(arrLen / 2);
        }
    }

    private int generateRandomIndex() {
        return StdRandom.uniform(0, actualSize);
    }

    private void assertQueueNotEmpty() {
        if (actualSize == 0) {
            throw new NoSuchElementException();
        }
    }

    private class RandomIterator implements Iterator<Item> {
        private Item[] randomArr;
        private int curId;

        private RandomIterator() {
            copyQueue();
            edu.princeton.cs.algs4.StdRandom.shuffle(randomArr);
            curId = 0;
        }

        private void copyQueue() {
            randomArr = (Item[]) new Object[actualSize];
            for (int i = 0; i < actualSize; i++) {
                randomArr[i] = arr[i];
            }
        }

        @Override
        public boolean hasNext() {
            return curId < actualSize;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = randomArr[curId];
            curId++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }


    }

    /**
     * Construct an empty randomized queue
     */
    public RandomizedQueue() {
        actualSize = 0;
        arr = (Item[]) new Object[2];
    }

    /**
     * Is the randomized queue empty?
     */
    public boolean isEmpty() {
        return actualSize == 0;
    }

    /**
     * Return the number of items on the randomized queue
     * @return
     */
    public int size() {
        return actualSize;
    }

    /**
     * Add the item
     * @param item
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        arr[actualSize++] = item;
        autoEnlarge();
    }

    /**
     * Remove and return a random item
     * @return
     */
    public Item dequeue() {
        assertQueueNotEmpty();
        int randomId = generateRandomIndex();
        Item item = arr[randomId];
        arr[randomId] = arr[actualSize - 1];
        arr[--actualSize] = null;
        autoShrink();
        return item;
    }

    /**
     * Return a random item (but do not remove it)
     * @return
     */
    public Item sample() {
        assertQueueNotEmpty();
        return arr[generateRandomIndex()];
    }

    /**
     * Return an independent iterator over items in random order
     * @return
     */
    public Iterator<Item> iterator() {
        // randomly generate the start point
        return new RandomIterator();
    }

    /**
     * Unit testing (optional)
     * @param args
     */
    public static void main(String[] args) {
        int testSize = 5;
        // test string
//        System.out.println("-------TEST STRING--------");
//        RandomizedQueue<String> stringRandomizedQueue = new RandomizedQueue<>();
//        for (int i = 0; i < testSize; i++) {
//            // test enqueue
//            stringRandomizedQueue.enqueue("ITEM_" + Integer.toString(i));
//        }
//
//        for (int i = 0; i < testSize; i++) {
//            // test sample
//            System.out.println("Sample: " + stringRandomizedQueue.sample());
//        }
//
//        // test iterator
//        for (String item : stringRandomizedQueue) {
//            System.out.println("item: " + item);
//        }
//
//        while (!stringRandomizedQueue.isEmpty()) {
//            // test dequeue
//            System.out.println("Dequeue: " + stringRandomizedQueue.dequeue());
//        }
//
//        System.out.println("Size: " + stringRandomizedQueue.size());

        // test int
        System.out.println("-------TEST INT--------");
        RandomizedQueue<Integer> intRandomizedQueue = new RandomizedQueue<>();
        for (int i = 0; i < testSize; i++) {
            // test enqueue
            intRandomizedQueue.enqueue(i);
        }

        for (int i = 0; i < testSize; i++) {
            // test sample
            System.out.println("Sample: " + intRandomizedQueue.sample());
        }

        // test iterator
        for (Integer item : intRandomizedQueue) {
            System.out.println("item: " + item);
        }

        while (!intRandomizedQueue.isEmpty()) {
            // test dequeue
            System.out.println("Dequeue: " + intRandomizedQueue.dequeue());
        }

        System.out.println("Size: " + intRandomizedQueue.size());

        // test corner cases
        intRandomizedQueue.iterator().next();
        intRandomizedQueue.sample();
        intRandomizedQueue.dequeue();
    }
}
