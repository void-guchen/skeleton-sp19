import java.awt.*;

/**
 * @author guchen
 * @date 2019/8/12 - 下午9:41
 */
@SuppressWarnings("all")
public class ArrayDeque<T> {
    private static final int DEFAULT_CAPACITY = 8;
    private static final int GROW_FACTOR = 2;
    private static final double LEAST_USAGE_FACTOR = 0.25;
    private static final double DEFAULT_USAGE_FACTOR = 0.5;
    private T[] items;
    private int size;
    // Add these two field to avoid frequently rotating array.
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    public ArrayDeque(ArrayDeque other) {
        this();
        for(int i = 0; i < other.size; i++) {
            addLast( (T) other.get(i));
        }
    }

    private void grow(int capacity) {
        boolean flag = capacity > items.length ? true : false;
        T[] newItems = (T[]) new Object[capacity];
        int l = (capacity - size) / 2;
        int left = l;
        int right = l + size - 1;
        int index = plusOne(nextFirst);
        for(int i = left; i <= right; i++) {
            newItems[i] = items[index];
            index = plusOne(index);
        }
        items = newItems;
        nextFirst = left - 1;
        nextLast = right + 1;
        System.out.println("*************** " + ((flag == true) ? "grow" : "narrow") + " to " + capacity + " *************");
    }

    private void ensureUsageFactor() {
        if(items.length < 16) return;
        double usageFactor = usageFactor();
        //System.out.println("current usagefactor : " + usageFactor);
        if (usageFactor < LEAST_USAGE_FACTOR) {
            int capacity = (int) (size / DEFAULT_USAGE_FACTOR);
            grow(capacity);
        }
    }

    private void ensureCapacity() {
        if(size == capacity()) {
            grow(size * GROW_FACTOR);
        }
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        ensureCapacity();
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        ensureCapacity();
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
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

    public int capacity() {
        return items.length;
    }

    public double usageFactor() {
        double usageFactor = (double) size / (double) items.length;
        usageFactor = (double) Math.round(usageFactor * 100) / 100;
        return usageFactor;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < size; i++) {
            builder.append(get(i));
            builder.append(" ");
        }
        System.out.println(builder);
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        if(size == 0) return null;
        int firIndex = plusOne(nextFirst);
        T first = items[firIndex];
        items[firIndex] = null;
        nextFirst = firIndex;
        size -= 1;
        ensureUsageFactor();
        return first;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if(size == 0) return null;
        int lastIndex = minusOne(nextLast);
        T last = items[lastIndex];
        items[lastIndex] = null;
        nextLast = lastIndex;
        size -= 1;
        ensureUsageFactor();
        return last;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if(index < 0 || index > size -1) return null;
        int firIndex = plusOne(nextFirst);
        int realIndex = (firIndex + index) % items.length;
        return items[realIndex];
    }

    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

}
