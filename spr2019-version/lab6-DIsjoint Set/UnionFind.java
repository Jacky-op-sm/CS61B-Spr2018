import java.util.ArrayList;

public class UnionFind {

    // TODO - Add instance variables?
    private ArrayList<Integer> arr;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("the length of array should be positive");
        }
        arr = new ArrayList<Integer>();
        for (int i = 0; i < n; i += 1) {
            arr.add(i, -1);
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < 0 || vertex >= arr.size()) {
            throw new IllegalArgumentException("index out of bound at" + vertex);
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        int root = find(v1);
        return -arr.get(root);
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return arr.get(v1);
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        if (!connected(v1, v2)) {
            int rootOfv1 = find(v1);
            int sizeOfv1 = sizeOf(v1);
            int rootOfv2 = find(v2);
            int sizeOfv2 = sizeOf(v2);
            int sizePlus = sizeOfv1 + sizeOfv1;
            if (sizeOfv1 <= sizeOfv2) {
                arr.set(rootOfv2, -sizePlus);
                arr.set(rootOfv1, rootOfv2);
            } else {
                arr.set(rootOfv1, -sizePlus);
                arr.set(rootOfv2, rootOfv1);
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        if (parent(vertex) < 0) {
            return vertex;
        }
        int helper = find(parent(vertex));
        arr.set(vertex, helper);
        return helper;
    }
}
