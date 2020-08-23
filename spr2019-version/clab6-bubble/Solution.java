class Solution {

    public class WeightedQuickUnionUF {
        private int[] parent;   // parent[i] = parent of i
        private int[] size;     // size[i] = number of elements in subtree rooted at i
        private int count;      // number of components

        public WeightedQuickUnionUF(int n) {
            count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int count() {
            return count;
        }
      
        public int find(int p) {
            validate(p);
            while (p != parent[p])
                p = parent[p];
            return p;
        }

        @Deprecated
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        // validate that p is a valid index
        private void validate(int p) {
            int n = parent.length;
            if (p < 0 || p >= n) {
                throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));  
            }
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;

            // make smaller root point to larger one
            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
            count--;
        }
    }


    int row;
    int col;
    int[][] grid;
    WeightedQuickUnionUF wqu;
    int[] output;


    private WeightedQuickUnionUF wquInit(int[][] grid) {
        row = grid.length;
        col = grid[0].length;
        wqu = new WeightedQuickUnionUF(row * col + 1);
        if (col == 1) {
            wqu.union(1,0);
            for (int j = 1; j < row; j += 1) {
                if (isOpen(j, 0)) {
                    connectUp(j, 0);
                }
            }
        }
        else {
            for (int j = 0; j < col; j += 1) {
                if (isOpen(i, j)) {
                    int temp = xyTo1D(0, j);
                    wqu.union(temp, 0);
                }
            }

            for (int i = 1; i <= row - 2; i += 1) {
                int j = 0;
                if (isOpen(i, j)) {
                    connectUp(i, j);
                    connectRight(i, j);
                    connectDown(i, j);
                }
                j += 1;
                for (; j <= col - 2; j += 1) {
                    if (isOpen(i, j)) {
                        connectUp(i, j);
                        connectRight(i, j);
                        connectDown(i, j);
                        connectLeft(i, j);
                    }
                }
                if (isOpen(i, j)) {
                    connectUp(i, j);
                    connectDown(i, j);
                    connectLeft(i, j);
                }
            }

            for (int j = 0; j < col; j += 1) {
                int i = row - 1;
                if (j == 0) {
                    if (isOpen(i, j)) {
                        connectUp(i, j);
                        connectRight(i, j);
                    }
                } else if (j == col - 1) {
                    if (isOpen(i, j)) {
                        connectUp(i, j);
                        connectLeft(i, j);
                    }
                } else {
                    if (isOpen(i, j)) {
                        connectUp(i, j);
                        connectRight(i, j);
                        connectLeft(i, j);
                    }
                }
            }
        }
        return wqu;
    }

    private void connectDown(int r, int c) {
        if (isOpen(r + 1, c)) {
            int i = xyTo1D(r, c);
            int k = xyTo1D(r + 1, c);
            wqu.union(i, k);
        }
    }

    private void connectLeft(int r, int c) {
        if (isOpen(r, c - 1)) {
            int i = xyTo1D(r, c);
            int k = xyTo1D(r, c - 1);
            wqu.union(i, k);
        }
    }

    private void connectUp(int r, int c) {
        if (isOpen(r - 1, c)) {
            int i = xyTo1D(r, c);
            int k = xyTo1D(r - 1, c);
            wqu.union(i, k);
        }
    }

    private void connectRight(int r, int c) {
        if (isOpen(r, c + 1)) {
            int i = xyTo1D(r, c);
            int k = xyTo1D(r, c + 1);
            wqu.union(i, k);
        }
    }

    private boolean isOpen(int r, int c) {
        return grid[r][c] == 1;
    }

    private int connectSize(WeightedQuickUnionUF wqu) {
        int result = 0;
        for (int i = 1; i < row * col + 1; i += 1) {
            if (wqu.connected(0, i)) {
                result += 1;
            }
        }
        return result;
    }


    public int[] hitBricks(int[][] grid, int[][] hits) {
        output = new int[hits.length];
        this.grid = grid;
        int outputIndex = 0;
        for (int[] hit : hits) {
            int r = hit[0];
            int c = hit[1];
            if (grid[r][c] == 0) {
                output[outputIndex] = 0;
            } else {
                WeightedQuickUnionUF wqu1 = wquInit(this.grid);
                int r1 = connectSize(wqu1);
                this.grid[r][c] = 0;
                WeightedQuickUnionUF wqu2 = wquInit(this.grid);
                int r2 = connectSize(wqu2);
                int rm = r1 - r2;
                if (rm <= 1) {
                    output[outputIndex] = 0;
                }
                else {
                    output[outputIndex] = rm - 1;
                }

            }
            outputIndex += 1;
        }
        return output;
    }

    private int xyTo1D(int i, int j) {
        return i * col + j + 1;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1},{1},{1},{1},{1}};
        int[][] hits = new int[][]{{1,0}};
        Solution s = new Solution();
        int[] output = s.hitBricks(grid, hits);
        System.out.println(output);
    }
}
