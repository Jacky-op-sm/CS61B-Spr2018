package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF wqu;
    private boolean[] grid;
    private int n;
    private int size;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        validate(N);
        this.n = N;
        size = 0;
        int length = N * N + 2;
        wqu = new WeightedQuickUnionUF(length);
        grid = new boolean[length];
        grid[0] = true;
        grid[length - 1] = true;
        for (int i = 1; i <= length - 2; i += 1) {
            grid[i] = false;
        }
    }

    // use for unit testing (not required)
    private static void main(String[] args) {
        Percolation test = new Percolation(1);
        test.open(0, 0);
        System.out.println(test.percolates());
        System.out.println(test.numberOfOpenSites());
    }

    private int xyTo1D(int row, int col) {
        return n * row + col + 1;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int num = xyTo1D(row, col);
        if (!grid[num]) {
            grid[num] = true;
            size += 1;
            if (n == 1) {
                wqu.union(num, 0);
                wqu.union(num, 2);
            } else {
                if (row == 0) {
                    wqu.union(num, 0);
                    connectDown(row, col);
                } else if (row == n - 1) {
                    if (!percolates()) {
                        wqu.union(num, n * n + 1);
                    }
                    connectUp(row, col);
                } else {
                    connectDown(row, col);
                    connectUp(row, col);
                }
                if (col == 0) {
                    connectRight(row, col);
                } else if (col == n - 1) {
                    connectLeft(row, col);
                } else {
                    connectRight(row, col);
                    connectLeft(row, col);
                }
            }
        }
    }

    private void connectDown(int row, int col) {
        if (isOpen(row + 1, col)) {
            int i = xyTo1D(row, col);
            int k = xyTo1D(row + 1, col);
            wqu.union(i, k);
        }
    }

    private void connectLeft(int row, int col) {
        if (isOpen(row, col - 1)) {
            int i = xyTo1D(row, col);
            int k = xyTo1D(row, col - 1);
            wqu.union(i, k);
        }
    }

    private void connectUp(int row, int col) {
        if (isOpen(row - 1, col)) {
            int i = xyTo1D(row, col);
            int k = xyTo1D(row - 1, col);
            wqu.union(i, k);
        }
    }

    private void connectRight(int row, int col) {
        if (isOpen(row, col + 1)) {
            int i = xyTo1D(row, col);
            int k = xyTo1D(row, col + 1);
            wqu.union(i, k);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        int num = xyTo1D(row, col);
        return grid[num];
    }

    // validate that (row, col) is a valid
    private void validate(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    // validate that the size of grid is positive
    private void validate(int k) {
        if (k <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int num = xyTo1D(row, col);
        if (isOpen(row, col)) {
            return wqu.connected(num, 0);
        }
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return size;
    }

    // does the system percolate?
    public boolean percolates() {
        return wqu.connected(0, n * n + 1);
    }

}


