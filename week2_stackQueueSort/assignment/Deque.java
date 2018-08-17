/**
 * Created by Liutong Chen on 08/15/2018
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;
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

    private void reduceSize() {
        assertNotEmpty();
        size -= 1;
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
        if (size == 1) {
            last = first;
        } else {
            oldFirst.prev = first;
        }
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
        last.prev = oldLast;
        if (size == 1) {
            first = last;
        } else {
            oldLast.next = last;
        }
    }

    /**
     * Remove and return the item from the front
     * @return
     */
    public Item removeFirst() {
        reduceSize();
        Item firstItem = first.item;
        if (size == 0) {
            first = last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        return firstItem;
    }

    /**
     * Remove and return the item from the end
     * @return
     */
    public Item removeLast() {
        reduceSize();
        Item lastItem = last.item;
        if (size == 0) {
            first = last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        return lastItem;
    }

    /**
     * Return an iterator over items in order from front to end
     * @return
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    /**
     * Unit testing
     * @param args
     */
    public static void main(String[] args) {
        // test strings
        System.out.println("------------TEST STRING---------");
        Deque<String> stringDeque = new Deque<>();
        if (stringDeque.isEmpty()) {
            // test add
            for (int i = 0; i < 5; i++) {
                stringDeque.addFirst("ADD_FIRST_" + Integer.toString(i));
                stringDeque.addLast("ADD_LAST_" + Integer.toString(i));
            }
        }

        // test iterator
        for (String item : stringDeque) {
            System.out.println("ITEM: " + item);
        }

        if (!stringDeque.isEmpty()) {
            // test remove
            for (int i = 0; i < 5; i++) {
                System.out.println("REMOVE_FIRST_" + stringDeque.removeFirst() + "---------");
                System.out.println("REMOVE_Last_" + stringDeque.removeLast() + "---------");
            }
        }
        System.out.println("size: " + stringDeque.size());

        // test int
        System.out.println("------------TEST INT---------");
        Deque<Integer> integerDeque = new Deque<>();
        if (integerDeque.isEmpty()) {
            // test add
            for (int i = 0; i < 5; i++) {
                integerDeque.addFirst(i);
                integerDeque.addLast(i + 5);
            }
        }

        // test iterator
        for (Integer item : integerDeque) {
            System.out.println("ITEM: " + item);
        }

        if (!integerDeque.isEmpty()) {
            // test remove
            for (int i = 0; i < 5; i++) {
                System.out.println("REMOVE_FIRST_" + integerDeque.removeFirst() + "---------");
                System.out.println("REMOVE_Last_" + integerDeque.removeLast() + "---------");
            }
        }
        System.out.println("size: " + integerDeque.size());
    }
}