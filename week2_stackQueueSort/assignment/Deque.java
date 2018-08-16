/**
 * Created by Liutong Chen on 08/15/2018
 */

import java.util.*;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node next;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private void assertValidArg(Item arg) {
        if (arg == null) {
            throw new IllegalArgumentException();
        }
    }

    private void assertNotEmpty() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
    }



    /**
     * Construct an empty deque
     */
    public Deque() {
        size = 0;
        first = last = null;
    }

    /**
     * Check if the deque is empty
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return the number of items on the deque
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Add the item to the front
     * @param item
     */
    public void addFirst(Item item) {
        assertValidArg(item);
        size += 1;
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    /**
     * Add the item to the last
     * @param item
     */
    public void addLast(Item item) {
        assertValidArg(item);
        size += 1;
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        oldLast.next = last;
    }

    /**
     * Remove and return the item from the front
     * @return
     */
    public Item removeFirst() {
        assertNotEmpty();
        size -= 1;
        Node oldFirst = first;
        first = first.next;
        return oldFirst.item;
    }

    /**
     * Remove and return the item from the end
     * @return
     */
    public Item removeLast() {
        assertNotEmpty();
        size -= 1;
        Node oldLast = last;
        last = null;
        return oldLast.item;
    }

    /**
     * Return an iterator over items in order from front to end
     * @return
     */
    public Iterator<Item> iterator() {

    }


    public static void main(String[] args) {

    }
}