package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    List<Node> heap;
    HashMap<T, Integer> items;
    int size;

    public ArrayHeapMinPQ() {
        heap = new ArrayList<>();
        items = new HashMap<>();
        heap.add(0, null);
        size = 0;
    }

    private int leftIndex(int i) {
        return 2 * i;
    }

    private int rightIndex(int i) {
        return 2 * i + 1;
    }

    private int parentIndex(int i) {
        return i / 2;
    }


    private void swap(int i, int j) {
        Node temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);

        items.put(heap.get(i).item(), i);
        items.put(heap.get(j).item(), j);
    }

    private boolean less(int i, int j) {
        return heap.get(i).priority() < heap.get(j).priority();
    }

    private void swim(int i) {
        if (i == 1) return;
        int par = parentIndex(i);
        if (less(i, par)) {
            swap(par, i);
            swim(par);
        }
    }

    private int min(int i, int j) {
        if (heap.get(i).priority() < heap.get(j).priority()) return i;
        else return j;
    }

    private void sink(int i) {
        if (i > size / 2) return;
        int s = leftIndex(i);
        int right = rightIndex(i);
        if (right < size && less(right, s)) s = right;
        if (less(s, i)) {
            swap(s, i);
            sink(s);
        }

        /**
         int right = rightIndex(i);

         if (right > size) {
         if (less(left, i)) {
         swap(left, i);
         sink(left);
         }
         } else {
         if (less(left, i) && !less(right, i)) {
         swap(left, i);
         sink(left);
         }
         if (less(right, i) && !less(left, i)) {
         swap(right, i);
         sink(right);
         }
         if (less(left, i) && less(right, i)) {
         int min = min(left, right);
         swap(min, i);
         sink(min);
         }
         }
         */
    }


    @Override
    public void add(T item, double priority) {
        if (contains(item)) throw new IllegalArgumentException();
        size += 1;
        heap.add(size, new Node(item, priority));
        items.put(item, size);
        swim(size);
    }


    @Override
    public boolean contains(T item) {
        return items.containsKey(item);
    }

    @Override
    public T getSmallest() {
        return heap.get(1).item();
    }

    @Override
    public T removeSmallest() {
        T peek = getSmallest();
        swap(size, 1);
        Node n = heap.get(size);
        items.remove(n.item());
        heap.set(size, null);
        size -= 1;
        sink(1);
        return peek;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) throw new NoSuchElementException();
        int i = items.get(item);
        Node n = new Node(item, priority);
        heap.set(i, n);

        if (i != 1 && less(i, parentIndex(i))) swim(i);
        if (i <= size / 2 && (less(leftIndex(i), i) || less(rightIndex(i), i))) sink(i);
    }

    class Node {
        private final T myItem;
        private final double myPriority;

        private Node(T item, double priority) {
            myItem = item;
            myPriority = priority;
        }

        public T item() {
            return myItem;
        }

        public double priority() {
            return myPriority;
        }
    }
}
