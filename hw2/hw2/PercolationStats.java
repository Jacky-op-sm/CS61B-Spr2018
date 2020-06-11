package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    double[] stats;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        validate(N, T);
        stats = new double[T];
        for (int i = 0; i < T; i += 1) {
            Percolation exp = pf.make(N);
            while (!exp.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                exp.open(row, col);
            }
            double openRatio = (double) exp.numberOfOpenSites() / (N * N);
            stats[i] = openRatio;
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(stats);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(stats);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double m = mean();
        double s = stddev();
        return m - 1.96 * s / (Math.sqrt(stats.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double m = mean();
        double s = stddev();
        return m + 1.96 * s / (Math.sqrt(stats.length));
    }

    // validate that (row, col) is a valid
    private void validate(int n, int t) {
        if (n <= 0 || t <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
    }
}


