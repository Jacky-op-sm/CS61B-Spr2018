public class LinkedListDeque<T> {

    /** invariants:
     * The sentinel reference always points to a sentinel node.
     * The first item is always at sentinel.next.item.
     * The size variable is always the total number of items that have been added.
     */

    private class IntNode {
        private IntNode prev;
        private T item;
        private IntNode next;

        public IntNode() {
        }

        public IntNode(IntNode p, T i, IntNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private IntNode sentinel;
    private IntNode last;
    private int size;

    public LinkedListDeque() {
        sentinel = new IntNode();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        last = sentinel;
        size = 0;
    }

    public void addFirst(T x) {
        sentinel.next = new IntNode(sentinel, x, sentinel.next);
        if (sentinel.next.next == sentinel) {
            last = sentinel.next;
        }
        sentinel.next.next.prev = sentinel.next;
        size = size + 1;
    }

    public void addLast(T x) {
        last.next = new IntNode(last, x, sentinel);
        last = last.next;
        sentinel.prev = last;
        size = size + 1;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            T item = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            if (sentinel.next == sentinel) {
                last = sentinel;
            }
            sentinel.next.prev = sentinel;
            size = size - 1;
            return item;
        }
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            T item = last.item;
            last = last.prev;
            last.next = sentinel;
            sentinel.prev = last;
            size = size - 1;
            return item;
        }
    }

    public T get(int index) {
        IntNode p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index = index - 1;
        }
        return p.item;
    }

    private T getRecursiveHelper(int index, IntNode p) {
        if (index == 0) {
            return p.item;
        }
        return getRecursiveHelper(index - 1, p.next);
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        IntNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
        }
    }
    System.out.println();

}