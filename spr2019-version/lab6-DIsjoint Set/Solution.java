
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

class Solution {
    int row;
    int col;
    WeightedQuickUnionUF wqu;
    int[] output;

    private WeightedQuickUnionUF wquInit(int[][] grid) {
        row = grid.length;
        col = grid[0].length;
        wqu = new WeightedQuickUnionUF(row * col + 1);
        for (int i = 0; i < row; i += 1) {
            for (int j = 0; j < col; j += 1) {
                if (grid[i][j] == 1) {
                    int temp = xyTo1D(i, j);
                    wqu.union(temp, 0);
                }
            }
        }
        return wqu;
    }

    private WeightedQuickUnionUF wquHits(int[][] grid, int[] hit) {
        int r = hit[0];
        int c = hit[1];
        grid[r][c] = 0;
        WeightedQuickUnionUF wqu = wquInit(grid);
        return wqu;
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
        int outputIndex = 0;
        for (int[] hit : hits) {
            int r = hit[0];
            int c = hit[1];
            if (grid[r][c] == 0) {
                output[outputIndex] = 0;
            } else {
                WeightedQuickUnionUF wqu1 = wquInit(grid);
                int r1 = connectSize(wqu1);
                grid[r][c] = 0;
                WeightedQuickUnionUF wqu2 = wquInit(grid);
                int r2 = connectSize(wqu2);
                output[outputIndex] = r1 - r2;
            }
            outputIndex += 1;
        }
        return output;
    }

    private int xyTo1D(int i, int j) {
        return i * row + j * col + 1;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1,0,0,0},{1,1,0,0}};
        int[][] hits = new int[][]{{1,1},{1,0}};
        Solution s = new Solution();
        int[] output = s.hitBricks(grid, hits);
        System.out.println(Arrays.toString(output));
    }
}