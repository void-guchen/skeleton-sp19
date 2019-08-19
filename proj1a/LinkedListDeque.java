/**
 * Deque (usually pronounced like “deck”) is an irregular acronym of double-ended queue.
 * Double-ended queues are sequence containers with dynamic sizes that can be expanded or contracted
 * on both ends (either its front or its back).
 * @author guchen
 * @date 2019/8/12 - 下午3:59
 */
public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    /**
     * Creates an empty linked list deque.
     */
    public LinkedListDeque() {
        //initializes an empty deque with one head node called sentinel.
        sentinel = new Node();
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     * Creates a deep copy of other.
     */
    public LinkedListDeque(LinkedListDeque other) {
        this();
        Node p = other.sentinel.next;
        while (p != sentinel) {
            addLast(p.item);
        }
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        Node node = new Node(item, sentinel, sentinel.next);
        sentinel.next.pre = node;
        sentinel.next = node;
        size += 1;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        Node node = new Node(item, sentinel.pre, sentinel);
        sentinel.pre.next = node;
        sentinel.pre = node;
        size += 1;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        StringBuilder builder = new StringBuilder();
        Node p = sentinel.next;
        while (p != sentinel) {
            builder.append(p.item);
            builder.append(" ");
            p = p.next;
        }
        System.out.println(builder);
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node firNode = sentinel.next;
        T firItem = firNode.item;
        firNode.next.pre = firNode.pre;
        sentinel.next = firNode.next;
        firNode.item = null; //is it necessary?
        size -= 1;
        return firItem;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node lastNode = sentinel.pre;
        T lastItem = lastNode.item;
        lastNode.pre.next = lastNode.next;
        sentinel.pre = lastNode.pre;
        lastNode.item = null;
        size -= 1;
        return lastItem;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        Node p = sentinel;
        int count = 0;
        while (count <= index) {
            p = p.next;
            count += 1;
        }
        return p.item;
    }

    /**
     * Sames as get(int index), but uses recursion.
     */
    public T getRecursive(int index) {
        return getFromSub(sentinel.next, index);
    }

    private T getFromSub(Node sub, int index) {
        if (sub == sentinel) {
            return null;
        }
        if (index == 0) {
            return sub.item;
        }
        return getFromSub(sub.next, index - 1);
    }

    //TODO : figure out access control of nested class
    private class Node {
        private T item;
        private Node pre;
        private Node next;
        private Node() { }

        private Node(T item, Node pre, Node next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }
}
