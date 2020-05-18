public class ArrayDeque<T> {

    /** invariants:
     * The index of the nextFirst is always first - 1.
     * The index of the nextLast is always last + 1.
     * The number of items in the ArrayDeque is always size.
     */

    private T[] items;
    private int size;
    private double usageRatio;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    private void biggerResize(int n) {
        T[] a = (T []) new Object[n];
        T[] copy = items;
        int last = minusOne(nextLast);
        int first = plusOne(nextFirst);
        System.arraycopy(items, 0, a, 0, plusOne(last));
        System.arraycopy(copy, first, a, first + size, size - first);
        items = a;
        nextFirst = minusOne(first + size);
        last = minusOne(nextLast);
    }

    private void smallerResize(int n) {
        T[] a = (T []) new Object[n];
        T[] copy = items;
        int first = nextFirst + 1;
        System.arraycopy(items, 0, a, 0, nextLast);
        System.arraycopy(copy, first, a, first - items.length / 2, items.length - first);
        items = a;
        nextFirst = minusOne(first - items.length);
    }

    private T getlast() {
        return items[minusOne(nextLast)];
    }

    private T getfirst() {
        return items[plusOne(nextFirst)];
    }

    private int minusOne(int index) {
        int j = index - 1;
        if (j == -1) {
            return items.length + j;
        } else {
            return j;
        }
    }

    private int plusOne(int index) {
        int i = index + 1;
        if (i == items.length) {
            return 0;
        } else {
            return i;
        }
    }

    public void addLast(T x) {
        if (size == items.length) {
            biggerResize(size * 2);
        }
        items[nextLast] = x;
        nextLast = plusOne(nextLast);
        size = size + 1;
    }

    public void addFirst(T x) {
        if (size == items.length) {
            biggerResize(size * 2);
        }
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size = size + 1;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        else {
            T item = getlast();
            nextLast = minusOne(nextLast);
            size = size - 1;
            if (size == 0) {
                nextFirst = 7;
                nextLast = 0;
            }
            usageRatio = (double) size / (double) items.length;
            if (items.length >= 16 && usageRatio < 0.25) {
                smallerResize(items.length / 2);
            }
            return item;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
        	return null;
        }
        else {
            T item = getfirst();
            nextFirst = plusOne(nextFirst);
            size = size - 1;
            if (size == 0) {
                nextFirst = 7;
                nextLast = 0;
            }
            usageRatio = (double) size / (double) items.length;
            if (items.length >= 16 && usageRatio < 0.25) {
                smallerResize(items.length / 2);
            }
            return item;
        }
    }

    public T get(int i) {
        int index = plusOne(nextFirst) + i;
        if (index >= items.length) {
            index = index - items.length;
        }
        return items[index];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i = i + 1) {
            T item = get(i);
            System.out.print(item);
            System.out.print(" ");
        }
    }
}
