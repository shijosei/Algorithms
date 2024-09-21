import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int trials; // number of trials
    private final double[] thresholds; // percolation thresholds

    /**
     * Perform independent trials on an n-by-n grid
     * @param n
     * @param trials
     */
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();

        this.trials = trials;
        this.thresholds = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            int openSites = 0;

            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n + 1); // generates random row between 1 and n
                int col = StdRandom.uniform(1, n + 1); // generates random col between 1 and n

                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    openSites++;
                }
            }

            thresholds[i] = (double) openSites / (n * n); // fraction of open sites
        }
    }

    /**
     * Sample mean of percolation threshold
     * @return
     */
    public double mean() {
        return StdStats.mean(thresholds);
    }

    /**
     * Sample standard deviation of percolation threshold
     * @return
     */
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    /**
     * Low endpoint of 95% confidence interval
     * @return
     */
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(trials));
    }

    /**
     * High endpoint of 95% confidence interval
     * @return
     */
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(trials));
    }

    /**
     * Test client (takes two command-line arguments n and T)
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please provide two arguments: grid size (n) and number of trials.");
            return;
        }

        int n = Integer.parseInt(args[0]); // grid size
        int trials = Integer.parseInt(args[1]); // number of trials

        PercolationStats percolationStats = new PercolationStats(n, trials);
        System.out.println("mean                    = " + percolationStats.mean());
        System.out.println("stddev                  = " + percolationStats.stddev());
        System.out.println("95% confidence interval = " + "[" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }
}
