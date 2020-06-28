
import java.util.*;

public class MyHashMap<K, V>  implements Map61B<K, V>  {

    int initialSize;
    double loadFactor;
    SequentialSearchST<K, V>[] st;
    int size;
    HashSet<K> hashSet;

    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        st = (SequentialSearchST[]) new SequentialSearchST[initialSize];
        for (int i = 0; i < initialSize;i += 1) {
            st[i] = new SequentialSearchST<>();
        }
        hashSet = new HashSet<>();
    }

    private class SequentialSearchST<K, V> {
        Node first;
        int n;
        ArrayList<K> keys;

        public SequentialSearchST() {
            first = new Node();
        }

        public V get(K key) {
            return get(first, key);
        }

        private V get(Node n, K key) {
            if (n.next == null) {
                return null;
            }
            else if (key.equals(n.next.key)) {
                return n.next.val;
            }
            return get(n.next, key);
        }

        public void put(K key, V val) {
            put(first, key, val);
        }

        private void put(Node n, K key, V val) {
            if (n.next == null) {
                n.next = new Node(key, val, null);
            }
            else if (n.next.key == key) {
                n.next.val = val;
            }
            else {
                put(n.next, key, val);
            }
        }

        public V remove(K key) {
            return remove(first,key);
        }

        private V remove(Node n, K key) {
            if (n.next == null) {
                return null;
            }
            else if (n.next.key == key) {
                V val = n.next.val;
                n.next = n.next.next;
                return val;
            }
            return remove(n.next, key);
        }

        public V removeKV(K key, V val) {
            return removeKV(first, key, val);
        }

        private V removeKV(Node n, K key, V val) {
            if (n.next == null) {
                return null;
            }
            else if (n.next.key == key && n.next.val == val) {
                n.next = n.next.next;
                return val;
            }
            return removeKV(n.next, key, val);
        }

        public ArrayList<K> keys() {
            keys = getAllKeys(first);
            return keys;
        }

        private ArrayList<K> getAllKeys(Node n) {
            ArrayList<K> temp = new ArrayList<>();
            while (n.next != null) {
                temp.add(n.next.key);
                n = n.next;
            }
            return temp;
        }

        private class Node {
            K key;
            V val;
            Node next;

            public Node() {}

            public Node(K key, V val, Node next) {
                this.key = key;
                this.val = val;
                this.next = next;
            }
        }
    }

    private int hash(K key) {
        int i = key.hashCode() % initialSize;
        if (i < 0) {
            i += initialSize;
        }
        return i;
    }

    @Override
    public void clear() {
        MyHashMap<K, V> temp = new MyHashMap<>(initialSize);
        this.initialSize = temp.initialSize;
        this.size = temp.size;
        this.st = temp.st;
        this.hashSet = temp.hashSet;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        int i = hash(key);
        return st[i].get(key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        double useRatio = (double) size / initialSize;
        if (useRatio >= loadFactor) {
            resize(2 * initialSize);
        }
        int i = hash(key);
        if (!containsKey(key)) {
            size += 1;
        }
        st[i].put(key, value);
    }

    private void resize(int newSize) {
        MyHashMap<K, V> temp = new MyHashMap<>(newSize);
        for (int i = 0; i < initialSize; i += 1) {
            for (K key: st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.initialSize = temp.initialSize;
        this.size = temp.size;
        this.st = temp.st;
    }

    @Override
    public Set<K> keySet() {
        for (int i = 0; i < initialSize; i += 1) {
            for (K key : st[i].keys()) {
                hashSet.add(key);
            }
        }
        return hashSet;
    }

    @Override
    public V remove(K key) {
        int i = hash(key);
        return st[i].remove(key);
    }

    @Override
    public V remove(K key, V value) {
        int i = hash(key);
        return st[i].removeKV(key, value);
    }

    @Override
    public Iterator<K> iterator() {
        return hashSet.iterator();
    }
}
