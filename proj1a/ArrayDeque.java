public class ArrayDeque<BleepBlorp> {

    /** invariants:
     * The index of the nextFirst is always first - 1.
     * The index of the nextLast is always Last + 1.
     * The number of items in the ArrayDeque is always size.
     */

    BleepBlorp[] items;
    int size;
    double usageRatio;
    int nextFirst;
    int nextLast;
    int First;
    int Last;

    public ArrayDeque() {
        items = (BleepBlorp []) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    public void BiggerResize(int n) {
        BleepBlorp[] a = (BleepBlorp []) new Object[n];
        BleepBlorp[] copy = items;
        System.arraycopy(items, 0, a, 0, PlusOne(Last));
        System.arraycopy(copy, First, a, First + size, size - First);
        items = a;
        nextFirst = MinusOne(First + size);
        Last = MinusOne(nextLast);
    }

    public void SmallerResize(int n) {
        BleepBlorp[] a = (BleepBlorp []) new Object[n];
        BleepBlorp[] copy = items;
        System.arraycopy(items, 0, a, 0, nextLast);
        System.arraycopy(copy, First, a, First - items.length/2, items.length - nextFirst - 1);
        items = a;
        nextFirst = MinusOne(First - items.length);
        First = PlusOne(nextFirst);
        Last = MinusOne(nextLast);
    }

    public BleepBlorp getLast() {
        return items[Last];
    }

    public BleepBlorp getFirst() {
        return items[First];
    }

    public int MinusOne(int index) {
        int j = index - 1;
        if (j == -1) {
            return items.length + j;
        } else {
            return j;
        }
    }

    public int PlusOne(int index) {
        int i = index + 1;
        if (i == items.length) {
            return 0;
        } else {
            return i;
        }
    }

    public void addLast(BleepBlorp x) {
        if (size == items.length) {
            BiggerResize(size * 2);
        }
        items[nextLast] = x;
        Last = nextLast;
        nextLast = nextLast + 1;
        size = size + 1;
        if (size == 1) {
            First = Last;
        }
    }

    public void addFirst(BleepBlorp x) {
        if (size == items.length) {
            BiggerResize(size * 2);
        }
        items[nextFirst] = x;
        First = nextFirst;
        nextFirst = MinusOne(nextFirst);
        size = size + 1;
        if (size == 1) {
            Last = First;
        }
    }

    public BleepBlorp removeLast() {
        BleepBlorp item = getLast();
        nextLast = MinusOne(nextLast);
        Last = MinusOne(Last);
        size = size - 1;
        usageRatio = (double)size / (double)items.length;
        if (items.length >= 16 && usageRatio < 0.25) {
            SmallerResize(items.length / 2);
        }
        return item;
    }

    public BleepBlorp removeFirst() {
        BleepBlorp item = getFirst();
        First = PlusOne(First);
        nextFirst = PlusOne(nextFirst);
        size = size - 1;
        usageRatio = (double)size / (double)items.length;
        if (items.length >= 16 && usageRatio < 0.25) {
            SmallerResize(items.length / 2);
        }
        return item;
    }

    public BleepBlorp get(int i) {
        int index = First + i;
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
            BleepBlorp item = get(i);
            System.out.print(item);
            System.out.print(" ");
        }
    }
}