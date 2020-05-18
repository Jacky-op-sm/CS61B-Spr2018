public class LinkedListDeque<BleepBlorp> {

    /** invariants:
     * The sentinel reference always points to a sentinel node.
     * The first item is always at sentinel.next.item.
     * The size variable is always the total number of items that have been added.
     */

    public class IntNode {
        public IntNode prev;
        public BleepBlorp item;
        public IntNode next;

        public IntNode() {
        }

        public IntNode(IntNode p, BleepBlorp i, IntNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    public IntNode sentinel;
    public IntNode last;
    public int size;

    public LinkedListDeque() {
        sentinel = new IntNode();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        last = sentinel;
        size = 0;
    }

    public void addFirst(BleepBlorp x) {
        sentinel.next = new IntNode(sentinel, x, sentinel.next);
        if (sentinel.next.next == sentinel) {
            last = sentinel.next;
        }
        sentinel.next.next.prev = sentinel.next;
        size = size + 1;
    }

    public void addLast(BleepBlorp x) {
        last.next = new IntNode(last, x, sentinel);
        last = last.next;
        sentinel.prev = last;
        size = size + 1;
    }

    public BleepBlorp removeFirst() {
        BleepBlorp item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        if (sentinel.next == sentinel) {
            last = sentinel;
        }
        sentinel.next.prev = sentinel;
        size = size - 1;
        return item;
    }

    public BleepBlorp removeLast() {
        BleepBlorp item = last.item;
        last = last.prev;
        last.next = sentinel;
        sentinel.prev = last;
        size = size - 1;
        return item;
    }

    public BleepBlorp get(int index) {
        IntNode p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index = index -1;
        }
        return p.item;
    }

    public BleepBlorp getRecursiveHelper(int index, IntNode p) {
        if (index == 0) {
            return p.item;
        }
        return getRecursiveHelper(index - 1, p.next);
    }

    public BleepBlorp getRecursive(int index) {
        return getRecursiveHelper(index, sentinel);
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
}