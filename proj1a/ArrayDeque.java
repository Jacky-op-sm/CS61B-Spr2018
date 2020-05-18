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
    private int first;
    private int last;

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    public void biggerResize(int n) {
        T[] a = (T []) new Object[n];
        T[] copy = items;
        System.arraycopy(items, 0, a, 0, plusOne(last));
        System.arraycopy(copy, first, a, first + size, size - first);
        items = a;
        nextFirst = minusOne(first + size);
        last = minusOne(nextLast);
    }

    public void smallerResize(int n) {
        T[] a = (T []) new Object[n];
        T[] copy = items;
        System.arraycopy(items, 0, a, 0, nextLast);
        System.arraycopy(copy, first, a, first - items.length/2, items.length - nextFirst - 1);
        items = a;
        nextFirst = minusOne(first - items.length);
        first = plusOne(nextFirst);
        last = minusOne(nextLast);
    }

    public T getlast() {
        return items[last];
    }

    public T getfirst() {
        return items[first];
    }

    public int minusOne(int index) {
        int j = index - 1;
        if (j == -1) {
            return items.length + j;
        } else {
            return j;
        }
    }

    public int plusOne(int index) {
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
        last = nextLast;
        nextLast = nextLast + 1;
        size = size + 1;
        if (size == 1) {
            first = last;
        }
    }

    public void addFirst(T x) {
        if (size == items.length) {
            biggerResize(size * 2);
        }
        items[nextFirst] = x;
        first = nextFirst;
        nextFirst = minusOne(nextFirst);
        size = size + 1;
        if (size == 1) {
            last = first;
        }
    }

    public T removeLast() {
        if (isEmpty()) {return null;
        } else {
        T item = getlast();
        nextLast = minusOne(nextLast);
        last = minusOne(last);
        size = size - 1;
        usageRatio = (double) size / (double) items.length;
        if (items.length >= 16 && usageRatio < 0.25) {
            smallerResize(items.length / 2);
        }
        return item;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {return null;
        } else {
        T item = getfirst();
        first = plusOne(first);
        nextFirst = plusOne(nextFirst);
        size = size - 1;
        usageRatio = (double)size / (double)items.length;
        if (items.length >= 16 && usageRatio < 0.25) {
            smallerResize(items.length / 2);
        }
        return item;
        }
    }

    public T get(int i) {
        int index = first + i;
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