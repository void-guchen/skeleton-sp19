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
        nextFirst = 0;
        nextLast = 1;
    }

    public ArrayDeque(ArrayDeque other) {
        this();
        for(int i = 0; i < other.size; i++) {
            addLast( (T) other.get(i));
        }
    }

    private void grow(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;
        System.out.println("*************** grow to " + capacity + " *************");
    }

    private void ensureUsageFactor() {
        if(items.length < 16) return;
        double usageFactor = usageFactor();
        if (usageFactor < LEAST_USAGE_FACTOR) {
            int newLen = (int) (size / DEFAULT_USAGE_FACTOR);
            T[] newItems = (T[]) new Object[newLen];
            System.arraycopy(items, 0, newItems, 0, size);
            items = newItems;
            System.out.println("************** shrink to " + newLen + " ****************");
        }
    }

    private void ensureCapacity() {
        if(size == items.length) {
            grow(size * GROW_FACTOR);
        }
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        ensureCapacity();
        for(int i = size - 1; i >= 0; i--) {
            items[i + 1] = items[i];
        }
        items[0] = item;
        size += 1;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        ensureCapacity();
        items[size] = item;
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
            builder.append(items[i]);
            builder.append(" ");
        }
        System.out.println(builder);
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        T first = get(0);
        for(int i = 1; i < size; i++) {
            items[i-1] = items[i];
        }
        items[size - 1] = null;
        size -= 1;
        ensureUsageFactor();
        return first;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        T last = get(size - 1);
        items[size - 1] = null;
        size -= 1;
        ensureUsageFactor();
        return last;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if(index < 0 || index > size -1) return null;
        return items[index];
    }
}
